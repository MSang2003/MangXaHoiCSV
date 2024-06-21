import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Container, Form } from "react-bootstrap";
import "./login.css";
import { useDispatch, useSelector } from "react-redux";
import { login } from '../../redux/reducers/userActions';

const Login = () => {
  const current = useSelector((state) => state.user.user);

  const [user, setUser] = useState({});
  const [err, setErr] = useState(null);
  const navigate = useNavigate();
  const dispatch = useDispatch();

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
    setUser(prevState => ({
      ...prevState,
      [field]: e.target.value
    }));
    
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      await dispatch(login(user.username, user.password));
    } catch (err) {
      setErr(err.message);
    }
  };

  useEffect(() => {
    if (current && current.userID) {
      navigate(`/`);
    }
  }, [current, navigate]);

  return (
    <Container>
      <div className="login">
        <div className="card">
          <div className="left">
            <h1>Alunium Website.</h1>
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
