'use client';

import { useState } from 'react';
import Image from 'next/image';
import { useRouter } from 'next/navigation';
import styles from './login.module.css'; 
import { LoginData } from '@/types/gameTypes';

export default function LoginPage() {
  const [username, setUsername] = useState('');
  const router = useRouter();

  const handleLogin = async () => {
    if (username.trim() === '') return;
  
    const loginDTO: LoginData = { username };
  
    const response = await fetch('http://localhost:8082/api/players/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(loginDTO),
    });
  
    if (response.ok) {
      router.push('/gameMap');
    } else {
      alert('Login fehlgeschlagen!');
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.overlay}>
        <h1 className={styles.title}>LOGIN</h1>
        <input
          className={styles.input}
          type="text"
          placeholder="Benutzername eingeben"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <button className={styles.button} onClick={handleLogin}>
          Starten
        </button>
      </div>
    </div>
  );
}