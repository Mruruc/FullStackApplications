import AccountLayout from "../layouts/AccountLayout.jsx";
import Account from "../pages/user/Account.jsx";
import Auctions from "../pages/user/Auctions.jsx";
import NewAuction from "../pages/user/NewAuction.jsx";
import auctionRoute from "./AuctionRoute.jsx";
import userProfileRoute from "./UserProfileRoute.jsx";

const accountRoute = {
  path: "/account",
  element: <AccountLayout />,
  children: [
    {
      index: true,
      element: <Account />,
    },
    userProfileRoute,
    {
      path: "auctions",
      element: <Auctions />,
    },
    auctionRoute,
    {
      path: "newAuction",
      element: <NewAuction />,
    },
  ],
};

export default accountRoute;
