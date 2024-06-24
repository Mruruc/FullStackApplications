import "bootstrap/dist/css/bootstrap.min.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";

import homePageRoute from "./routes/HomePageRoute.jsx";
import accountRoute from "./routes/AccountRoute.jsx";

const route = createBrowserRouter([
  homePageRoute,
  accountRoute
]);

function App() {
  return (
    <>
      <RouterProvider router={route} />
    </>
  );
}

export default App;
