import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const AdminPanel = ({ user, onLogout }) => {
  const [currentTime, setCurrentTime] = useState(new Date());

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  const handleManufacture = (product) => {
    // Lógica para iniciar el proceso de fabricación
    console.log(`Iniciando fabricación del producto: ${product}`);
  };

  return (
    <div className="container mt-5">
      <div className="card shadow">
        <div className="card-header bg-primary text-white text-center">
          <h2>Panel de Administrador</h2>
        </div>
        <div className="card-body">
          <p className="text-center">Bienvenido, {user}</p>
          <div className="d-flex justify-content-between">
            <p>Fecha actual: {currentTime.toLocaleDateString()}</p>
            <p>Hora actual: {currentTime.toLocaleTimeString()}</p>
          </div>
          <button className="btn btn-danger btn-block mb-4" onClick={onLogout}>Cerrar Sesión</button>
          <div>
            <h3 className="text-center mb-4">Procesos de Fabricación</h3>
            <ul className="list-group">
              <li className="list-group-item d-flex justify-content-between align-items-center">
                Producto 1 
                <button className="btn btn-success" onClick={() => handleManufacture('Producto 1')}>Fabricar</button>
              </li>
              <li className="list-group-item d-flex justify-content-between align-items-center">
                Producto 2 
                <button className="btn btn-success" onClick={() => handleManufacture('Producto 2')}>Fabricar</button>
              </li>
              {/* Agrega más productos según sea necesario */}
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminPanel;
