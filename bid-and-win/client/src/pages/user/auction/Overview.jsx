import { useOutletContext } from "react-router-dom";
import { handleDownload } from "../../../util/util.js";

const baseurl = import.meta.env.VITE_API_URL
  ? import.meta.env.VITE_API_URL + "items/info/"
  : "http://localhost:8080/api/items/info/";

const Overview = () => {
  const { getAuction } = useOutletContext();

  const itemId = getAuction?.items?.[0]?.itemId;

  const callDownloadHandler =  () => {
    handleDownload(getAuction)
  };
  

  return (
    <div className="overview-container">
      <div className="header-container">
        <h3>Overview:</h3>
        <button onClick={callDownloadHandler} className="download-button">
          Download Item's Information
        </button>
      </div>
      <p>Title: {getAuction?.items?.[0]?.itemTitle}</p>
      <p>Start Price: {getAuction?.items?.[0]?.startingPrice}</p>
      <p>Status: {getAuction.auctionStatus}</p>
      <p>End Date: {getAuction.auctionEndTime}</p>
    </div>
  );
};

export default Overview;
