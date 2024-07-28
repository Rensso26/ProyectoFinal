// App.js
import React, { useState } from 'react';
import './App.css';
import Login from './components/Login';
import AdminPanel from './components/AdminPanel';
import ClientPanel from './components/ClientPanel';
import CreateProduct from './components/CreateProduct'; // Asegúrate de importar el componente
import ManufactureProduct from './components/ManufactureProduct'; // Importar el nuevo componente
import 'bootstrap/dist/css/bootstrap.min.css';
import { Routes, Route, Navigate } from 'react-router-dom';

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
      <Routes>
        {!user ? (
          <Route path="/" element={<Login onLogin={handleLogin} />} />
        ) : profile === 'admin' ? (
          <>
            <Route path="/admin" element={<AdminPanel user={user} onLogout={handleLogout} />} />
            <Route path="/create-product" element={<CreateProduct />} />
            <Route path="/manufacture-product" element={<ManufactureProduct />} /> {/* Nueva ruta */}
          </>
        ) : (
          <Route path="/client" element={<ClientPanel user={user} onLogout={handleLogout} />} />
        )}
        <Route path="*" element={<Navigate to={user ? (profile === 'admin' ? '/admin' : '/client') : '/'} />} />
      </Routes>
    </div>
  );
}

export default App;
