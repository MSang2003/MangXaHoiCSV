import { useEffect, useState } from "react";
import Post from "../post/Post";
import "./posts.css";
import APIs, { authApi, endpoints } from '../../configs/APIs';
import cookie from "react-cookies";


const Posts = () => {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    console.log(cookie.load("token"))
    const loadPosts = async () => {

      try {
        const res = await authApi().get(endpoints['posts']);
        console.log(res.data)
        
        setPosts(res.data);
      } catch (error) {
        console.error("Failed to fetch posts", error);
      }
    };

    loadPosts();
  }, []);

  return (
    <div className="posts">
      {posts.map((post) => (
        <Post post={post} key={post.postID} />
      ))}
    </div>
  );
};

export default Posts;
