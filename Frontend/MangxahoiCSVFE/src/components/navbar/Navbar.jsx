import "./navbar.css";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import GridViewOutlinedIcon from "@mui/icons-material/GridViewOutlined";
import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import EmailOutlinedIcon from "@mui/icons-material/EmailOutlined";
import PersonOutlinedIcon from "@mui/icons-material/PersonOutlined";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import { Link, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { logout } from '../../redux/reducers/userActions';

const Navbar = () => {
  const user = useSelector((state) => state.user.user);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    console.log(user);
  }, [user]);

  const handleLogout = () => {
    dispatch(logout());
    navigate("/login");
  };

  return (
    <div className="navbar">
      <div className="left">
        <Link to="/" style={{ textDecoration: "none" }}>
          <span>Alunium</span>
        </Link>
        <Link to="/"><HomeOutlinedIcon /></Link>
        <GridViewOutlinedIcon />
        <div className="search">
          <SearchOutlinedIcon />
          <input type="text" placeholder="Search..." />
        </div>
      </div>
      <div className="right">
        <Link className="profile" to={`/profile/${user?.userID}`}><PersonOutlinedIcon /></Link>
        <Link className="chat"><EmailOutlinedIcon /></Link>
        <NotificationsOutlinedIcon />
        <div className="user">
          <img
            src={user?.avatar}
            alt="avatar_user"
          />
          <span>{user?.name}</span>
        </div>
        <button onClick={handleLogout} className="logout-button">Đăng xuất</button>
      </div>
    </div>
  );
};

export default Navbar;
