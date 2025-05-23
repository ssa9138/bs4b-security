"use client";

import React from 'react';
import styles from './startScreen.module.css';

function StartScreen() {
    return (
        <div className={styles.startscreen}>
            <h1 className={styles.title}>BS4B</h1>
            <div className={styles.buttons}>
                <button className={styles.button}>Choose Map</button>
                <button className={styles.button}>Brawler</button>
            </div>
        </div>
    );
}

export default StartScreen;
