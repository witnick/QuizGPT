import Navbar from "./NavBar";
import Footer from "./Footer";

const style = {
  height: "100vh",
  display: "flex",
  flexDirection: "column",
  flex: 1,
};

export default function Layout({ children }) {
  return (
    <div style={style}>
      <Navbar />
      {children}
      <Footer />
    </div>
  );
}
