import React, { useState } from 'react';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import CoursePage from './admin/CoursePage';
import ClientManagementPage from './admin/ClientManagementPage';

const Admin: React.FC = () => {
  const [activePage, setActivePage] = useState<string>('CourseManagement');

  const renderPage = () => {
    switch (activePage) {
      case 'CourseManagement':
        return <CoursePage />;
      case 'ClientManagement':
        return <ClientManagementPage />;
      default:
        return <CoursePage />;
    }
  };

  return (
    <div className="App">
      <NavBar role="admin" setActivePage={setActivePage} />
      <div className="main-content">
        {renderPage()}
      </div>
      <Footer/>
    </div>
  );
};

export default Admin;