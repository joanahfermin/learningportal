import React, { useEffect } from 'react';
import { Routes, Route, useLocation } from 'react-router-dom';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import CoursePage from './admin/CoursePage';
import ClientManagementPage from './admin/ClientManagementPage';

const Admin: React.FC = () => {
  const location = useLocation();

  useEffect(() => {
    //console.log('Current location:', location.pathname);
  }, [location]);

  return (
    <div className="App">
      <NavBar role="admin"/>
      <div className="main-content">
        <Routes>
          <Route path="/admin/course" element={<CoursePage />} />
          <Route path="/admin/client" element={<ClientManagementPage />} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
};

export default Admin;