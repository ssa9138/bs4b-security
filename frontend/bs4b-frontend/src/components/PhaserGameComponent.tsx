"use client";
 
import React, { useEffect, useRef, useState } from 'react';
import { Game } from 'phaser';
import { gameConfig } from '@/components/game/gameConfig';
import { createStompClient } from '@/network/stompService';
import { Client } from '@stomp/stompjs';
 
export default function PhaserGameComponent() {
  const gameContainerRef = useRef<HTMLDivElement>(null);
  const gameRef = useRef<Game | null>(null);
  const stompClientRef = useRef<Client>(null);
  
  
  const [gameId, setGameId] = useState<string | null>(null);
  async function createGame() {
    const payload = { brawlerIds: [1, 2, 3] }; 
    const response = await fetch("http://localhost:8080/api/game/create", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    });

    if (!response.ok) {
      throw new Error("Spielerstellung fehlgeschlagen");
    }
    const result = await response.json();
    return result; 
  }

  useEffect(() => {
    async function init() {
      try {
        const gameResponse = await createGame();
        const newGameId = gameResponse.gameId;
        setGameId(newGameId);
        
        stompClientRef.current = createStompClient({
          gameId: newGameId,
          onMessage: (data) => {
            console.log("Empfangener Game-State:", data);
          }
        });

        // Phaser Spiel initialisieren
        if (gameContainerRef.current && !gameRef.current) {
          const config = {
            ...gameConfig,
            parent: gameContainerRef.current,
          };
          gameRef.current = new Game(config);

          gameRef.current.scene.start('BaseGameScene', { gameId: newGameId });
        }
      } catch (error) {
        console.error("Fehler beim Initialisieren:", error);
      }
    }
    init();

    // Cleanup: Beim Demontieren der Komponente Ressourcen freigeben
    return () => {
      if (gameRef.current) {
        gameRef.current.destroy(true);
        gameRef.current = null;
      }
      if (stompClientRef.current) {
        stompClientRef.current.deactivate();
      }

    };
  }, []);

  return (
    <div
      ref={gameContainerRef}
      style={{ width: "100%", height: "100%" }}
    />
  );
}