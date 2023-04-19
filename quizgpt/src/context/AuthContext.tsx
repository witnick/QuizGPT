/* eslint-disable no-unused-vars */
import axios from "axios";
import React, { createContext, useContext, useEffect, useState } from "react";
import { api } from "../../pages/create-quiz";

interface AuthContextData {
  loggedIn: boolean;
  login: (username: string, password: string) => Promise<void>;
  logout: () => void;
  user: Omit<LoginRes, "statusCode" | "statusCodeValue"> | null;
  signup: (username: string, password: string, email: string) => Promise<void>;
}

interface LoginRes {
  body: {
    accessToken: string;
    email: string;
    id: number;
    roles: string[];
    tokenType: string;
    username: string;
    password: unknown;
  };
  statusCode: string;
  statusCodeValue: number;
}

const AuthContext = createContext<AuthContextData>({
  loggedIn: false,
  login: async () => {},
  logout: () => {},
  signup: async () => {},
  user: null,
});

function useAuthContext() {
  return useContext(AuthContext);
}

function AuthContextProvider({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState<Omit<
    LoginRes,
    "statusCode" | "statusCodeValue"
  > | null>(null);
  const [loggedIn, setLoggedIn] = useState(false);

  const logout = async () => {
    setLoggedIn(false);
    setUser(null);
    localStorage.removeItem("user");
  };

  const signup = async (username: string, password: string, email: string) => {
    const res = await axios.post<{
      statusCode: string;
      statusCodeValue: number;
    }>(api + "/account/signup", {
      username,
      password,
      email,
    });
    const loginRes = res.data;
    if (loginRes.statusCodeValue !== 201) {
      throw new Error("signup error");
    }
    login(username, password);
  };

  const login = async (username: string, password: string) => {
    const res = await axios.post<LoginRes>(api + "/account/login", {
      username,
      password,
    });
    const loginRes = res.data;
    if (loginRes.statusCodeValue !== 200) {
      throw new Error("login error");
    }
    setLoggedIn(true);
    setUser(loginRes);
    localStorage.setItem("user", JSON.stringify(loginRes));
  };

  useEffect(() => {
    const userData = localStorage.getItem("user");
    if (!userData) return;
    const user = JSON.parse(userData) as LoginRes;
    setUser(user);
    setLoggedIn(true);
  }, []);

  return (
    <AuthContext.Provider value={{ loggedIn, login, logout, user, signup }}>
      {children}
    </AuthContext.Provider>
  );
}

export { useAuthContext, AuthContextProvider };
