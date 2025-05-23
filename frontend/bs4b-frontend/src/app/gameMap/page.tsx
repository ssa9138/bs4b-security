"use client";
import dynamic from 'next/dynamic';
 
const GameComponent = dynamic(() => import('@/components/PhaserGameComponent'), {
  ssr: false,
});
 
export default function GameMapPage() {
    
  return (
    <div style={{ width: '100vw', height: '100vh', position: 'relative' }}>
      <GameComponent />
    </div>
  );
}