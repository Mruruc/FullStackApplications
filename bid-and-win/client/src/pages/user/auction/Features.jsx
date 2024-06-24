import { useOutletContext } from "react-router-dom";
import { timeLeft } from "../../../util/util.js"; 


const Features = () => {
  const { getAuction } = useOutletContext();
  
  return (
    <div className="features-container">
      <h3>Features</h3>
      <p>Auction Status: {getAuction.auctionStatus}</p>
      <p>Auction Start Time: {getAuction.auctionStartTime}</p>
      <p>Auction End Time: {getAuction.auctionEndTime}</p>
      <p>Time To End: {timeLeft(getAuction.auctionEndTime)}</p>
    </div>
  );
}

export default Features;
