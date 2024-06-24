import Overview from "../pages/user/auction/Overview.jsx";
import Pricing from "../pages/user/auction/Pricing.jsx";
import Description from "../pages/user/auction/Description.jsx";
import Features from "../pages/user/auction/Features.jsx";
import AuctionLayout from "../layouts/AuctionLayout.jsx";
import Bid from "../pages/user/auction/Bid.jsx";
import SuccessPage from "../pages/user/auction/SuccessPage.jsx";
import CancelPage from "../pages/user/auction/CancelPage.jsx";


const auctionRoute=
{
    path: "auction/:auctionId",
    element: <AuctionLayout />,
    children: [
      {
        index: true,
        element: <Overview />,
      },
      {
        path: "pricing",
        element: <Pricing />,
      },
      {
        path: "description",
        element: <Description />,
      },
      {
        path: "features",
        element: <Features />,
      },
      {
        path: "bid",
        element: <Bid />,
      },
      {
        path:"payment-success",
        element:<SuccessPage/>
      },
      {
        path: "payment-cancel",
        element:<CancelPage/>
      }
    ],
};


export default auctionRoute;