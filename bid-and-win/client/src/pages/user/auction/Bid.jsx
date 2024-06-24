import { useState } from "react";
import { useOutletContext } from "react-router-dom";
import { requestPostOrPut } from "../../../api/request.js";

const paymentUrl = import.meta.env.VITE_API_URL
  ? import.meta.env.VITE_API_URL + "payment"
  : "http://localhost:8080/api/payment";

const rootUrl = import.meta.env.VITE_ROOT_URL;

const Bid = () => {
  const { getAuction } = useOutletContext();

  const [getPayment, setPayment] = useState({
    method: "Paypal",
    amount: "",
    currency: "",
    description: "",
    successUrl: "",
    cancelUrl: "",
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setPayment((prev) => {
      return {
        ...prev,
        [name]: value,
      };
    });
  };

  const handleFormData = (event) => {
    event.preventDefault();
    getPayment.successUrl = `${rootUrl}/account/auction/${getAuction.auctionId}/payment-success?bidAmount=${getPayment.amount}`;
    getPayment.cancelUrl = `${rootUrl}/account/auction/${getAuction.auctionId}/payment-cancel?auctionId=${getAuction.auctionId}`;

    requestPostOrPut(`${paymentUrl}/create`, getPayment, "POST")
      .then((data) => {
        window.location.assign(data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="container text-align-center mt-2 mb-5">
      <h4 className="text-info">
        When you place a bid, we will hold your bid amount until the auction
        ends.
      </h4>
      <div className="row">
        <div className="col-4"></div>
        <div className="col-4 border rounded mt-4 p-3 ">
          <form autoComplete="on" onSubmit={handleFormData} className="form">
            <div className="form-group">
              <label htmlFor="title">Item title</label>
              <input
                type="text"
                name="itemTitle"
                id="title"
                defaultValue={getAuction?.items?.[0].itemTitle}
                className="form-control"
                readOnly
              />
            </div>
            <div className="form-group">
              <label htmlFor="currentPrice">Current Price</label>
              <input
                type="text"
                id="currentPrice"
                defaultValue={getAuction?.items?.[0].currentPrice}
                className="form-control"
                readOnly
              />
            </div>

            <div className="form-group">
              <label htmlFor="p_method">Payment Method</label>
              <input
                type="text"
                name="method"
                defaultValue={"Paypal"}
                className="form-control"
                readOnly
              />
            </div>

            <div className="form-group">
              <label htmlFor="currentPrice">Bid Amount</label>
              <input
                type="text"
                name="amount"
                value={getPayment.amount}
                onChange={handleInputChange}
                className="form-control"
                placeholder="Bid amount"
              />
            </div>

            <div className="form-group">
              <label htmlFor="currency">Currency</label>
              <select
                id="currency"
                name="currency"
                value={getPayment.currency}
                onChange={handleInputChange}
                className="form-control">
                <option value="">Select Currency</option>
                <option value="PLN">PLN</option>
                <option value="USD">USD</option>
                <option value="EUR">EUR</option>
              </select>
            </div>

            <div className="form-group">
              <label htmlFor="desc">Description</label>
              <textarea
                id="desc"
                name="description"
                value={getPayment.description}
                onChange={handleInputChange}
                className="form-control"
              />
            </div>
            <button type="submit" className="btn btn-primary mt-2">
              Bid Now
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Bid;
