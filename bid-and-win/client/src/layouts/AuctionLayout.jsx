import { Outlet, useOutletContext, useParams } from "react-router-dom";
import AuctionHeader from "../components/AuctionHeader.jsx";
import { useEffect, useState } from "react";
import { requestGetOrDelete } from "../api/request.js";

const baseURL = import.meta.env.VITE_API_URL
  ? import.meta.env.VITE_API_URL
  : "http://localhost:8080/api/";
const url = baseURL + "auctions/";

const AuctionLayout = () => {
  const getUserData = useOutletContext();
  const { auctionId } = useParams();
  const [getAuction, setAuction] = useState({});

  const fetchAuctionData = () => {
    requestGetOrDelete(`${url}${auctionId}`, "GET")
      .then((data) => {
        setAuction(data);
      })
      .catch((err) => {
        console.log(err.message);
      });
  };

  useEffect(() => {
    fetchAuctionData();
  }, [auctionId]);

  return (
    <>
      <AuctionHeader />
      <Outlet
        context={{ getAuction, getUserData, refreshAuction: fetchAuctionData }}
      />
    </>
  );
};

export default AuctionLayout;
