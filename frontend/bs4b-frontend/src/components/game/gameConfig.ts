import { Types } from 'phaser';
import BaseGameScene from '@/components/game/scenes/BaseGameScene';
 
export const gameConfig: Types.Core.GameConfig = {
  type: Phaser.AUTO,
  backgroundColor: '#222',
  scene: [BaseGameScene],
  scale: {
    mode: Phaser.Scale.FIT,
    autoCenter: Phaser.Scale.CENTER_BOTH,
    width: 1980,
    height: 1080,                      
  }
};