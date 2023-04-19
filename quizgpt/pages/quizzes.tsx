import React, { useEffect, useState } from "react";
import { useAuthContext } from "../src/context/AuthContext";
import styles from "../styles/home.module.css";
import { useRouter } from "next/router";
import { Quiz } from "./quiz/[quizId]";
import axios from "axios";
import { api } from "./create-quiz";

export default function Quizzes() {
  const { loggedIn } = useAuthContext();
  const router = useRouter();

  const [quizzes, setQuizzes] = useState<Quiz[] | null>(null);

  useEffect(() => {
    if (!loggedIn) {
      router.push("/");
    }
  }, [loggedIn]);

  useEffect(() => {
    axios
      .get<Quiz[]>(api + "/quizzes")
      .then((res) => {
        console.log(res.data);
        setQuizzes(res.data);
      })
      .catch((e) => {
        console.error(e);
        setQuizzes(null);
      });
  }, []);

  if (quizzes === null)
    return (
      <div className={styles.container}>
        <div className={styles.main}>
          <text>{"Loading..."}</text>
        </div>
      </div>
    );

  if (quizzes.length === 0)
    return (
      <div className={styles.container}>
        <div className={styles.main}>
          <text>{"No quizzes created"}</text>
        </div>
      </div>
    );

  return (
    <div className={styles.container}>
      <div className={styles.main}>
        <div
          style={{
            display: "flex",
            alignItems: "stretch",
            flexDirection: "column",
            width: "100%",
            flex: 1,
          }}
        >
          {quizzes.map((quiz) => (
            <div
              style={{
                display: "flex",
                margin: 25,
                borderWidth: 1,
                borderStyle: "solid",
                padding: 25,
                borderColor: "black",
                minWidth: 200,
                justifyContent: "center",
              }}
            >
              <text>{quiz.name}</text>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
