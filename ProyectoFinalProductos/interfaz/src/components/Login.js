import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const Login = ({ onLogin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    // Validación para el administrador
    if (username === 'xavier' && password === '12345') {
      onLogin(username, 'admin');
    } else if (username !== '' && password !== '') {
      // Suponemos que cualquier otro usuario es un cliente normal
      onLogin(username, 'client');
    } else {
      setError('Credenciales inválidas. Inténtalo de nuevo.');
    }
  };

  return (
    <div className="login-container d-flex align-items-center justify-content-center vh-100" style={{ backgroundColor: 'transparent', border: 'none' }}>
      <div className="card p-4" style={{ width: '100%', maxWidth: '400px', backgroundColor: 'rgba(255, 255, 255, 0.8)', borderRadius: '15px', boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1), 0 6px 20px rgba(0, 0, 0, 0.19), 0 0 10px rgba(255, 0, 0, 0.5), 0 0 10px rgba(0, 255, 0, 0.5), 0 0 10px rgba(0, 0, 255, 0.5)' }}>
        <div className="card-body">
          <h2 className="card-title mb-4">Inicio de Sesión</h2>
          {error && <div className="alert alert-danger">{error}</div>}
          <form onSubmit={handleSubmit}>
            <div className="form-group mb-3">
              <label>Usuario:</label>
              <input 
                type="text" 
                className="form-control"
                value={username} 
                onChange={(e) => setUsername(e.target.value)} 
                placeholder="Ingrese su usuario"
              />
            </div>
            <div className="form-group mb-3">
              <label>Contraseña:</label>
              <input 
                type="password" 
                className="form-control"
                value={password} 
                onChange={(e) => setPassword(e.target.value)} 
                placeholder="Ingrese su contraseña"
              />
            </div>
            <button type="submit" className="btn btn-primary btn-block">Iniciar Sesión</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
