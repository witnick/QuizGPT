import React, { useEffect } from "react";
import { useAuthContext } from "../src/context/AuthContext";
import styles from "../styles/home.module.css";
import { useRouter } from "next/router";

export default function Quizzes() {
  const { loggedIn } = useAuthContext();
  const router = useRouter();

  useEffect(() => {
    if (loggedIn) {
      router.push("/");
    }
  }, [loggedIn]);

  return (
    <div className={styles.container}>
      <div className={styles.main}>
        <text>{"hello world"}</text>
      </div>
    </div>
  );
}
