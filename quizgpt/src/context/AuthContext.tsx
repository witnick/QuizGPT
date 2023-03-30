/* eslint-disable no-unused-vars */
import React, { createContext, useContext, useState } from "react";

interface AuthContextData {
  loggedIn: boolean;
  login: () => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextData>({
  loggedIn: false,
  login: () => {},
  logout: () => {},
});

function useAuthContext() {
  return useContext(AuthContext);
}

function AuthContextProvider({ children }: { children: React.ReactNode }) {
  const [loggedIn, setLoggedIn] = useState(true);

  const logout = () => {
    setLoggedIn(false);
  };

  const login = () => {
    setLoggedIn(true);
  };

  return (
    <AuthContext.Provider value={{ loggedIn, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export { useAuthContext, AuthContextProvider };
