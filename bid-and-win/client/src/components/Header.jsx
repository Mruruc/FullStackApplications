import { NavLink } from "react-router-dom";
import logo from '../assets/logo.png'; // Adjusted path to the logo

const Header = () => {
  return (
      <header className="container d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-1 mb-2 border-bottom">
        <h1>
          {/* Replaced inline styles with CSS classes */}
          <NavLink to="/" className="logo-link">
            <img src={logo} alt="Logo" className="logo-img" />
            #BidAndWin
          </NavLink>
        </h1>

        <ul className="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
          <li>
            <NavLink to="/" className="nav-link px-2 link-secondary">
              Home
            </NavLink>
          </li>
          <li>
            <NavLink to='/features' className="nav-link px-2 link-dark">Features</NavLink>
          </li>
          <li>
            <NavLink to="/products" className="nav-link px-2 link-dark">
              Products
            </NavLink>
          </li>
          <li>
            <NavLink to="faq" className="nav-link px-2 link-dark">
              FAQs
            </NavLink>
          </li>
          <li>
            <NavLink to="/about" className="nav-link px-2 link-dark">
              About
            </NavLink>
          </li>
        </ul>

        <div>
          <NavLink to="/login">
            <button type="button" className="btn btn-outline-primary me-2">
              Login
            </button>
          </NavLink>

          <NavLink to="/register">
            <button type="button" className="btn btn-primary">
              Sign-up
            </button>
          </NavLink>
        </div>
      </header>
  );
};

export default Header;
