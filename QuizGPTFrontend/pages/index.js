import styles from "../styles/home.module.css";
import Navbar from "../src/NavBar";

export default function Home() {
  return (
    <div class={styles.container}>
      <Navbar />
      <div class={styles.main}>
        <h2>Welcome to the QuizGPT</h2>
        <p>
          Our app is designed to help you create engaging and interactive
          quizzes for your audience. Whether you're a teacher looking to create
          a fun quiz for your students, or a marketer looking to engage your
          audience with a quiz, our app has you covered.
        </p>
        <a href="#" class={styles["btn-learnmore"]}>
          Learn More
        </a>
      </div>
      <footer class={styles.footer}>
        <p class={styles["footer-p"]}>
          &copy; 2023 QuizGPT. All rights reserved.
        </p>
      </footer>
    </div>
  );
}
