import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './components/Auth/AuthContext';
import NavBar from './components/NavBar';
import LoginPage from './pages/LoginPage';
import CoursePage from './pages/admin/CoursePage';
import LessonPage from './pages/admin/LessonPage';
import TopicPage from './pages/admin/TopicPage';
import ClientManagementPage from './pages/admin/ClientManagementPage';
import ClientGroupPage from './pages/admin/ClientGroupPage';
import HomePage from './pages/client/HomePage';
import LearningsPage from './pages/client/LearningsPage';

const App: React.FC = () => {
  return (
    <AuthProvider>
      <MainRoutes />
    </AuthProvider>
  );
};

const MainRoutes: React.FC = () => {
  const { username } = useAuth();

  return (
    <>
      {username && <NavBar />}
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/admin-course" element={<RequireAuth role="ADMIN"><CoursePage /></RequireAuth>} />
        <Route path="/admin-course/:courseId/lessons" element={<RequireAuth role="ADMIN"><LessonPage /></RequireAuth>} />
        <Route path="/admin-course/:courseId/lessons/:lessonId/topics" element={<RequireAuth role="ADMIN"><TopicPage /></RequireAuth>} />
        <Route path="/admin-client" element={<RequireAuth role="ADMIN"><ClientManagementPage /></RequireAuth>} />
        <Route path="/admin-client-group" element={<RequireAuth role="ADMIN"><ClientGroupPage /></RequireAuth>} />
        <Route path="/client-home" element={<RequireAuth role="CLIENT"><HomePage /></RequireAuth>} />
        <Route path="/client-learnings" element={<RequireAuth role="CLIENT"><LearningsPage /></RequireAuth>} />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </>
  );
};

const RequireAuth: React.FC<{ children: JSX.Element; role: 'ADMIN' | 'CLIENT' }> = ({ children, role }) => {
  const { role: userRole, username } = useAuth();

  if (!username || userRole !== role) {
    return <Navigate to="/" />;
  }

  return children;
};

export default App;