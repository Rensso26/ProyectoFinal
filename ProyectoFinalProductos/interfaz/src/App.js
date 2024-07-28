import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import './App.css';
import Login from './components/Login';
import AdminPanel from './components/AdminPanel';
import ClientPanel from './components/ClientPanel';
import Notifications from './components/Notifications';
import CreateProduct from './components/CreateProduct';
import ManufactureProduct from './components/ManufactureProduct'; // Importa el nuevo componente
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
    <Router>
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
          <Routes>
            <Route path="/admin" element={<AdminPanel user={user} onLogout={handleLogout} />} />
            <Route path="/create-product" element={<CreateProduct />} />
            <Route path="/manufacture-product" element={<ManufactureProduct />} /> {/* Nueva ruta */}
            <Route path="*" element={<Navigate to="/admin" />} />
          </Routes>
        ) : (
          <Routes>
            <Route path="/client" element={<ClientPanel user={user} onLogout={handleLogout} />} />
            <Route path="*" element={<Navigate to="/client" />} />
          </Routes>
        )}
        <Notifications />
      </div>
    </Router>
  );
}

export default App;
