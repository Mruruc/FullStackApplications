import { useState } from "react";
import { useNavigate, useOutletContext } from "react-router-dom";
import { requestPostOrPut } from "../../api/request";

const baseURL=import.meta.env.VITE_API_URL ? import.meta.env.VITE_API_URL : 'http://localhost:8080/api/';
const url= baseURL+`auctions`

const NewAuction = () => {
  const userData = useOutletContext();
  const navigate = useNavigate();
  const [getError,setError]=useState();

  const [getItem, setItem] = useState({
    itemId: "",
    itemTitle: "",
    itemDescription: "",
    startingPrice: "",
    currentPrice: "",
    auctionStartTime: "",
    auctionEndTime: "",
    clientId:"",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setItem((prevItem) => {
      return {
        ...prevItem,
        [name]: value,
      };
    });
  };

  const handleForm = (event) => {
    event.preventDefault();
    const newAuction={
       "auctionStartTime": getItem.auctionStartTime,
        "auctionEndTime": getItem.auctionEndTime,
        "itemDtos": [
            {
                "itemTitle": getItem.itemTitle,
                "itemDescription": getItem.itemDescription,
                "startingPrice": getItem.startingPrice,
                "currentPrice": getItem.startingPrice,
                "clientId":userData.clientId
            }
        ]
    };
    console.log(newAuction);  
    requestPostOrPut(url,newAuction,"POST")
    .then((response)=>{
        console.log(response);
        navigate(`/account/auction/${response.auctionId}`);
    })
    .catch((err) => {
     setError(err.message);
    });
    
  };

  return (
    <div className="description-container">
      <h1>New Auction</h1>
      <form onSubmit={handleForm} autoComplete="on">
        <label htmlFor="itemTitle">Item Title</label>
        <input
          type="text"
          id="itemTitle"
          name="itemTitle"
          value={getItem.itemTitle}
          onChange={handleChange}
          className="input-field"
        />

        <label htmlFor="itemDesc">Item Description</label>
        <textarea
          type="text"
          id="itemDesc"
          name="itemDescription"
          value={getItem.itemDescription}
          onChange={handleChange}
          className="input-field"
        />

        <label htmlFor="startPrice">Start Price</label>
        <input
          type="number"
          id="startPrice"
          name="startingPrice"
          value={getItem.startingPrice}
          onChange={handleChange}
          className="input-field"
        />

        <label htmlFor="startTime">Auction Start Time</label>
        <input
          type="dateTime-local"
          id="startTime"
          name="auctionStartTime"
          value={getItem.auctionStartTime}
          onChange={handleChange}
          className="input-field"
        />

        <label htmlFor="endTime">Auction End Time</label>
        <input
          type="dateTime-local"
          id="endTime"
          name="auctionEndTime"
          value={getItem.auctionEndTime}
          onChange={handleChange}
          className="input-field"
        />

        <button type="submit" className="create-button">
          Create
        </button>

       {getError && <p className="error">{getError}</p>}
      </form>
    </div>
  );
};

export default NewAuction;
