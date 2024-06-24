import { NavLink, useRouteError } from "react-router-dom";

const NotFound = () => {
  const error = useRouteError();

  return (
    <main>
      {error.status === 404 ? (
        <div className="d-flex align-items-center justify-content-center vh-100">
          <div className="text-center">
            <h1 className="display-1 fw-bold">404</h1>
            <p className="fs-3">
              <span className="text-danger">Opps!</span> Page not found.
            </p>
            <p className="lead">The page you're looking for doesn't exist.</p>
            <NavLink to="/" className="btn btn-primary">
              Go Home
            </NavLink>
          </div>
        </div>
      ) : (
        <div className="standard-main">
          <h1> Something went wrong....</h1>
          <p>
            <NavLink to="/">back to home...</NavLink>
          </p>
        </div>
      )}
    </main>
  );
};

export default NotFound;

/** in case will be needed later on
 *  <main>
      {error.status === 404 ? (
        <div className="d-flex align-items-center justify-content-center vh-100">
          <div className="text-center">
            <h1 className="display-1 fw-bold">404</h1>
            <p className="fs-3">
              <span className="text-danger">Opps!</span> Page not found.
            </p>
            <p className="lead">The page you're looking for doesn't exist.</p>
            <NavLink to="/" className="btn btn-primary">
              Go Home
            </NavLink>
          </div>
        </div>
      ) : (
        <div className="standard-main">
          <h1> Something went wrong....</h1>
          <p>
            <NavLink to="/">back to home...</NavLink>
          </p>
        </div>
      )}
    </main>
 */