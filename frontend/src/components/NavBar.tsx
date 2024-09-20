import React from 'react';

interface NavBarProps {
  role: string;
  setActivePage: (page: string) => void;
}

const NavBar: React.FC<NavBarProps> = ({ role, setActivePage }) => {
  const [isActive, setisActive] = React.useState(false)

  return (
    <nav className='navbar' role='navigation' aria-label='main navigation'>
      <div className='container'>
        <div className='navbar-brand'>
          <a href='/' className='navbar-item'>
            <img src='/logowide.webp'alt='Logo'/>
          </a>
          <a
            onClick={() => {
              setisActive(!isActive)
            }}
            role='button'
            className={`navbar-burger burger ${isActive ? 'is-active' : ''}`}
            aria-label='menu'
            aria-expanded='false'
            data-target='navbarBasicExample'
          >
            <span aria-hidden='true'></span>
            <span aria-hidden='true'></span>
            <span aria-hidden='true'></span>
          </a>
        </div>
        <div id='navbarBasicExample' className={`navbar-menu ${isActive ? 'is-active' : ''}`}>
          <div className='navbar-end'>
            <div className='navbar-item'>
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
              <a className="navbar-item" >Logout</a>
            </div>
          </div>
        </div>

      </div>
    </nav>
  );
};

export default NavBar;