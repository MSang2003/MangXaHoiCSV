import { BrowserRouter, Routes, Route, Navigate, Outlet } from "react-router-dom";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Navbar from "./components/navbar/Navbar";
import LeftBar from "./components/leftBar/LeftBar";
import Home from "./pages/home/Home";
import Profile from "./pages/profile/Profile";
import "./style.css";
import { useContext } from "react";
import { AuthContext } from "./context/authContext";
import { Provider } from "react-redux";
import store from "./redux/store";

function App() {
  // const {currentUser} = useContext(AuthContext);


  const Layout = () => {
    return (
      <div>
        <Navbar />
        <div style={{ display: "flex" }}>
          <LeftBar />
          <div style={{ flex: 6 }}>
            <Outlet />
          </div>
        </div>
      </div>
    );
  };

  const ProtectedRoute = ({ children }) => {
    // if (!currentUser) {
    //   return <Navigate to="/login" />;
    // }

    return children;
  };

  return (
    <Provider store={store}>
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            // <ProtectedRoute>
              <Layout />
            // </ProtectedRoute>
          }
        >
          <Route index element={<Home />} />
          <Route path="profile/:id" element={<Profile />} />
        </Route>
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
      </Routes>
    </BrowserRouter>
    </Provider>
  );
}

export default App;
