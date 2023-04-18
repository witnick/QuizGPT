import React from "react";
import styles from "../../styles/navbar.module.css";
import Link from "next/link";
import { useAuthContext } from "../context/AuthContext";
import { useRouter } from "next/router";

export default function Navbar() {
  const { loggedIn, logout } = useAuthContext();
  const router = useRouter();
  return (
    <div className={styles.container}>
      <h1
        className={styles.logo}
        onClick={() => {
          router.push("/");
        }}
      >
        QuizGPT
      </h1>
      <div className={styles.navContainer}>
        <nav>
          <ul className={styles.navLinks}>
            <li>
              <Link href="/" className={styles.button}>
                Home
              </Link>
            </li>
            <div style={{ width: 20 }} />
            {loggedIn && (
              <li>
                <button onClick={logout} className={styles.button}>
                  Logout
                </button>
              </li>
            )}
            {!loggedIn && (
              <li>
                <Link href="/login" className={styles.button}>
                  Login
                </Link>
              </li>
            )}
            {!loggedIn && <div style={{ width: 20 }} />}
            {!loggedIn && (
              <li>
                <Link href="/signup" className={styles.button}>
                  Sign up
                </Link>
              </li>
            )}
          </ul>
        </nav>
      </div>
    </div>
  );
}
