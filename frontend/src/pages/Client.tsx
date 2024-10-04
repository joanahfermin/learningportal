import React from 'react';
import { Routes, Route } from 'react-router-dom';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import HomePage from './client/HomePage';
import LearningsPage from './client/LearningsPage';

const Client: React.FC = () => {
  return (
    <div className="App">
      <NavBar role="client" setActivePage={() => {}} />
      <div className="main-content">
        <Routes>
          <Route path="home" element={<HomePage />} />
          <Route path="learnings" element={<LearningsPage />} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
};

export default Client;
