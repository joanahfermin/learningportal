import React from "react";
import { Link } from "react-router-dom"; // Import useNavigate from react-router-dom
import { useAuth } from "./Auth/AuthContext";

const NavBar: React.FC = () => {
  const [isActive, setIsActive] = React.useState(false);
  const { role, logout } = useAuth();

  return (
    <nav className="navbar" role="navigation" aria-label="main navigation">
      <div className="container">
        <div className="navbar-brand">
          <a href="/" className="navbar-item">
            <img src="/logowide.webp" alt="Logo" />
          </a>
          <a
            onClick={() => setIsActive(!isActive)}
            role="button"
            className={`navbar-burger burger ${isActive ? "is-active" : ""}`}
            aria-label="menu"
            aria-expanded="false"
            data-target="navbarBasicExample"
          >
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
          </a>
        </div>
        <div
          id="navbarBasicExample"
          className={`navbar-menu ${isActive ? "is-active" : ""}`}
        >
          <div className="navbar-end">
            {role === "ADMIN" && (
              <>
                <Link className="navbar-item" to="/admin-course">Course</Link>
                <div className="navbar-item has-dropdown is-hoverable">
                  <a className="navbar-link">Client</a>
                  <ul className="navbar-dropdown">
                    <Link className="navbar-item" to="/admin-client">Client</Link>
                    <Link className="navbar-item" to="/admin-client-group">Client Group</Link>
                  </ul>
                </div>

              </>
            )}
            {role === "CLIENT" && (
              <>
                <Link className="navbar-item" to="/client-home">Home</Link>
                <Link className="navbar-item" to="/client-learnings">Learnings</Link>
              </>
            )}
            <a className="navbar-item" onClick={logout}>Logout</a>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
