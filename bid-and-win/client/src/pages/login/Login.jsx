import { useState } from "react";
import { NavLink, useNavigate, useLocation } from "react-router-dom"; //added useLocation for route protection
import { requestPostOrPut } from "../../api/request.js";

const baseURL = import.meta.env.VITE_API_URL ? import.meta.env.VITE_API_URL : "http://localhost:8080/api/";
const url = baseURL + 'users/login';

const Login = () => {
  const navigate=useNavigate();
  const [getUser, setUser] = useState({ userName: "", password: "" });

  const location = useLocation();

  const handleChange = (event) => {
    const { name, value } = event.target;
    setUser((prevValue) => {
      return {
        ...prevValue,
        [name]: value,
      };
    });
  };


  const handleFormData = (event) => {
    event.preventDefault();
    requestPostOrPut(url, getUser, "POST")
      .then((userData) => {
        navigate(`/account?userId=${userData.userId}`); 
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <main className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6 mt-5">
          <div className="card shadow-lg">
            <div className="card-body">
              <form onSubmit={handleFormData} autoComplete="on">
                <div className="mb-3">
                  <label htmlFor="userName" className="form-label">
                    User Name
                  </label>
                  <input
                    type="text"
                    id="userName"
                    name="userName"
                    value={getUser.userName}
                    onChange={handleChange}
                    className="form-control"
                    required
                  />
                </div>

                <div className="mb-3">
                  <label htmlFor="password" className="form-label">
                    Password
                  </label>
                  <input
                    type="password"
                    id="password"
                    name="password"
                    value={getUser.password}
                    onChange={handleChange}
                    className="form-control"
                    required
                  />
                </div>

                <button type="submit" className="btn btn-primary">
                  Login
                </button>
              </form>
            </div>
            <div className="card-footer text-center">
              <NavLink to="/register" className="text-decoration-none">
                Do not have an account? Register here
              </NavLink>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
};

export default Login;
