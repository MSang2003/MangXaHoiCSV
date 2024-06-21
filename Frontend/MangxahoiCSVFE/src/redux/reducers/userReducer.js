import { createSlice } from '@reduxjs/toolkit';

const userSlice = createSlice({
  name: 'user',
  initialState: {
    user:  JSON.parse(localStorage.getItem('user')) || null,
    loading: false,
    error: null,
  },
  reducers: {
    loginStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    loginSuccess: (state, action) => {
      state.loading = false;
      state.user = action.payload;
    },
    loginFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    logoutSuccess:(state,action)=>{
      state.user = null;
    }
  },
});

export const { loginStart, loginSuccess, loginFailure ,logoutSuccess} = userSlice.actions;

export default userSlice.reducer;