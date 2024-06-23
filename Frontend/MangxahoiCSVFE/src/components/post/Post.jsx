import "./post.css";
import ThumbUpIcon from "@mui/icons-material/ThumbUp";
import FavoriteIcon from "@mui/icons-material/Favorite";
import SentimentVerySatisfiedIcon from "@mui/icons-material/SentimentVerySatisfied";
import TextsmsOutlinedIcon from "@mui/icons-material/TextsmsOutlined";
import ShareOutlinedIcon from "@mui/icons-material/ShareOutlined";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { Link } from "react-router-dom";
import Comments from "../comments/Comments";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { authApi, endpoints } from "../../configs/APIs";
import cookie from "react-cookies";

const Post = ({ post }) => {
  const currentUser = useSelector((state) => state.user.user);
  const [commentOpen, setCommentOpen] = useState(false);
  const [reaction, setReaction] = useState(null);
  const [likeCount, setLikeCount] = useState(post.likeCount);
  const [loveCount, setLoveCount] = useState(post.loveCount);
  const [hahaCount, setHahaCount] = useState(post.hahaCount);

  useEffect(() => {
    // Load the current reaction for the post if it exists
    const loadReaction = async () => {
      try {
        const res = await authApi().get(endpoints['reactions'](post.postID), {
          headers: {
            'Authorization': `${cookie.load('token')}`
          }
        });
        setReaction(res.data.reaction);
      } catch (error) {
        console.error("Failed to load reaction", error);
      }
    };
    loadReaction();
  }, [post.postID]);  

  const handleReaction = async (reactionType) => {
    try {
      if (reaction === reactionType) {
        await authApi().delete(endpoints['reactions'](post.postID), {
          headers: {
            'Authorization': `${cookie.load('token')}`
          }
        });
        setReaction(null);
        // Update the count
        if (reactionType === "Like") setLikeCount(likeCount - 1);
        if (reactionType === "Love") setLoveCount(loveCount - 1);
        if (reactionType === "Haha") setHahaCount(hahaCount - 1);
      } else {
        await authApi().post(endpoints['reactions'](post.postID), { type: reactionType }, {
          headers: {
            'Authorization': `${cookie.load('token')}`
          }
        });
        setReaction(reactionType);
        // Update the count
        if (reactionType === "Like") setLikeCount(likeCount + 1);
        if (reactionType === "Love") setLoveCount(loveCount + 1);
        if (reactionType === "Haha") setHahaCount(hahaCount + 1);
      }
    } catch (error) {
      console.error("Failed to update reaction", error);
    }
  };

  const renderReactionIcon = (reactionType) => {
    switch (reactionType) {
      case "Like":
        return <ThumbUpIcon style={{ color: reaction === "Like" ? "blue" : "inherit" }} />;
      case "Love":
        return <FavoriteIcon style={{ color: reaction === "Love" ? "red" : "inherit" }} />;
      case "Haha":
        return <SentimentVerySatisfiedIcon style={{ color: reaction === "Haha" ? "orange" : "inherit" }} />;
      default:
        return null;
    }
  };

  const handleLockComments = async () => {
    try {
      await authApi().post(`/api/posts/${post.postID}/lock-comments`, {}, {
        headers: {
          'Authorization': `${cookie.load('token')}`
        }
      });
      post.commentsLocked = !post.commentsLocked;
    } catch (error) {
      console.error("Failed to lock comments", error);
    }
  };

  const renderPostContent = () => {
    if (post.postType === "Survey") {
      let surveyOptions = [];
      if (typeof post.surveyOptions === 'string') {
        try {
          surveyOptions = JSON.parse(post.surveyOptions);
        } catch (error) {
          console.error("Failed to parse survey options, using fallback", error);
          surveyOptions = [post.surveyOptions]; // Use as is if parsing fails
        }
      } else {
        surveyOptions = post.surveyOptions;
      }
      return (
        <div className="survey">
          <div className="poll">
            <h3 className="poll__title">{post.content}</h3>
            <p className="poll__question">{post.question}</p> {/* Display the survey question */}
            <div className="poll__options">
              {surveyOptions.map((option, index) => (
                <div className="poll__option" key={index}>
                  {option}
                </div>
              ))}
            </div>
          </div>
        </div>
      );
    } else {
      return (
        <div className="content">
          <p>{post.content}</p>
          {post.image && <img src={post.image} alt="" />}
        </div>
      );
    }
  };

  return (
    <div className="post">
      <div className="container">
        <div className="user">
          <div className="userInfo">
            <img src={post.avatar} alt="" />
            <div className="details">
              <Link
                to={`/profile/${post.userID}`}
                style={{ textDecoration: "none", color: "inherit" }}
              >
                <span className="name">{post.userName}</span>
              </Link>
              <span className="date">{post.createdAt}</span>
            </div>
          </div>
          <MoreHorizIcon />
        </div>
        {renderPostContent()}
        <div className="info">
          <div className="reaction-item" onClick={() => handleReaction("Like")}>
            {renderReactionIcon("Like")}
            <span>{likeCount}</span>
          </div>
          <div className="reaction-item" onClick={() => handleReaction("Love")}>
            {renderReactionIcon("Love")}
            <span>{loveCount}</span>
          </div>
          <div className="reaction-item" onClick={() => handleReaction("Haha")}>
            {renderReactionIcon("Haha")}
            <span>{hahaCount}</span>
          </div>
          <div className="item" onClick={() => setCommentOpen(!commentOpen)}>
            <TextsmsOutlinedIcon />
            {post.commentCount}
          </div>
          <div className="item">
            <ShareOutlinedIcon />
            Share
          </div>
        </div>
        {commentOpen && <Comments postId={post.postID} />}
        {currentUser.userID === post.userID && (
          <button onClick={handleLockComments}>
            {post.commentsLocked ? "Unlock Comments" : "Lock Comments"}
          </button>
        )}
      </div>
    </div>
  );
};

export default Post;
