import { NavLink, useParams } from "react-router-dom";

const CancelPage = () => {
  const params = useParams();

  return (
    <div className="container text-align-center mt-2 mb-5">
      <h4 className="text-danger">Payment Cancelled</h4>
      <p>
        Your payment was cancelled. Please try again if you wish to place a bid.
      </p>
      <NavLink
        to={`/account/auction/${params.auctionId}/bid`}
        className="btn btn-primary">
        Back to Bidding
      </NavLink>
    </div>
  );
};

export default CancelPage;
