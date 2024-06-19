import { createContext, useEffect, useState } from "react";
import cookie from "react-cookies";
import APIs, { authApi, endpoints } from "../configs/APIs"; // Import sắp xếp lại

export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(
    JSON.parse(localStorage.getItem("user")) || null
  );
  const getToken = () => cookie.load("token");

  const login = async (input) => {
    try {
      const params = new URLSearchParams();
      params.append('client_id', 'sTC0ix5fSQ12R6Pa4323TrbWxIPmYA4vLO40CdFv');
      params.append('client_secret', 'NQkMojPVrvjMN0MIoBfEd0bPE9cu1XlShzNgddncNOE9iSm2PesAtP9oNZP50YjkH70VfmL1EQFHCfCo4tAK8yOhjB1nSXB6GN0uE9fASWrYw5xu4kKd4IOmxN1QOQOT');
      params.append('grant_type', 'password');
      params.append('username', input.username);
      params.append('password', input.password);

      const res = await APIs.post(endpoints['login'], params, {
        headers: { 'Authorization': `Bearer ${getToken()}` },
        withCredentials: true,
      });
      cookie.save("token", res.data.access_token);

      const u = await authApi().get(endpoints['current-user']);
      cookie.save("user", u.data);
      setCurrentUser(u.data);

      localStorage.setItem("user", JSON.stringify(u.data));
      return u.data;
    } catch (err) {
      console.error(err);
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
