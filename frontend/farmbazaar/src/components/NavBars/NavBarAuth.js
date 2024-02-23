import React from 'react';
import { Link } from 'react-router-dom';
import logo from "../NavBars/logo.png";

const NavBarAuth = () => {
  return (
    <>
      <nav style={{backgroundColor: 'transparent', marginBottom: '20px'}} className="navbar navbar-expand-lg bg-light">
        <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center'}} className="container-fluid">
          <div>
            <Link to='/signin' style={{textDecoration: 'none', color: '#12372A', display: 'flex', alignItems: 'center'}} className="navbar-brand">
              <img src={logo} alt="FarmBazaar Logo" width="52" height="45" style={{marginRight: '8px'}} />
              <span style={{fontFamily: 'cursive', fontSize: '24px', color: '#FF0000', fontWeight: 'bold'}}>Farm</span>
              <span style={{fontFamily: 'cursive', fontSize: '24px', color: '#FFA500', fontWeight: 'bold'}}>Bazaar</span>
            </Link>
          </div>
        </div>
      </nav>
      {/* Add margin or padding below the Navbar */}
      <div style={{ marginBottom: '20px' }}>
        {/* Your page content here */}
      </div>
    </>
  );
};

export default NavBarAuth;
