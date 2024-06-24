import {
  NavLink,
  useNavigate,
  useOutletContext,
} from "react-router-dom";
import { requestGetOrDelete } from "../../api/request.js";

const UserProfile = () => {
  const  {userData}  = useOutletContext();

  const navigate = useNavigate();

  const handleCloseAccount = () => {
    if (window.confirm("Are you sure you want to close your account? This action cannot be undone.")){

      requestGetOrDelete(
        `http://localhost:8080/api/clients/${userData.clientId}`,
        "DELETE"
      )
        .then((data) => {
          localStorage.removeItem("getUserData");
          navigate("/");
          
        })
        .catch((error) => {
          console.error("Error closing account", error);
        });
    }
  };
  

  return (
    <main className="standard-main1">
      <h1>Update Your Info</h1>
      <div className="update-options"> {/* Moved inline styles to CSS class */}
        <NavLink
          className="nav-link-custom"
          to="/account/profile/updatePersonalInfo">
          Update Personal Data
        </NavLink>

        <NavLink
          className="nav-link-custom"
          to="/account/profile/updateAddressInfo">
          Update Address Data
        </NavLink>
      </div>

      <div className="danger-zone">
        <p>Danger Zone</p>
        <button
          type="button"
          className="btn btn-danger"
          onClick={handleCloseAccount}>
          Close Your Account
        </button>
      </div>
    </main>
  );
};

export default UserProfile;
