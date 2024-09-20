import React from 'react';

interface NavBarProps {
  role: string;
  setActivePage: (page: string) => void;
}

const NavBar: React.FC<NavBarProps> = ({ role, setActivePage }) => (
  <nav className="navbar has-background-primary-blue" role="navigation" aria-label="main navigation">
    <div className="container">
      <div className="navbar-brand">
        <div className="navbar-burger burger"
          aria-label="menu"
          aria-expanded="false"
          data-target="navbarMenu"
          onClick={() => {
            const menu = document.getElementById('navbarMenu');
            if (menu) {
              menu.classList.toggle('is-active');
            }
          }}>
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
        </div>
      </div>

      <div id="navbarMenu" className="navbar-menu">
        <div className="navbar-start">
          {role === 'admin' ? (
            <>
              <a className="navbar-item" onClick={() => setActivePage('CourseManagement')}>Course Management</a>
              <a className="navbar-item" onClick={() => setActivePage('ClientManagement')}>Client Management</a>
            </>
          ) : (
            <>
              <a className="navbar-item" onClick={() => setActivePage('Home')}>Home</a>
              <a className="navbar-item" onClick={() => setActivePage('Learnings')}>My Learnings</a>
            </>
          )}
        </div>
        <div className="navbar-end">
          <a className="navbar-item">Logout</a>
        </div>
      </div>
    </div>
  </nav>
);

export default NavBar;