
import { loginStart, loginSuccess, loginFailure } from '../reducers/userReducer';

export const login = (username, password) => async (dispatch) => {
  dispatch(loginStart());
  try {
    // const response = await fetch('https://api.example.com/login', {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type': 'application/json',
    //   },
    //   body: JSON.stringify({ username, password }),
    // });
    // const data = await response.json();
    

    // API get user

    // if (response.ok) {
    //   dispatch(loginSuccess(data));
    // } else {
    //   dispatch(loginFailure(data.error));
    // }
  } catch (error) {
    dispatch(loginFailure(error.toString()));
  }
};