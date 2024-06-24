import { useOutletContext } from "react-router-dom";

const Pricing = () => {
  const { getAuction } = useOutletContext();
  return (
    <div className="pricing-container mb-5"> 
      <h1>Pricing page</h1>  
      <p>Start Price: {getAuction?.items?.[0].currentPrice}</p>
      <p>Current Price: {getAuction?.items?.[0].currentPrice}</p>

      {getAuction?.bids?.length > 0 && (
        <table className="table mb-4">
        <thead>
          <tr>
             <td>Bidder</td>
             <td>Bid Amount</td>
            <td>Time</td>
          </tr>
        </thead>
        <tbody>
          {
           getAuction?.bids?.map((bid)=>(
            <tr key={bid.bidId}>
              <td>Bider</td>
              <td>{bid.bidAmount}</td>
              <td>{bid.bidTime}</td>
            </tr>
           ))
          }
        </tbody>
      </table>
      )}

    </div>
  );
};

export default Pricing;
