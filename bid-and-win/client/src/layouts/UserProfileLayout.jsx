import { Outlet, useNavigate, useOutletContext } from "react-router-dom";

const UserProfileLayout = () => {
  const userData=useOutletContext();
  const navigate = useNavigate();

  return (
    <div>
      <main className="standard-main2">
      <div className="text-center text-primary go-back-text" //moved style to index.css
          onClick={() => navigate(-1)}>
          go back ...
        </div>
        <br />
        <Outlet
          context={{
            location1: "/account/profile/updateAddressInfo",
            location2: "",
            clientId:userData.clientId,
            userData
          }}
        />
      </main>
    </div>
  );
};

export default UserProfileLayout;
