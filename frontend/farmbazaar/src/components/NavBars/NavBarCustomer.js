import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const NavBarCustomer = ({ isLoggedIn }) => {
  const navigate = useNavigate();

  const handleSignOut = () => {
    // Perform sign-out logic here
    // For example, clear session/local storage, update state, etc.
    navigate('/signin');
  };

  return (
    <header className="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
      <Link to="/" className="d-flex align-items-center mb-2 mb-md-0 text-dark text-decoration-none">
      <img src="logo.png" alt="FarmBazaar Logo" width="40" height="32" className="me-2" />

        <span className="fs-4">FarmBazaar</span>
      </Link>

      <ul className="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><Link to="#" className="nav-link px-2 link-secondary">Home</Link></li>
        <li><Link to="#" className="nav-link px-2 link-dark">Features</Link></li>
        <li><Link to="#" className="nav-link px-2 link-dark">Pricing</Link></li>
        <li><Link to="#" className="nav-link px-2 link-dark">FAQs</Link></li>
        <li><Link to="#" className="nav-link px-2 link-dark">About</Link></li>
      </ul>

      <div className="col-md-3 text-end">
        {isLoggedIn ? (
          <button type="button" className="btn btn-outline-primary me-2">Logout</button>
        ) : (
          <>
            <Link to="/signin" className="btn btn-outline-primary me-2">Login</Link>
            <Link to="/signup" className="btn btn-primary">Sign-up</Link>
          </>
        )}
      </div>
    </header>
  );
};

export default NavBarCustomer;