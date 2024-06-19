import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'https://longtocdo107.pythonanywhere.com/';

export const endpoints = {
    'categories': '/api/categories/', 
    'posts': '/api/posts/',
    'details': (postId) => `/api/posts/${postId}/`,
    'register': '/api/users/',
    'login': '/o/token/',
    'current-user': '/current-user/'
}

export const authApi = () => {
    const token = cookie.load("token");

    return axios.create({
        baseURL: BASE_URL,
        headers: {
             'Authorization': `Bearer ${token}`
        }
    });
}

export default axios.create({
    baseURL: BASE_URL
});