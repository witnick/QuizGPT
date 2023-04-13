import React, { useEffect, useState } from "react";
import { useAuthContext } from "../../src/context/AuthContext";
import styles from "../styles/home.module.css";
import { useRouter } from "next/router";
import axios from "axios";
import { api } from "../create-quiz";

export interface Quiz {
  name: string;
  id: string;
  description: string;
  gptRequest: unknown;
  questions: unknown;
  gptConversation: {
    id: number;
    questions: {
      id: number;
      number: number;
      text: string;
      conversationId: number;
      createdAt: string;
      updatedAt: string;
      quizId: number;
    }[];
    responses: {
      id: number;
      number: number;
      conversationId: number;
      results: {
        id: number;
        question: string;
        answer: string;
      }[];
    }[];
    createdAt: string;
    updatedAt: string;
    quizId: number;
  };
  createdAt: string;
  updatedAt: string;
  status: string;
  userId: unknown;
}

export default function Quizzes() {
  const { loggedIn } = useAuthContext();
  const router = useRouter();

  const [quiz, setQuiz] = useState<Quiz | null>(null);

  useEffect(() => {
    if (loggedIn) {
      router.push("/");
    }
  }, [loggedIn]);

  useEffect(() => {
    if (!router.isReady) return;
    const { query } = router;
    const id = query.quizId as string;
    axios.get<Quiz>(`${api}/quiz/${id}`).then((res) => {
      console.log(res);
      setQuiz(res.data);
    });
  }, [router.isReady]);

  if (!quiz) return null;

  if (quiz.gptConversation.responses.length === 0)
    return (
      <div className={styles.container}>
        <div className={styles.main}>
          <text>
            {"Your quiz is being prepared, please come back later..."}
          </text>
        </div>
      </div>
    );

  return (
    <div className={styles.container}>
      <div className={styles.main}>
        <text>{quiz.name}</text>
        <text>{quiz.description}</text>
        {quiz.gptConversation.responses[0].results.map((item) => (
          <div>
            <text>{item.question}</text>
            <text>{item.answer}</text>
          </div>
        ))}
      </div>
    </div>
  );
}
