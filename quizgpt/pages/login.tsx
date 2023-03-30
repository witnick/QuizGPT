import { useRouter } from "next/router";
import React, { useEffect } from "react";
import { useAuthContext } from "../src/context/AuthContext";
import styles from "../styles/form.module.css";

export default function Login() {
  const { loggedIn, login } = useAuthContext();
  const router = useRouter();

  useEffect(() => {
    if (loggedIn) {
      router.push("/");
    }
  }, [loggedIn]);

  return (
    <div className={styles.container}>
      <div className={styles.main}>
        <h2 className={styles.header}>Login</h2>
        <div
          style={{
            flexDirection: "column",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          <div className={styles.inputContainer}>
            <text className={styles.label}>Username</text>
            <input className={styles.input} type="text" />
          </div>
          <div className={styles.inputContainer}>
            <text className={styles.label}>Password</text>
            <input className={styles.input} type="password" />
          </div>
          <button
            className={styles.button}
            onClick={() => {
              login();
              router.push("/");
            }}
          >
            Login
          </button>
        </div>
      </div>
    </div>
  );
}
