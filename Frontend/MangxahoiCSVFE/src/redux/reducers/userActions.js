
import { type } from '@testing-library/user-event/dist/type';
import APIs, { authApi, endpoints } from '../../configs/APIs';
import { loginStart, loginSuccess, loginFailure, logoutSuccess } from '../reducers/userReducer';
import cookie from "react-cookies";

export const login = (username, password) => async (dispatch) => {
  dispatch(loginStart());
  try {
           let res = await APIs.post(endpoints['login'], {
            "userName": username,
            "password": password
        });
          
            console.info(res.data);
            cookie.save("token", res.data);
          
            let u = await authApi().get(endpoints['current-user']);
            cookie.save('user', u.data);
            localStorage.setItem('token', res.data);
            localStorage.setItem('user', JSON.stringify(u.data));



    // API get user

    if (u.status==200) {
      dispatch(loginSuccess(u.data));}
    else {
    }
  } catch (error) {
    dispatch(loginFailure(error.toString()));
    console.log(error)
  }
};
export const logout = () => async (dispatch) => {
  try {
    // Xóa thông tin đăng nhập khỏi localStorage
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    // Xóa thông tin đăng nhập khỏi cookie
    cookie.remove("token");
    cookie.remove("user");

    dispatch(logoutSuccess());
  } catch (error) {
    console.error("Failed to logout:", error);
  }
};