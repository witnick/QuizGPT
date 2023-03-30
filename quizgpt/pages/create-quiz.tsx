import { useRouter } from "next/router";
import React, { useEffect } from "react";
import { useAuthContext } from "../src/context/AuthContext";
import styles from "../styles/form.module.css";

export default function CreateQuiz() {
  const { loggedIn } = useAuthContext();
  const router = useRouter();

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
            <input className={styles.input} type="text" />
          </div>
          <div className={styles.inputContainer}>
            <text className={styles.label}>Difficulty</text>
            <select className={styles.input}>
              <option value="">Select difficulty</option>
              <option value="easy">Easy</option>
              <option value="medium">Medium</option>
              <option value="hard">Hard</option>
            </select>
          </div>
          <div className={styles.inputContainer}>
            <text className={styles.label}>Number of questions</text>
            <input className={styles.input} type="number" />
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
