import Link from "next/link";
import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";
import { useAuthContext } from "../src/context/AuthContext";
import styles from "../styles/form.module.css";

export default function SignUp() {
  const { loggedIn, signup } = useAuthContext();
  const router = useRouter();

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(false);

  useEffect(() => {
    if (loggedIn) {
      router.push("/");
    }
  }, [loggedIn]);

  const onClick = async () => {
    try {
      await signup(username, email, password);
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
        <h2 className={styles.header}>Sign up</h2>
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
            <text className={styles.label}>Email</text>
            <input
              className={styles.input}
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
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
            {error ? "Error" : "Sign up"}
          </button>
          <p>
            Already have an account? <Link href={"/login"}>Log in</Link>
          </p>
        </div>
      </div>
    </div>
  );
}
