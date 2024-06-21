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
  const [selectedOption, setSelectedOption] = useState(null);
  const [showReactions, setShowReactions] = useState(false);

  useEffect(() => {
    // Load the current reaction for the post if it exists
    const loadReaction = async () => {
      try {
        const res = await authApi().get(endpoints['reactions'], {
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
        await authApi().delete(endpoints['reactions'], {
          headers: {
            'Authorization': `${cookie.load('token')}`
          }
        });
        setReaction(null);
      } else {
        await authApi().post(endpoints['reactions'], { type: reactionType }, {
          headers: {
            'Authorization': `${cookie.load('token')}`
          }
        });
        setReaction(reactionType);
      }
    } catch (error) {
      console.error("Failed to update reaction", error);
    }
  };

  const renderReactionIcon = () => {
    switch (reaction) {
      case "Like":
        return <ThumbUpIcon style={{ color: "blue" }} />;
      case "Love":
        return <FavoriteIcon style={{ color: "red" }} />;
      case "Haha":
        return <SentimentVerySatisfiedIcon style={{ color: "orange" }} />;
      default:
        return <ThumbUpIcon />;
    }
  };

  const handleOptionSelect = (option) => {
    setSelectedOption(prevOption => prevOption === option ? null : option);
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
            <div className="poll__options">
              {surveyOptions.map((option, index) => (
                <div className="poll__option" key={index}>
                  <button
                    className={selectedOption === option ? "active" : ""}
                    onClick={() => handleOptionSelect(option)}
                  >
                    {option}
                  </button>
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
          <div
            className="item reaction-item"
            onClick={() => handleReaction("Like")}
            onMouseEnter={() => setShowReactions(true)}
            onMouseLeave={() => setShowReactions(false)}
          >
            {renderReactionIcon()}
            {post.reactionCount}
            {showReactions && (
              <div className="reaction-menu">
                <ThumbUpIcon onClick={() => handleReaction("Like")} style={{ color: reaction === "Like" ? "blue" : "inherit" }} />
                <FavoriteIcon onClick={() => handleReaction("Love")} style={{ color: reaction === "Love" ? "red" : "inherit" }} />
                <SentimentVerySatisfiedIcon onClick={() => handleReaction("Haha")} style={{ color: reaction === "Haha" ? "orange" : "inherit" }} />
              </div>
            )}
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
