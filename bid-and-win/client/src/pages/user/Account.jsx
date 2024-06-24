import { useState, useEffect } from "react";
import { useOutletContext, NavLink } from "react-router-dom";
import { requestGetOrDelete } from "../../api/request";
import { extractUsernameFromEmail } from "../../util/util";

const baseURL = import.meta.env.VITE_API_URL ? import.meta.env.VITE_API_URL: "http://localhost:8080/api/";
const url = baseURL + "auctions";
const Account = () => {
  const getUserData = useOutletContext();
  const [getAuctions, setAuctions] = useState([]);

  useEffect(() => {
    requestGetOrDelete(url, "GET")
      .then((data) => {
        setAuctions(data);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);

  return (
    <main className="standard-main">
      <h1 className="createAccountForm">Welcome {getUserData.userName &&  extractUsernameFromEmail(getUserData.userName)}</h1>
      <div className="card-container">

        {getAuctions.map((auction) => (
          <div className="card" key={auction.auctionId}>
            <NavLink to={`/account/auction/${auction.auctionId}`}>
              <div className="card-body">
                
                <div>
                  {
                    auction.items.map((item)=>(
                     <div key={item.itemId}>
                      <div>{item.itemTitle}</div>
                      <div>{item.currentPrice}</div>
                     </div>
                    ))
                  }
                </div>
                <div>{auction.auctionStatus}</div>
              </div>
            </NavLink>
          </div>
        ))}

      </div>
    </main>
  );
};

export default Account;