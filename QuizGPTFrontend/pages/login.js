import styles from "../styles/home.module.css";
import Navbar from "../src/NavBar";

export default function Login() {
  return (
    <div class={styles.container}>
      <Navbar />
      <div class={styles.main}>
        <h2>Login or sign up</h2>
      </div>
      <footer class={styles.footer}>
        <p class={styles["footer-p"]}>
          &copy; 2023 QuizGPT. All rights reserved.
        </p>
      </footer>
    </div>
  );
}
