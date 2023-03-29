import React from "react";
import styles from "../../styles/footer.module.css";

export default function Navbar() {
  return (
    <footer className={styles.footer}>
      <p className={styles.text}>&copy; 2023 QuizGPT. All rights reserved.</p>
    </footer>
  );
}
