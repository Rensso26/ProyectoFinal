import React, { useState } from 'react';
import './App.css';
import Login from './components/Login';
import AdminPanel from './components/AdminPanel';
import ClientPanel from './components/ClientPanel';
import Notifications from './components/Notifications';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [user, setUser] = useState(null);
  const [profile, setProfile] = useState(null);

  const handleLogin = (username, userProfile) => {
    setUser(username);
    setProfile(userProfile);
  };

  const handleLogout = () => {
    setUser(null);
    setProfile(null);
  };

  return (
    <div className="App">
      {profile === 'client' && (
        <div className="header-title">
          <h1>
            <span>J</span>
            <span>u</span>
            <span>e</span>
            <span>g</span>
            <span>o</span>
            <span>m</span>
            <span>a</span>
            <span>n</span>
            <span>í</span>
            <span>a</span>
          </h1>
        </div>
      )}
      {!user ? (
        <Login onLogin={handleLogin} />
      ) : profile === 'admin' ? (
        <AdminPanel user={user} onLogout={handleLogout} />
      ) : (
        <ClientPanel user={user} onLogout={handleLogout} />
      )}
      <Notifications />
    </div>
  );
}

export default App;
