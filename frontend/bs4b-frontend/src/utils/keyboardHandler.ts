import * as Phaser from 'phaser';
import { Client } from '@stomp/stompjs';
import { MoveMessage, ShootMessage } from '@/types/gameTypes';
 
export interface MovementKeys {
    up: Phaser.Input.Keyboard.Key;
    left: Phaser.Input.Keyboard.Key;
    down: Phaser.Input.Keyboard.Key;
    right: Phaser.Input.Keyboard.Key;
}
 
export function createMovementKeys(scene: Phaser.Scene): MovementKeys {
    return scene.input.keyboard!.addKeys({
      up: 'W',
      left: 'A',
      down: 'S',
      right: 'D',
    }) as MovementKeys;
}

export function createShootKey(scene: Phaser.Scene){
  return scene.input.keyboard!.addKey(Phaser.Input.Keyboard.KeyCodes.SPACE);
}
 
export function handleContinuousMovement(
    keys: MovementKeys,
    stompClient: Client | undefined,
    gameId:string
  ) {
    if (!stompClient || !stompClient.connected) return;
 
    let dx = 0;
    let dy = 0;
 
    if (keys.up.isDown) {
      dy-=1;
    } if (keys.down.isDown) {
      dy+=1;
    } if (keys.left.isDown) {
      dx-= 1;
    } if (keys.right.isDown) {
      dx+=1;
    }
 
    if(dx === 0 && dy === 0){
      return;
    }

    
    
    const moveMessage: MoveMessage = {
      gameId: gameId,
      brawlerId: 1,
      movement: {dx, dy},
    };


    stompClient.publish({
      destination: '/bs4b/move',
      body: JSON.stringify(moveMessage),
    });
        
  
}

export function handleShoot(shootKey: Phaser.Input.Keyboard.Key, stomp: Client|undefined, gameId: string, playerRect: Phaser.GameObjects.Rectangle){
  if(!stomp?.connected) return;

  if(Phaser.Input.Keyboard.JustDown(shootKey)){
    const pointer = playerRect.scene.input.activePointer;
    const world = playerRect.scene.cameras.main.getWorldPoint(pointer.x,pointer.y);

    let dx = world.x - (playerRect.x + playerRect.width/2);
    let dy = world.y - (playerRect.y + playerRect.height/2);
    const len = Math.hypot(dx,dy);
    if(len === 0) return;
    dx /=len;
    dy /=len;

    const shoot: ShootMessage = {gameId, brawlerId: 1, dx, dy};
    stomp.publish({destination:'/bs4b/shoot',body:JSON.stringify(shoot)});
  }
}
 