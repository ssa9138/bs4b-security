export interface BrawlerData {
    id: number;
    box: BoundingBox;
}
export interface ProjectileData {
    id: string; 
    shooterId: number;
    box: BoundingBox; 
    dx: number;
    dy: number;
}
// GameStateDTO
export interface GameData {
    gameId:string;
    map: MapData;
    brawlerDTOS: BrawlerData[];
    projectileDTOS: ProjectileData[];
}

export interface LoginData {
    username: string;
}

export interface ObstacleData {
    id: number;
    box: BoundingBox;
    
}

export interface MapData {
    height: number;
    width: number;
    obstacles?: ObstacleData[]
}
export interface BoundingBox {
    x: number;
    y: number;
    width: number;
    height: number;
}

// PlayerMoveDTO
export interface MoveMessage {
    gameId: string;
    brawlerId: number;
    movement: {
        dx: number;
        dy: number;
    };
}
export interface ShootMessage {
    gameId: string;
    brawlerId: number;
    dx: number;
    dy: number;
}
