import styles from "../styles/home.module.css";

export default function Home() {
  return (
    <div class={styles.container}>
      <div class={styles.main}>
        <h2 class={styles.headerText}>Welcome to the QuizGPT</h2>
        <p class={styles.text}>
          Our app is designed to help you create engaging and interactive
          quizzes for your audience. Whether you're a teacher looking to create
          a fun quiz for your students, or a marketer looking to engage your
          audience with a quiz, our app has you covered.
        </p>
        <a
          href="https://openai.com/blog/chatgpt"
          class={styles["btn-learnmore"]}
          target="_blank"
        >
          Learn More
        </a>
      </div>
    </div>
  );
}
