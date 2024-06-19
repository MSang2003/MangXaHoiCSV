import { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/authContext";
import { Button, Container, Form } from "react-bootstrap";
import "./login.css";

const Login = () => {
  const { login } = useContext(AuthContext);
  const [user, setUser] = useState({});
  const [err, setErr] = useState(null);
  const navigate = useNavigate();

  const fields = [
    {
      label: "Tên đăng nhập",
      type: "text",
      field: "username"
    },
    {
      label: "Mật khẩu",
      type: "password",
      field: "password"
    }
  ];

  const change = (e, field) => {
    setUser(current => {
      return { ...current, [field]: e.target.value }
    });
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const loggedInUser = await login(user);
      console.log(loggedInUser);
      if (loggedInUser) {
        navigate("/profile/1");
      }
    } catch (err) {
      setErr(err.message);  // Hiển thị thông báo lỗi nếu có
    }
  };

  return (
    <Container>
      <div className="login">
        <div className="card">
          <div className="left">
            <h1>Hello World.</h1>
            <p>
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Libero cum,
              alias totam numquam ipsa exercitationem dignissimos, error nam,
              consequatur.
            </p>
            <span>Don't you have an account?</span>
            <Link to="/register">
              <button>Register</button>
            </Link>
          </div>
          <div className="right">
            <h1>Login</h1>
            <Form onSubmit={handleLogin}>
              {fields.map(f => (
                <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                  <Form.Label>{f.label}</Form.Label>
                  <Form.Control
                    onChange={e => change(e, f.field)}
                    value={user[f.field] || ""}
                    type={f.type}
                    placeholder={f.label}
                  />
                </Form.Group>
              ))}
              {err && <span>{err}</span>}
              <Form.Group className="mb-3">
                <Button type="submit" variant="primary">Đăng nhập</Button>
              </Form.Group>
            </Form>
          </div>
        </div>
      </div>
    </Container>
  );
};

export default Login;
