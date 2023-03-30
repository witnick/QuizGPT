import Link from "next/link";
import { useRouter } from "next/router";
import React, { useEffect } from "react";
import { useAuthContext } from "../src/context/AuthContext";
import styles from "../styles/form.module.css";

export default function SignUp() {
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
            <input className={styles.input} type="text" />
          </div>
          <div className={styles.inputContainer}>
            <text className={styles.label}>Email</text>
            <input className={styles.input} type="email" />
          </div>
          <div className={styles.inputContainer}>
            <text className={styles.label}>Password</text>
            <input className={styles.input} type="password" />
          </div>
          <div className={styles.inputContainer}>
            <text className={styles.label}>Confirm password</text>
            <input className={styles.input} type="password" />
          </div>
          <button className={styles.button}>Sign Up</button>
          <p>
            Already have an account? <Link href={"/login"}>Log in</Link>
          </p>
        </div>
      </div>
    </div>
  );
}
