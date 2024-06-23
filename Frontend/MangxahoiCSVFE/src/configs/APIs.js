import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/AlumniSocial/';

export const endpoints = {
    'posts': '/api/posts/',
    'survey':'/api/posts/create_survey/',
    'comments': (postID) => `/api/post/${postID}/comments`,
    'register': '/api/users/',
    'login': '/api/login/',
    'current-user': '/api/current-user/',
    'reactions':(postID )=>`/api/post/${postID}/reactions`,
}

export const authApi = () => {
    return axios.create({
        baseURL: BASE_URL,  
        headers: {
              'Authorization': `${cookie.load('token')}`}

    });
}

export default axios.create({
    baseURL: BASE_URL
});