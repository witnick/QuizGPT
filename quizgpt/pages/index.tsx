import React from "react";
import { useAuthContext } from "../src/context/AuthContext";
import Link from "next/link";
import styles from "../styles/home.module.css";

export default function Home() {
  const { loggedIn } = useAuthContext();
  return (
    <div className={styles.container}>
      <div
        className={styles.main}
        style={loggedIn ? { justifyContent: "center" } : {}}
      >
        {!loggedIn && (
          <>
            <h2 className={styles.headerText}>Welcome to the QuizGPT</h2>
            <p className={styles.text}>
              {`Our app is designed to help you create engaging and interactive
              quizzes for your audience. Whether you're a teacher looking to
              create a fun quiz for your students, or a marketer looking to
              engage your audience with a quiz, our app has you covered.`}
            </p>
            <a
              href="https://openai.com/blog/chatgpt"
              className={styles["btn-learnmore"]}
              target="_blank"
            >
              Learn More
            </a>
          </>
        )}
        {loggedIn && (
          <div className={styles.buttonContainer}>
            <Link href="/create-quiz" className={styles.button}>
              Create a quiz
            </Link>
            <Link href="/quizzes" className={styles.button}>
              Show all quizzes
            </Link>
          </div>
        )}
      </div>
    </div>
  );
}
