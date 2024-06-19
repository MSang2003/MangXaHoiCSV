
import { createContext, useEffect, useState } from "react";

export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(
    JSON.parse(localStorage.getItem("user")) || null
  );

  const defaultUser = {
    id: 1,
    name: "John Doe",
    username: "longtocdo",
    password: "123",
    profilePic:
      "https://images.pexels.com/photos/3228727/pexels-photo-3228727.jpeg?auto=compress&cs=tinysrgb&w=1600",
  };

  const login = async (input) => {
    // Kiểm tra thông tin đăng nhập
    if (
      input.username === defaultUser.username &&
      input.password === defaultUser.password
    ) {
      setCurrentUser(defaultUser);
    } else {
      throw new Error("Username or password is incorrect");
    }
  };

  useEffect(() => {
    localStorage.setItem("user", JSON.stringify(currentUser));
  }, [currentUser]);

  return (
    <AuthContext.Provider value={{ currentUser, login }}>
      {children}
    </AuthContext.Provider>
  );
};
