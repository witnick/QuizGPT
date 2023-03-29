import React from "react";
import styles from "../../styles/navbar.module.css";
import Link from "next/link";

export default function Navbar() {
  return (
    <div className={styles.container}>
      <h1 className={styles.logo}>QuizGPT</h1>
      <div className={styles.navContainer}>
        <nav>
          <ul className={styles.navLinks}>
            <li>
              <Link href="/" className={styles.button}>
                Home
              </Link>
            </li>
            <div style={{ width: 20 }} />
            <li>
              <Link href="/login" className={styles.button}>
                Login
              </Link>
            </li>
            <div style={{ width: 20 }} />
            <li>
              <Link href="/signup" className={styles.button}>
                Sign up
              </Link>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  );
}
