import { useEffect, useState } from "react";
import { validateClient } from "../../util/clientSideValidation.js";
import { useNavigate, useOutletContext } from "react-router-dom";
import { requestGetOrDelete, requestPostOrPut } from "../../api/request.js";

const baseURL = import.meta.env.VITE_API_URL ? import.meta.env.VITE_API_URL : "http://localhost:8080/api/";
const url = baseURL + 'clients';

const PersonalInfo = (props) => {
  const { submitButtonValue,httpMethod } = props;

  const { location1, clientId } = useOutletContext();
  const [getClient, setClient] = useState({
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    gender: "",
    email: "",
    phone: "",
  });

  useEffect(() => {
    clientId &&
      requestGetOrDelete(import.meta.env.VITE_API_URL+`clients/${clientId}` || `http://localhost:8080/api/clients/${clientId}`, "GET")
        .then((response) => {
          setClient(response);
        })
        .catch((error) => {
          console.error("Error fetching client data", error);
        });
  }, [clientId]);

  const navigate = useNavigate();
  const [getErrorMessage, setErrorMessage] = useState("");

  const handleClientInfo = (event) => {
    const { name, value } = event.target;
    setClient((prevValue) => {
      return {
        ...prevValue,
        [name]: value,
      };
    });
  };

  const handleForm = (event) => {
    event.preventDefault();

    try {
      validateClient(getClient);
       requestPostOrPut(url, getClient, httpMethod)
        .then((response) => {
          console.log("response", response);
          navigate(`${location1}`, { state: { client: { ...response } } });
        })
        .catch((error) => {
          setErrorMessage(error.message);
        });
    } catch (error) {
      setErrorMessage(error.message);
    }
  };

  return (
    <main className="container mb-2 pb-2">
      <form onSubmit={handleForm} autoComplete="on">
        <fieldset className="border p-4 rounded mb-4">
          <legend className="w-auto">Personnel Information</legend>

          <div className="mb-3">
            <label htmlFor="f_name" className="form-label">
              First Name
            </label>
            <input
              type="text"
              id="f_name"
              name="firstName"
              className="form-control"
              value={getClient.firstName}
              onChange={handleClientInfo}
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="l_name" className="form-label">
              Last Name
            </label>
            <input
              type="text"
              id="l_name"
              name="lastName"
              className="form-control"
              value={getClient.lastName}
              onChange={handleClientInfo}
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="dob" className="form-label">
              Date Of Birth
            </label>
            <input
              type="date"
              id="dob"
              name="dateOfBirth"
              className="form-control"
              value={getClient.dateOfBirth}
              onChange={handleClientInfo}
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="gender" className="form-label">
              Gender
            </label>
            <select
              id="gender"
              name="gender"
              className="form-select"
              value={getClient.gender}
              onChange={handleClientInfo}
              required>
              <option value="" disabled>
                Select your gender
              </option>
              <option value="FEMALE">Female</option>
              <option value="MALE">Male</option>
              <option value="OTHERS">Other</option>
            </select>
          </div>

          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Email Address
            </label>
            <input
              type="email"
              id="email"
              name="email"
              className="form-control"
              value={getClient.email}
              onChange={handleClientInfo}
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="phone" className="form-label">
              Phone Number
            </label>
            <input
              type="phone"
              id="phone"
              name="phone"
              className="form-control"
              value={getClient.phone}
              onChange={handleClientInfo}
              required
            />
          </div>
          <div className="text-center">
            <input
              type="submit"
              className="btn btn-primary"
              value={submitButtonValue}
            />
          </div>
        </fieldset>
        {getErrorMessage && (
          <div className="alert alert-danger">{getErrorMessage}</div>
        )}
      </form>
    </main>
  );
};

export default PersonalInfo;
