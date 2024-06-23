import { useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import APIs, { endpoints } from "../../configs/APIs";
import "./register.css";

const Register = () => {
    const fields = [
        {
            label: "Họ và tên",
            type: "text",
            field: "name"
        },
        {
            label: "Email",
            type: "email",
            field: "email"
        },
        {
            label: "Điện thoại",
            type: "text",
            field: "phone"
        },
        {
            label: "Tên đăng nhập",
            type: "text",
            field: "username"
        },
        {
            label: "Mật khẩu",
            type: "password",
            field: "password"
        },
        {
            label: "Mã sinh viên",
            type: "text",
            field: "studentID"
        },
        {
            label: "Ngày sinh",
            type: "date",
            field: "doB"
        },
        {
            label: "Giới tính",
            type: "select",
            field: "sex",
            options: ["Male", "Female"]
        },
        {
            label: "Ảnh đại diện",
            type: "file",
            field: "file"
        }
    ];

    const roles = ["Alumni", "Teacher", "Admin"];

    const [user, setUser] = useState({
        role: "Alumni",
        sex: "Male" // Initialize sex as "Male"
    });
    const nav = useNavigate();
    const avatar = useRef();

    const handleChange = (field, value) => {
        setUser(prevUser => ({
            ...prevUser,
            [field]: value
        }));
    };

    const handleDateChange = date => {
        if (date instanceof Date && !isNaN(date)) {
            const formattedDate = `${("0" + date.getDate()).slice(-2)}-${("0" + (date.getMonth() + 1)).slice(-2)}-${date.getFullYear()}`;
            setUser(prevUser => ({
                ...prevUser,
                doB: formattedDate
            }));
        }
    };

    const register = async (e) => {
        e.preventDefault();
        console.log(user)

        let form = new FormData();
        for (let key in user) {
            if (key !== 'confirm') {
                form.append(key, user[key]);
            }
        }

        if (avatar.current.files.length > 0) {
            form.append('avatar', avatar.current.files[0]);
        }

        try {
            let res = await APIs.post(endpoints['register'], form, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            if (res.status === 201) {
                nav("/login");
            }
        } catch (ex) {
            console.error(ex);
        }
    };


//     const SignUpFB= async()=>{
// // const userCredential = await createUserWithEmailAndPassword(auth, "msang.nms@gmail.com", "msang.nms@gmail.com");

//     const userCredential = await signInWithEmailAndPassword(auth, "msang.nms@gmail.com", "msang.nms@gmail.com");
//     console.log(userCredential.user)
//     }



    return (
        <div className="register">
            <div className="card">
                <div className="left">
                    <h1>Alumni Social.</h1>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Libero cum, alias totam numquam ipsa exercitationem dignissimos, error nam, consequatur.</p>
                    <span>Do you have an account?</span>
                    <Link to="/login">
                        <button>Login</button>
                    </Link>
                </div>
                <div className="right">
                    <h1>Register</h1>
                    <form onSubmit={register}>
                        {fields.map(f => (
                            <div key={f.field} className="form-group">
                                <label>{f.label}</label>
                                {f.type === "date" ? (
                                    <DatePicker
                                        selected={user.doB ? new Date(user.doB.split('-').reverse().join('-')) : null}
                                        onChange={date => handleDateChange(date)}
                                        dateFormat="dd-MM-yyyy"
                                        placeholderText={f.label}
                                        calendarClassName="custom-calendar"
                                    />
                                ) : f.type === "file" ? (
                                    <input
                                        type="file"
                                        accept=".png,.jpg"
                                        ref={avatar}
                                        onChange={(e) => handleChange(f.field, e.target.files[0])}
                                    />
                                ) : f.type === "select" && f.field === "sex" ? (
                                    <select onChange={e => handleChange("sex", e.target.value)} value={user.sex || ""}>
                                        {f.options.map(option => (
                                            <option key={option} value={option}>{option}</option>
                                        ))}
                                    </select>
                                ) : (
                                    <input
                                        onChange={e => handleChange(f.field, e.target.value)}
                                        value={user[f.field] || ""}
                                        type={f.type}
                                        placeholder={f.label}
                                    />
                                )}
                            </div>
                        ))}
                        <div className="form-group">
                            <label>Vai trò</label>
                            <select onChange={e => handleChange("role", e.target.value)} value={user.role || "Alumni"}>
                                {roles.map(role => (
                                    <option key={role} value={role}>{role}</option>
                                ))}
                            </select>
                        </div>
                        <button type="submit">Register</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Register;
