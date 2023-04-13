import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";
import { useAuthContext } from "../src/context/AuthContext";
import styles from "../styles/form.module.css";
import axios from "axios";

enum Difficulty {
  NONE = "",
  EASY = "East",
  MEDIUM = "Medium",
  HARD = "Hard",
}

interface CreateQuizInput {
  name: string;
  description: string;
  status: string;
  questions: null;
  gptRequest: {
    number: string;
    text: string;
  };
}

interface CreateQuizResponse {
  id: string;
}

export const api = "http://localhost:3001/api/v1";

export default function CreateQuiz() {
  const { loggedIn } = useAuthContext();
  const router = useRouter();

  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [topic, setTopic] = useState("");
  const [difficulty, setDifficulty] = useState<Difficulty>(Difficulty.NONE);
  const [numOfQuestions, setNumOfQuestions] = useState("");

  useEffect(() => {
    if (!loggedIn) {
      router.push("/");
    }
  }, [loggedIn]);

  const onSubmit = async () => {
    const input: CreateQuizInput = {
      name,
      description,
      status: "DRAFT",
      questions: null,
      gptRequest: {
        number: numOfQuestions,
        text: `${topic}. ${difficulty} difficulty.`,
      },
    };
    const res = await axios.post<CreateQuizResponse>(`${api}/queryGpt`, input);
    const quizId = res.data.id;
    console.log(quizId);
  };

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
            <text className={styles.label}>Name</text>
            <input
              className={styles.input}
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <div className={styles.inputContainer}>
            <text className={styles.label}>Description</text>
            <textarea
              className={styles.input}
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
          </div>
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
              onClick={onSubmit}
              disabled={
                name === "" ||
                description === "" ||
                difficulty === "" ||
                topic === "" ||
                numOfQuestions === ""
              }
            >
              Submit
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
