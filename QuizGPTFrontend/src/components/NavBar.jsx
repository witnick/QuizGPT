import React from "react";
import styles from "../../styles/navbar.module.css";
import Link from "next/link";

const spacingHorizontal = {
  width: 20,
};

export default function Navbar() {
  return (
    <div class={styles.header}>
      <h1 class={styles.logo}>QuizGPT</h1>
      <div class={styles["btn-group"]}>
        <nav>
          <ul class={styles["nav-links"]}>
            <li>
              <Link href="/" class={styles["button"]}>
                Home
              </Link>
            </li>
            <div style={spacingHorizontal} />
            <li>
              <Link href="/login" class={styles["button"]}>
                Login
              </Link>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  );
}
