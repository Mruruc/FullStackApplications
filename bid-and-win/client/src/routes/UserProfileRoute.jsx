import UserProfile from "../pages/user/UserProfile.jsx";
import PersonalInfo from "../pages/register/PersonalInfo.jsx";
import AddressInfo from "../pages/register/AddressInfo.jsx";
import UserProfileLayout from "../layouts/UserProfileLayout.jsx";


const userProfileRoute = {
  path: "profile",
  element: <UserProfileLayout />,
  children: [
    {
      index: true,
      element: <UserProfile />,
    },
    {
      path: "updatePersonalInfo",
      element: <PersonalInfo submitButtonValue={"Update"} httpMethod={"PUT"}/>,
    },
    {
      path: "updateAddressInfo",
      element: <AddressInfo submitButtonValue={"Update"} httpMethod="PUT"/>,
    },
  ],
};

export default userProfileRoute;
