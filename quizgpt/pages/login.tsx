import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";
import { useAuthContext } from "../src/context/AuthContext";
import styles from "../styles/form.module.css";

export default function Login() {
  const { loggedIn, login } = useAuthContext();
  const router = useRouter();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(false);

  useEffect(() => {
    if (loggedIn) {
      router.push("/");
    }
  }, [loggedIn]);

  const onClick = async () => {
    try {
      await login(username, password);
      router.push("/");
    } catch (e) {
      setError(true);
      setTimeout(() => {
        setError(false);
      }, 3000);
    }
  };

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
            <input
              className={styles.input}
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div className={styles.inputContainer}>
            <text className={styles.label}>Password</text>
            <input
              className={styles.input}
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button className={styles.button} onClick={onClick}>
            {error ? "Error" : "Login"}
          </button>
        </div>
      </div>
    </div>
  );
}
