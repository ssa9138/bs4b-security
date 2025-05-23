"use client";
import React from 'react';
import './Lobby.css'; 

export default function Lobby() {
  return (
    <div className="lobby-container">
        <h1 className="lobby-title">Warte auf weitere Spieler…</h1>

        <div className="player-slot slot1">🧝 Spieler 1 (ich)</div>
        <div className="player-slot slot2">🧙 Warte auf Spieler 2…</div>
        <div className="player-slot slot3">🗡️ Warte auf Spieler 3…</div>

        <button className="leave-button">Lobby verlassen</button>
    </div>
  );
}


// brawler information hinzufügen, was für ein brawler der hat, bild oder name ?
