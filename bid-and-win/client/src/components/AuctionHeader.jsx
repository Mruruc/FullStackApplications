    import { NavLink } from "react-router-dom";

    const AuctionHeader = () => {
        return(
            <div className="centered-navbar">
                <nav className="navbar navbar-expand-lg navbar-light bg-light navbar-container">
                    <NavLink className="navbar-brand px-5" to='.'>
                        Overview
                    </NavLink>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item active">
                                <NavLink className="nav-link px-3" to="#">
                                    Host
                                </NavLink>
                            </li>
                            <li className="nav-item">
                                <NavLink className="nav-link px-3" to="description">
                                    Description
                                </NavLink>
                            </li>
                            <li className="Bid">
                                <NavLink className="nav-link px-3" to="bid">
                                    Bid
                                </NavLink>
                            </li>
                            <li className="nav-item">
                                <NavLink className="nav-link px-3" to="pricing">
                                    Pricing
                                </NavLink>
                            </li>
                            <li className="nav-item">
                                <NavLink className="nav-link px-3" to="features">
                                    Features
                                </NavLink>
                            </li>
                           
                        </ul>
                    </div>
                </nav>
            </div>
        );
    };

    export default AuctionHeader;
