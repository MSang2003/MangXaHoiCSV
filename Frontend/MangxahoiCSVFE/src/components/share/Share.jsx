import "./share.css";
import Image from "../../assets/img.png";
import Map from "../../assets/map.png";
import Friend from "../../assets/friend.png";
import Poll from "../../assets/poll.png";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import cookie from "react-cookies";
import APIs, { authApi, endpoints } from "../../configs/APIs";
import Modal from "react-modal";

const Share = () => {
  const currentUser = useSelector((state) => state.user.user);
  const [content, setContent] = useState("");
  const [file, setFile] = useState(null);
  const [postType, setPostType] = useState("Status");
  const [surveyContent, setSurveyContent] = useState("");
  const [surveyOptions, setSurveyOptions] = useState(["", ""]); // Start with two empty options
  const [isPollModalOpen, setIsPollModalOpen] = useState(false);
  const dispatch = useDispatch();
  
  const handleAddOption = () => {
    setSurveyOptions([...surveyOptions, ""]);
  };

  const handleOptionChange = (index, value) => {
    const newOptions = [...surveyOptions];
    newOptions[index] = value;
    setSurveyOptions(newOptions);
  };
 

  const handleSubmit = async (e) => {
    console.log(file)
    e.preventDefault();

    const formData = new FormData();
    formData.append("content", postType === "Survey" ? surveyContent : content);
    formData.append("userID", currentUser.userID);
    formData.append("postType", postType);

    if (file) {
      formData.append("file", file);
    }

    if (postType === "Survey") {
      formData.append("surveyOptions", JSON.stringify(surveyOptions));
    }

    try {
      await authApi().post(endpoints['posts'], formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Authorization': `${cookie.load('token')}`
        }
      });
      setContent("");
      setFile(null);
      setSurveyContent("");
      setSurveyOptions(["", ""]);
      setIsPollModalOpen(false);
    } catch (error) {
      console.error("Failed to share post", error);
    }
  };

  const openPollModal = () => {
    setIsPollModalOpen(true);
  };

  const closePollModal = () => {
    setIsPollModalOpen(false);
  };

  return (
    <div className="share">
      <div className="container">
        <div className="top">
          <img src={currentUser?.avatar} alt="anh dai dien" />
          <input
            type="text"
            placeholder={`What's on your mind ${currentUser?.name}?`}
            value={content}
            onChange={(e) => setContent(e.target.value)}
          />
        </div>
        <hr />
        <form className="bottom" onSubmit={handleSubmit}>
          <div className="left">
            <input
              type="file"
              id="file"
              style={{ display: "none" }}
              onChange={(e) => setFile(e.target.files[0])}
            />
            <label htmlFor="file">
              <div className="item">
                <img src={Image} alt="" />
                <span>Add Image</span>
              </div>
            </label>
            <div className="item">
              <img src={Map} alt="" />
              <span>Add Place</span>
            </div>
            <div className="item">
              <img src={Friend} alt="" />
              <span>Tag Friends</span>
            </div>
            <div className="item" onClick={openPollModal}>
              <img src={Poll} alt="" />
              <span>Poll</span>
            </div>
          </div>
          <div className="right">
            <select value={postType} onChange={(e) => setPostType(e.target.value)}>
              <option value="Status">Status</option>
              <option value="Invitation">Invitation</option>
            </select>
            <button type="submit">Share</button>
          </div>
        </form>
      </div>
      <Modal
        isOpen={isPollModalOpen}
        onRequestClose={closePollModal}
        className="pollModal"
        overlayClassName="pollModalOverlay"
      >
        <h2>Create a Poll</h2>
        <textarea
          className="pollContent"
          placeholder="What's your poll about?"
          value={surveyContent}
          onChange={(e) => setSurveyContent(e.target.value)}
        />
        <div className="surveyOptionsContainer">
          {surveyOptions.map((option, index) => (
            <input
              key={index}
              type="text"
              value={option}
              placeholder={`Option ${index + 1}`}
              onChange={(e) => handleOptionChange(index, e.target.value)}
            />
          ))}
          <button type="button" onClick={handleAddOption}>Add Option</button>
        </div>
        <button type="button" onClick={handleSubmit}>Share Poll</button>
      </Modal>
    </div>
  );
};

export default Share;
