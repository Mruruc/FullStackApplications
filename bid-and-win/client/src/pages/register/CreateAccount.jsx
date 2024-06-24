import { useState } from "react";
import { useLocation, useNavigate, useSearchParams } from "react-router-dom";
import { requestPostOrPut } from "../../api/request";

const baseURL = import.meta.env.VITE_API_URL
  ? import.meta.env.VITE_API_URL
  : "http://localhost:8080/api/";
const url = baseURL + "users/";

const CreateAccount = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const clientData = location.state?.client && location.state.client;

  const [getUser, setUser] = useState({
    userName: clientData.email,
    clientId: clientData.clientId,
    password: "",
    confirmPassword: "",
  });


  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser((prevValue) => {
      return {
        ...prevValue,
        [name]: value,
      };
    });
  };

  const handleForm = (e) => {
    e.preventDefault();

    requestPostOrPut(
     `${url}${clientData.clientId}`,
      {
        userName: getUser.userName,
        password: getUser.password,
        clientId: getUser.clientId
      },
      "POST"
    )
      .then((data) => {
        navigate(`/account?userId=${data.userId}`);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <main className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <h5 className="card-header">Create Your Account</h5>
            <div className="card-body">
              <form onSubmit={handleForm}>
                <div className="mb-3">
                  <label htmlFor="username" className="form-label">
                    Username
                  </label>
                  <input
                    type="text"
                    id="username"
                    name="username"
                    className="form-control"
                    value={getUser.userName}
                    readOnly
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
                    className="form-control"
                    value={getUser.password}
                    onChange={handleChange}
                    required
                  />
                </div>

                <div className="mb-3">
                  <label htmlFor="confirm_password" className="form-label">
                    Confirm Password
                  </label>
                  <input
                    type="password"
                    id="confirm_password"
                    name="confirmPassword"
                    className="form-control"
                    value={getUser.confirmPassword}
                    onChange={handleChange}
                    required
                  />
                </div>

                <button type="submit" className="btn btn-primary">
                  Create Account
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
};

export default CreateAccount;
