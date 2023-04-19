import React, { useEffect, useState } from "react";
import { useAuthContext } from "../../src/context/AuthContext";
import styles from "../../styles/home.module.css";
import { useRouter } from "next/router";
import axios from "axios";
import { api } from "../create-quiz";

export const dummyQuiz = {
  name: "Quiz 1",
  id: 1,
  description: "This is Quiz 1",
  gptRequest: null,
  questions: [],
  gptConversation: {
    id: 1,
    questions: [
      {
        id: 1,
        number: 5,
        text: "What is the meaning of life?",
        conversationId: 1,
        createdAt: "2023-04-12T21:16:24.631797",
        updatedAt: "2023-04-12T21:16:24.631797",
        quizId: 1,
      },
    ],
    responses: [
      {
        id: 1,
        number: 2,
        conversationId: 1,
        results: [
          {
            id: 1,
            question: "Question 1: What is the largest mammal in the world?",
            answer: "Answer: The blue whale.",
          },
          {
            id: 2,
            question:
              "Question 2: What is the only bird that can fly backwards?",
            answer: "Answer: The hummingbird.",
          },
        ],
      },
    ],
    createdAt: "2023-04-12T21:16:24.631797",
    updatedAt: "2023-04-12T21:16:24.631797",
    quizId: 1,
  },
  createdAt: "2023-04-12T21:16:24.631797",
  updatedAt: "2023-04-12T21:16:24.631797",
  status: "DRAFT",
  userId: null,
};

export interface Quiz {
  name: string;
  id: number;
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
    if (!loggedIn) {
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
    // setQuiz(dummyQuiz);
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
        <text style={{ fontSize: 40, margin: 10, fontWeight: "bold" }}>
          {quiz.name}
        </text>
        <text style={{ fontSize: 20, margin: 10 }}>{quiz.description}</text>
        {quiz.gptConversation.responses[0].results.map((item) => (
          <div
            style={{
              margin: 10,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <text style={{ fontSize: 16, fontWeight: "bold", marginBottom: 5 }}>
              {item.question}
            </text>
            <text style={{ fontSize: 16 }}>{item.answer}</text>
          </div>
        ))}
      </div>
    </div>
  );
}
