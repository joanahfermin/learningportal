import React, { useState } from 'react';
import LoginPage from './pages/LoginPage';
import Admin from './pages/Admin';
import Client from './pages/Client';

const App: React.FC = () => {
  const [userRole, setUserRole] = useState<string | null>(null);

  if (userRole === null) {
    return <LoginPage setUserRole={setUserRole} />;
  }

  return (
    <div className="App">
      {userRole === 'admin' ? (
        <Admin />
      ) : (
        <Client />
      )}
    </div>
  );
};

export default App;