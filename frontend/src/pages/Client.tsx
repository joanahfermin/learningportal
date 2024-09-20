import React, { useState } from 'react';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import HomePage from './client/HomePage';
import LearningsPage from './client/LearningsPage';

const Client: React.FC = () => {
  const [activePage, setActivePage] = useState<string>('Learnings');

  const renderPage = () => {
    switch (activePage) {
      case 'Home':
        return <HomePage />;
      case 'Learnings':
        return <LearningsPage />;
      default:
        return <LearningsPage />;
    }
  };

  return (
    <div className="App">
      <NavBar role="client" setActivePage={setActivePage} />
      <div className="main-content">
        {renderPage()}
      </div>
      <Footer/>
    </div>
  );
};

export default Client;