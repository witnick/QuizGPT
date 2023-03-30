import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";
import { useAuthContext } from "../src/context/AuthContext";
import styles from "../styles/form.module.css";

enum Difficulty {
  NONE = "",
  EASY = "EASY",
  MEDIUM = "MEDIUM",
  HARD = "HARD",
}

export default function CreateQuiz() {
  const { loggedIn } = useAuthContext();
  const router = useRouter();

  const [topic, setTopic] = useState("");
  const [difficulty, setDifficulty] = useState<Difficulty>(Difficulty.NONE);
  const [numOfQuestions, setNumOfQuestions] = useState("");

  useEffect(() => {
    if (!loggedIn) {
      router.push("/");
    }
  }, [loggedIn]);

  return (
    <div className={styles.container}>
      <div className={styles.main}>
        <h2 className={styles.header}>Create a quiz</h2>
        <div
          style={{
            flexDirection: "column",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          <div className={styles.inputContainer}>
            <text className={styles.label}>Topic</text>
            <input
              className={styles.input}
              type="text"
              value={topic}
              onChange={(e) => setTopic(e.target.value)}
            />
          </div>
          <div className={styles.inputContainer}>
            <text className={styles.label}>Difficulty</text>
            <select
              className={styles.input}
              onChange={(e) => setDifficulty(e.target.value as Difficulty)}
            >
              <option value={Difficulty.NONE}>Select difficulty</option>
              <option value={Difficulty.EASY}>Easy</option>
              <option value={Difficulty.MEDIUM}>Medium</option>
              <option value={Difficulty.HARD}>Hard</option>
            </select>
          </div>
          <div className={styles.inputContainer}>
            <text className={styles.label}>Number of questions</text>
            <input
              className={styles.input}
              type="number"
              value={numOfQuestions}
              onChange={(e) => setNumOfQuestions(e.target.value)}
            />
          </div>
          <div className={styles.buttonContainer}>
            <button
              className={styles.button}
              onClick={() => {
                console.log("generating quiz");
              }}
            >
              Submit
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
