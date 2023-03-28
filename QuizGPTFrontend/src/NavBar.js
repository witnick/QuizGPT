import React from "react";
import styles from "../styles/home.module.css";
import Link from "next/link";

export default function Navbar() {
  return (
    <header class={styles.header}>
      <h1 class={styles.logo}>QuizGPT</h1>
      <div class={styles["btn-group"]}>
        <nav>
          <ul class={styles["nav-links"]}>
            <li>
              <Link href="/" class={styles["button"]}>
                Home
              </Link>
            </li>
            <li>
              <Link href="/login" class={styles["button"]}>
                Login
              </Link>
            </li>
          </ul>
        </nav>
      </div>
    </header>
  );
}
