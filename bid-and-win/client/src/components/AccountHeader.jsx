import { NavLink } from "react-router-dom";
import userIcon from "../assets/userIcon.png";
import { useState } from "react";
import logo from "../assets/logo.png";

const AccountHeader = (props) => {
  const { userData } = props;

  const [getVisibility, setVisibility] = useState(false);
  return (
    <header className="container p-2 mb-4 border-bottom">
      <div className="d-flex">
        <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 centered-navbar">
          <li>
            <NavLink to="/" className="nav-link px-2">
              <img src={logo} alt="Logo" className="logo-image" width={100} height={100}/>
            </NavLink>
          </li>
          <li>
            <NavLink to="/" className="nav-link px-1">
              <h3 className="nav-heading">#BidAndWin</h3>
            </NavLink>
          </li>
          <li>
            <NavLink to="/account" className="nav-link px-1 nav-link-dashboard">
              <h3 className="nav-heading">Dashboard</h3>
            </NavLink>
          </li>
          <li>
            <NavLink
              to="/account/auctions"
              className="nav-link px-1 nav-link-auctions">
              <h3 className="nav-heading">Your Items</h3>
            </NavLink>
          </li>
          <li>
            <NavLink
              to="newAuction"
              className="nav-link px-1 nav-link-create-auction">
              <h3 className="nav-heading">Create Auction</h3>
            </NavLink>
          </li>
          <li className="mt-3" style={{marginLeft:"60px"}}>
            <div className="dropdown">
              <div
                className="dropdown-toggle "
                data-bs-toggle="dropdown"
                aria-expanded="false"
                onClick={() => setVisibility(!getVisibility)}>
                <img src={userIcon} alt="User" className="user-icon" width={40}/>
              </div>

              <ul style={{ visibility: getVisibility ? "visible" : "hidden" }}>
                <li>
                  <NavLink className="dropdown-item" to="/account/profile">
                    Profile
                  </NavLink>
                </li>
                <li>
                  <NavLink className="dropdown-item" to="/account/profile">
                    Settings
                  </NavLink>
                </li>
                <li>
                  <NavLink className="dropdown-item" to="/login">
                    Sign out
                  </NavLink>
                </li>
              </ul>
            </div>
          </li>

        </ul>
      </div>
    </header>
  );
};

export default AccountHeader;
