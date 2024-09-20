import React, { useState } from 'react';
import Footer from '../components/Footer';

interface LoginPageProps {
  setUserRole: (role: string) => void;
}

const LoginPage: React.FC<LoginPageProps> = ({ setUserRole }) => {
  const [username, setUsername] = useState<string>('');

  const handleLogin = () => {
    if (username === 'admin') {
      setUserRole('admin');
    } else if (username === 'client') {
      setUserRole('client');
    } else {
      alert('Invalid username');
    }
  };

  return (
    <div className="login-page">
      <div className="login-content">
        <div className="login-box">
          <h2 className="title has-text-centered">Login</h2>
          <div className="field">
            <label className="label">Username</label>
            <div className="control">
              <input 
                className="input" 
                type="text" 
                placeholder="Enter username" 
                value={username} 
                onChange={(e) => setUsername(e.target.value)} 
              />
            </div>
          </div>
          <div className="control has-text-centered">
            <button className="button is-primary is-rounded is-large is-fullwidth" onClick={handleLogin}>Login</button>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default LoginPage;