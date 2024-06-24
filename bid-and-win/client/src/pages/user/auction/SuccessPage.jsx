import { useEffect, useState } from "react";
import {
  useNavigate,
  useOutletContext,
  useSearchParams,
} from "react-router-dom";
import { requestPostOrPut } from "../../../api/request";

const paymentUrl = import.meta.env.VITE_API_URL
  ? import.meta.env.VITE_API_URL + "payment/success"
  : "http://localhost:8080/api/payment/success";

const SuccessPage = () => {
  const { getAuction, getUserData, refreshAuction } = useOutletContext();
  const [getSearchParams, setSearchParams] = useSearchParams();
  const navigate = useNavigate();

  const [getBid, setBid] = useState({
    bidAmount: "",
    bidTime: new Date().toISOString(),
    itemId: "",
    clientId: "",
    auctionId: "",
    paymentId: "",
    payerId: "",
  });

  const handlePaymentSuccess = (paymentId, payerId, amount) => {
    getBid.paymentId = paymentId;
    getBid.payerId = payerId;
    getBid.bidAmount = amount;
    getBid.itemId = getAuction?.items?.[0].itemId;
    getBid.clientId = getUserData?.clientId;
    getBid.auctionId = getAuction.auctionId;

    requestPostOrPut(paymentUrl, getBid, "POST")
      .then((response) => {
        navigate(`/account/auction/${response.auctionId}/pricing`);
        refreshAuction();
      })
      .catch((error) => {
        console.error("Error sending bid info:", error);
      });
  };

  const handleSuccessRedirect = () => {
    const paymentId = getSearchParams.get("paymentId");
    const payerId = getSearchParams.get("PayerID");
    const bidAmount = getSearchParams.get("bidAmount");

    if (paymentId && payerId) {
      handlePaymentSuccess(paymentId, payerId, bidAmount);
    } else {
       navigate(`/account/auction/${getAuction.auctionId}/pricing`);
    }
  };

    useEffect(() => {
      getAuction?.auctionId && handleSuccessRedirect();
    }, [getAuction.auctionId]);

  return (
    <div className="container">
      <h1>Payment Successful</h1>
      <p>Thank you for your payment</p>
    </div>
  );
};

export default SuccessPage;
