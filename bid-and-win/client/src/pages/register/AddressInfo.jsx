import { useNavigate, useLocation, useOutletContext } from "react-router-dom";
import { validateAddress } from "../../util/clientSideValidation";
import { useEffect, useState } from "react";
import { requestGetOrDelete, requestPostOrPut } from "../../api/request.js";

const baseURL = import.meta.env.VITE_API_URL
  ? import.meta.env.VITE_API_URL
  : "http://localhost:8080/api/";
const url = baseURL + "address/";

const AddressInfo = (props) => {
  const { submitButtonValue, httpMethod } = props;
  const { location2, clientId } = useOutletContext();

  const [getAddress, setAddress] = useState({
    country: "",
    city: "",
    zipCode: "",
    street: "",
    aptOrHouseNumber: "",
  });

  useEffect(() => {
    clientId &&
      requestGetOrDelete(
        import.meta.env.VITE_API_URL + `address/${clientId}` ||
          `http://localhost:8080/api/address/${clientId}`,
        "GET"
      )
        .then((response) => {
          console.log("response", response);
          setAddress(response);
        })
        .catch((error) => {
          console.error("Error fetching client data", error);
        });
  }, [clientId]);

  const navigate = useNavigate();
  const [getErrorMessage, setErrorMessage] = useState("");

  const location = useLocation();
  const personalInfo = location.state?.client && location.state.client;
  
  const handleClientInfo = (event) => {
    const { name, value } = event.target;
    setAddress((prevValue) => {
      return {
        ...prevValue,
        [name]: value,
      };
    });
  };

  const handleForm = async (event) => {
    event.preventDefault();
    try {
      validateAddress(getAddress);
      const result = await requestPostOrPut(
        `${url}${clientId || personalInfo.clientId}`,
        getAddress,
        httpMethod
      );
      result.message == "CREATED" &&
        location2 &&
        navigate(`${location2}`, {
          state: {
            client: { ...personalInfo },
          },
        });
    } catch (error) {
      setErrorMessage(error);
    }
  };

  return (
    <main className="container mb-2 pb-2">
      <form onSubmit={handleForm}>
        <fieldset className="border p-4 rounded mb-4">
          <legend className="w-auto">Address Information</legend>

          <div className="mb-3">
            <label htmlFor="country" className="form-label">
              Country
            </label>
            <input
              type="text"
              id="country"
              name="country"
              className="form-control"
              value={getAddress.country}
              onChange={handleClientInfo}
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="city" className="form-label">
              City
            </label>
            <input
              type="text"
              id="city"
              name="city"
              className="form-control"
              value={getAddress.city}
              onChange={handleClientInfo}
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="zip" className="form-label">
              Zip Code
            </label>
            <input
              type="text"
              id="zip"
              name="zipCode"
              className="form-control"
              value={getAddress.zipCode}
              onChange={handleClientInfo}
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="street" className="form-label">
              Street
            </label>
            <input
              type="text"
              id="street"
              name="street"
              className="form-control"
              value={getAddress.street}
              onChange={handleClientInfo}
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="aptOrHouseNo" className="form-label">
              House / Apt. NO
            </label>
            <input
              type="text"
              id="aptOrHouseNo"
              name="aptOrHouseNumber"
              className="form-control"
              value={getAddress.aptOrHouseNumber}
              onChange={handleClientInfo}
              required
            />
          </div>
          <div className="text-center">
            {" "}
            {/* style={{ textAlign: "center" }} */}
            <button type="submit" className="btn btn-primary">
              {submitButtonValue}
            </button>
          </div>
        </fieldset>
        {getErrorMessage && (
          <div className="alert alert-danger">{getErrorMessage.message}</div>
        )}
      </form>
    </main>
  );
};

export default AddressInfo;
