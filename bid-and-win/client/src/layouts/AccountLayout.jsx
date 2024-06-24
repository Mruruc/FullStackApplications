import { Outlet, useLocation, useSearchParams } from "react-router-dom";
import Footer from "../components/Footer";
import AccountHeader from "../components/AccountHeader";
import { useEffect, useState } from "react";
import { requestGetOrDelete } from "../api/request";

const baseURL = import.meta.env.VITE_API_URL ? import.meta.env.VITE_API_URL: "http://localhost:8080/api/";
const url = baseURL + "users/";

const AccountLayout = () => {
  const [getSearchParams, setSearchParams] = useSearchParams();
  const [getUserData, setUserData] = useState({});
  const userId = getSearchParams.get("userId");

  useEffect(() => {
    if (userId) {
      const userData=JSON.parse(localStorage.getItem("getUserData"));

      if(userData?.userId === parseInt(userId)){
        setUserData(userData);
      }
      else{
        localStorage.removeItem("getUserData");
        requestGetOrDelete(`${url}${userId}`, "GET")
        .then((data) => {
          if (localStorage.getItem("getUserData")) {
            setUserData(JSON.parse(localStorage.getItem("getUserData")));
          } else {
            localStorage.setItem("getUserData", JSON.stringify(data));
            setUserData(data);
          }
        })
        .catch((err) => {
          console.error("Failed to fetch user data:", err);
        });
      }
    } else {
      localStorage.getItem("getUserData")
        ? setUserData(JSON.parse(localStorage.getItem("getUserData")))
        : console.log("No client ID found, defaulting to guest user");
    }
  }, [userId]);

  return (
    <>
      {getUserData ? (
        <>
          <AccountHeader userData={getUserData} />
          <Outlet context={getUserData} />
          <Footer />
        </>
      ) : (
        <p> Loading...</p>
      )}
    </>
  );
};

export default AccountLayout;
