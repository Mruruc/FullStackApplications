import './footer.css';
import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <footer>
      <h3>
        &copy; 2023
        <Link to='https://github.com/Mruruc'>Mruruc.</Link>
        All rights reserved.
      </h3>
    </footer>
  );
};

export default Footer;
