import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { authApi, endpoints } from "../../configs/APIs";
import cookie from "react-cookies";
import "./comments.css";

const Comments = ({ postId }) => {
  const currentUser = useSelector(state => state.user.user);
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState("");

  useEffect(() => {
    const fetchComments = async () => {
      try {
        const res = await authApi().get(endpoints['comments'], { desc: newComment }, {
          headers: {
            'Authorization': `${cookie.load('token')}`
          }
        });
        console.log(res.data)
        setComments(res.data);
      } catch (error) {
        console.error("Failed to fetch comments", error);
      }
    };

    fetchComments();
  }, [postId]);

  const handleAddComment = async () => {
    try {
      const res = await authApi().post(endpoints['comments'], { desc: newComment }, {
        headers: {
          'Authorization': `${cookie.load('token')}`
        }
      });
      setComments([...comments, res.data]);
      setNewComment("");
    } catch (error) {
      console.error("Failed to add comment", error);
    }
  };

  const handleDeleteComment = async (commentId) => {
    try {
      await authApi().delete(`/api/posts/${postId}/comments/${commentId}`, {
        headers: {
          'Authorization': `${cookie.load('token')}`
        }
      });
      setComments(comments.filter(comment => comment.id !== commentId));
    } catch (error) {
      console.error("Failed to delete comment", error);
    }
  };

  return (
    <div className="comments">
      <div className="write">
        <input
          type="text"
          placeholder="Write a comment"
          value={newComment}
          onChange={(e) => setNewComment(e.target.value)}
        />
        <button onClick={handleAddComment}>Send</button>
      </div>
      {comments.map(comment => (
        <div className="comment" key={comment.id}>
          <img src={comment.profilePicture} alt="" />
          <div className="info">
            <span>{comment.name}</span>
            <p>{comment.desc}</p>
          </div>
          <span className="date">1 hour ago</span>
          {(currentUser.userID === comment.userId || currentUser.userID === postId) && (
            <button onClick={() => handleDeleteComment(comment.id)}>Delete</button>
          )}
        </div>
      ))}
    </div>
  );
};

export default Comments;
