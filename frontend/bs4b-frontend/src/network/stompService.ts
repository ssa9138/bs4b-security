import { GameData } from '@/types/gameTypes';
import { Client, IMessage } from '@stomp/stompjs';

 
interface StompOptions {
  onMessage: (data: GameData) => void;
  gameId: string;
}
 
export function createStompClient({ onMessage, gameId }: StompOptions): Client {
  const client = new Client({
    brokerURL: "ws://localhost:8080/ws-game",
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
    onConnect: () => {
      console.log('STOMP-Client verbunden');
      client.subscribe(`/topic/gameState/${gameId}`, (message: IMessage) => {
        //console.log("Received Message:", message.body);
        const data: GameData = JSON.parse(message.body);
        onMessage(data);
      });
    },
  });
  client.activate();
  return client;
}