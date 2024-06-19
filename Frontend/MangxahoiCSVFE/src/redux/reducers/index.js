import { combineReducers } from 'redux';
import userReducer from './userReducer';

const rootReducer = combineReducers({
  user: userReducer,
  // thêm các reducer khác vào đây
});

export default rootReducer;