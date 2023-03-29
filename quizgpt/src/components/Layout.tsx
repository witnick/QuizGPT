import React from "react";
import Navbar from "./NavBar";
import Footer from "./Footer";

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <div
      style={{
        height: "100vh",
        display: "flex",
        flexDirection: "column",
        flex: 1,
      }}
    >
      <Navbar />
      {children}
      <Footer />
    </div>
  );
}
