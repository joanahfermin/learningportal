import React, { createContext, useContext, useState, ReactNode } from 'react';
import {authService} from '../../services/AuthService';
import { setToken as setMemoryToken } from '../../services/tokenService';

type AuthContextType = {
  username: string | null;
  role: 'ADMIN' | 'CLIENT' | null;
  token: string | null;
  login: (username: string, password: string) => Promise<void>;
  logout: () => void;
};

const AuthContext = createContext<AuthContextType | undefined>(undefined);

type AuthProviderProps = {
  children: ReactNode;
};

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [username, setUsername] = useState<string | null>(null);
  const [role, setRole] = useState<'ADMIN' | 'CLIENT' | null>(null);
  const [token, setToken] = useState<string | null>(null);
  
  const login = async (username: string, password: string) => {
    try {
      const response = await authService.login(username, password);
      setUsername(response.username);
      setRole(response.role);
      setToken(response.token);
      setMemoryToken(response.token);
    } catch (error) {
      console.error(error);
      throw error;
    }
  };

  const logout = () => {
    setUsername(null);
    setRole(null);
    setToken(null);
    setMemoryToken(null);
  };

  return (
    <AuthContext.Provider value={{ username, role, token, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};