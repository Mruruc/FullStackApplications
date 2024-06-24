import HomePageLayout from "../layouts/HomePageLayout.jsx";
import CreateAccount from "../pages/register/CreateAccount.jsx";
import Login from "../pages/login/Login.jsx";
import About from "../pages/About.jsx";
import NotFound from "../pages/NotFound.jsx";
import RegisterLayout from "../layouts/RegisterLayout.jsx";
import PersonalInfo from "../pages/register/PersonalInfo.jsx";
import AddressInfo from "../pages/register/AddressInfo.jsx";

const homePageRoute = {
  path: "/",
  element: <HomePageLayout />,
  errorElement: <NotFound />,
  children: [
    {
      path: "about",
      element: <About />,
    },
    {
      path: "register",
      element: <RegisterLayout />,
      children: [
        {
          index: true,
          element: <PersonalInfo submitButtonValue={"Next"} httpMethod={"POST"}/>,
        },
        {
          path: "addressInfo",
          element: <AddressInfo submitButtonValue={"register"} httpMethod="POST"/>,
        },
        {
          path: "createAccount",
          element: <CreateAccount />,
        },
      ],
    },
    {
      path: "login",
      element: <Login />,
    },
  ],
};

export default homePageRoute;
