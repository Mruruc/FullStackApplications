import { useOutletContext } from "react-router-dom";
import { useEffect, useState } from "react";
import { requestGetOrDelete } from "../../api/request";

const Auctions = () => {
  const contextData = useOutletContext();
  const [getItem, setItem] = useState([]);

  useEffect(() => {
    contextData?.clientId && requestGetOrDelete(
      `http://localhost:8080/api/items/client/${contextData.clientId}`,
      "GET"
    )
      .then((data) => {
        setItem(data);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);

  return (
    <div className="card-container">
      {getItem.map((auction) => {
        return (
          <div className="card" key={auction.itemId}>
            <div className="card-body">
              <div>Item Title: {auction.itemTitle}</div>
              <div className="mt-1">Item Price: {auction.currentPrice}</div>
              <div className="mt-1">
                Item Description:  {auction.itemDescription}
                </div>
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default Auctions;
