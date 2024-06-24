import { Outlet, NavLink } from "react-router-dom";

const RegisterLayout = () => {
  return (
    <main className="container">
      <div className="row justify-content-center">
        <div className="col-md-8 mt-0">
          <div className="card">
            <h5 className="card-header">Register</h5>

            <NavLink to="/login" className="float-end text-primary">
              Already have an Account?
            </NavLink>
            
            <div className="card-body">
              <Outlet
                context={{
                  location1: "/register/addressInfo",
                  location2: "/register/createAccount",
                }}
              />
            </div>
          </div>
        </div>
      </div>
    </main>
  );
};

export default RegisterLayout;
