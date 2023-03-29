/* eslint-disable no-unused-vars */
import React, { createContext, useContext, useState } from "react";

interface AuthContextData {
  loggedIn: boolean;
}

const AuthContext = createContext<AuthContextData>({
  loggedIn: false,
});

function useAuthContext() {
  return useContext(AuthContext);
}

function AuthContextProvider({ children }: { children: React.ReactNode }) {
  const [loggedIn, setLoggedIn] = useState(true);

  return (
    <AuthContext.Provider value={{ loggedIn }}>{children}</AuthContext.Provider>
  );
}

export { useAuthContext, AuthContextProvider };
