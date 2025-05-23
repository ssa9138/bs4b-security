import * as Phaser from 'phaser';
import { createStompClient } from '@/network/stompService';
import { BoundingBox, GameData, ProjectileData } from '@/types/gameTypes';
import { createMovementKeys, createShootKey, handleContinuousMovement, handleShoot, MovementKeys } from '@/utils/keyboardHandler';
 
 
export default class BaseGameScene extends Phaser.Scene {
    private stompClient?: ReturnType<typeof createStompClient>;

    private playerRect?: Phaser.GameObjects.Rectangle;

    private obstacleRects = new Map<number, Phaser.GameObjects.Rectangle>();
    private projectileRects = new Map<string, Phaser.GameObjects.Rectangle>();

    //private boundingBoxRect?: Phaser.GameObjects.Rectangle
    //private obstalcesRect: Phaser.GameObjects.Rectangle[] = [];

    private movementKeys?: MovementKeys;
    private shootKey!: Phaser.Input.Keyboard.Key;
    private gameId!: string; 
 
    constructor() {
        super({ key: 'BaseGameScene' });
    }

    init(data: { gameId: string }): void {
        this.gameId = data.gameId;
    }
 
    preload(): void {
            
    }
 
    create(): void {
        this.playerRect = this.add.rectangle(0,0,32,64,0xff0000).setOrigin(0,0);
        this.cameras.main.startFollow(this.playerRect).setZoom(2);

        

        this.movementKeys = createMovementKeys(this);
        this.shootKey = createShootKey(this);
        
        this.stompClient = createStompClient({
            gameId: this.gameId,
            onMessage: (data) => this.applyGameUpdate(data)
        });

        this.events.once(Phaser.Scenes.Events.SHUTDOWN, () => this.stompClient?.deactivate());
        //const centerX = this.cameras.main.width / 2;
        //const centerY = this.cameras.main.height / 2;
        //this.boundingBoxRect = this.add.rectangle(centerX, centerY, 50, 50, 0xff0000, 1);
        //this.boundingBoxRect.setOrigin(0, 0); 
        //this.cameras.main.startFollow(this.boundingBoxRect, false);
        //this.cameras.main.setZoom(2.0);
        /*this.stompClient = createStompClient({
        gameId: this.gameId,
        onMessage: (data: GameData) => {
            const mapWidth = data.map.width;
            const mapHeight = data.map.height;


            this.cameras.main.setBounds(0, 0, mapWidth, mapHeight);
            
            if (data.brawlerDTOS.length > 0 && data.brawlerDTOS[0].box){
                const box = data.brawlerDTOS[0].box;

                if (this.boundingBoxRect) {
                    this.boundingBoxRect.x = box.x;
                    this.boundingBoxRect.y = box.y;
                    this.boundingBoxRect.width = box.width;
                    this.boundingBoxRect.height = box.height;
                }
            }

            if(data.map.obstacles && data.map.obstacles.length > 0){
                this.obstalcesRect.forEach(rect => rect.destroy());
                this.obstalcesRect = [];

                for (const obstacle of data.map.obstacles){
                    const box = obstacle.box;

                    const obsRect = this.add.rectangle(
                        box.x,
                        box.y,
                        box.width,
                        box.height,
                        0x00ff00
                    );
                    obsRect.setOrigin(0, 0);
                    this.obstalcesRect.push(obsRect);
                    
                }
            }
        },
        });*/
 
 
        /*this.events.on('shutdown', () => {
        if (this.stompClient) {
            this.stompClient.deactivate();
            console.log('STOMP-Client wurde bei Szenen-Shutdown deaktiviert.');
        }
        });
        this.events.on('destroy', () => {
        if (this.stompClient) {
            this.stompClient.deactivate();
            console.log('STOMP-Client wurde bei Szenen-Destroy deaktiviert.');
        }
        });*/
    }

    private applyGameUpdate(data: GameData): void {

        if (!data.map || !data.brawlerDTOS) return;

        this.cameras.main.setBounds(0,0,data.map.width,data.map.height);

        const me = data.brawlerDTOS[0];
        if(me && this.playerRect) {
            this.playerRect.setPosition(me.box.x,me.box.y).setSize(me.box.width,me.box.height);
        }

        if (data.map.obstacles)
            this.syncRectMap(this.obstacleRects, data.map.obstacles, 0x00ff00);

        this.syncProjectiles(this.projectileRects, data.projectileDTOS ?? []);

        
    }

    private syncRectMap(localMap: Map<number, Phaser.GameObjects.Rectangle>, serverArr: {id: number, box: BoundingBox}[], color : number){
        for (const dto of serverArr){
            const rect = localMap.get(dto.id) ?? this.add.rectangle(0,0,0,0,color).setOrigin(0,0).setDepth(color === 0xffff00 ? 10 : 5);

            rect.setPosition(dto.box.x,dto.box.y).setSize(dto.box.width,dto.box.height);

            localMap.set(dto.id,rect);
        }

        for(const [id, rect] of localMap){
            if(!serverArr.find(o => o.id === id)){
                rect.destroy();
                localMap.delete(id);
            }
        }
    }

    private syncProjectiles(
        local : Map<string, Phaser.GameObjects.Rectangle>,
        server: ProjectileData[]
      ) {
        for (const p of server) {
          const rect = local.get(p.id) ??
            this.add.rectangle(0, 0, 0, 0, 0xffff00)
                    .setOrigin(0, 0)
                    .setDepth(10);
      
          rect.setPosition(p.box.x, p.box.y).setSize(p.box.width, p.box.height);
          local.set(p.id, rect);
        }
      
        for (const [id, rect] of local) {
          if (!server.find(pr => pr.id === id)) {
            rect.destroy();
            local.delete(id);
          }
        }
      }
 
    update(time: number, delta: number): void {
        //console.log("Time: "+time, " Delta: " + delta);
        if (this.movementKeys) {
            //console.log("Update läuft. Delta:", delta);
            handleContinuousMovement(this.movementKeys, this.stompClient,this.gameId);
        }
        handleShoot(this.shootKey, this.stompClient,this.gameId,this.playerRect!);
    }
}
 