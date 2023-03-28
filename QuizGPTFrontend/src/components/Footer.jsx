import React from "react";
import styles from "../../styles/footer.module.css";

export default function Navbar() {
  return (
    <footer class={styles.footer}>
      <p class={styles["footer-p"]}>
        &copy; 2023 QuizGPT. All rights reserved.
      </p>
    </footer>
  );
}
