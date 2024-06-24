import './header.css';
import { NavLink } from 'react-router-dom';
const Header = () => {
  return (
    <header className='header'>
      <div className='container'>
        <h1>
          <NavLink to='/'>
            Task Buddy<span>.com</span>
          </NavLink>
        </h1>
      </div>
      <div className='greeting'>
        <h2>Welcome Task Buddy</h2>{' '}
      </div>
      <div className='new-Task-List'>
        <h3>
          <NavLink to='/' className={({isActive})=>{isActive ? "active" : ''}}>New Task List</NavLink>
        </h3>
      </div>
    </header>
  );
};

export default Header;
