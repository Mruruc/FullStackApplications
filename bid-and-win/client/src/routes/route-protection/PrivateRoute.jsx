import { Navigate, useLocation } from 'react-router-dom';
import PropTypes from 'prop-types';

const PrivateRoute = ({ children }) => {
  const isAuthenticated = localStorage.getItem("getUserData"); // Check if user data is in localStorage
  const location = useLocation();

  console.log("isAuthenticated:", isAuthenticated); // Add console log to check authentication status

  if (!isAuthenticated) {
    console.log("User not authenticated, redirecting to login"); // Add console log for redirection
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  console.log("User authenticated, rendering children"); // Add console log for rendering children
  return children;
};

PrivateRoute.propTypes = {
  children: PropTypes.node.isRequired,
};

export default PrivateRoute;
