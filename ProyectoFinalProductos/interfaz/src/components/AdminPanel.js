// src/components/AdminPanel.js
import React, { useEffect, useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ProductContext } from '../context/ProductContext';
import 'bootstrap/dist/css/bootstrap.min.css';

const AdminPanel = ({ user, onLogout }) => {
  const { productsToFabricate, setProductsToFabricate } = useContext(ProductContext);
  const [currentTime, setCurrentTime] = useState(new Date());
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  const handleAcceptProduct = (productId) => {
    setProductsToFabricate(prev => prev.filter(p => p.id !== productId));
    alert(`Producto ${productId} aceptado para fabricación.`);
  };

  const handleRejectProduct = (productId) => {
    setProductsToFabricate(prev => prev.filter(p => p.id !== productId));
    alert(`Producto ${productId} rechazado.`);
  };

  const handleLogoutAndClear = () => {
    onLogout();
    navigate('/');
  };

  return (
    <div className="container mt-5">
      <div className="d-flex justify-content-end mb-3">
        <button className="btn btn-danger" onClick={handleLogoutAndClear}>
          Cerrar Sesión
        </button>
      </div>

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

          <div className="mt-4">
            <h4>Productos para Fabricar</h4>
            {productsToFabricate.length === 0 ? (
              <p>No hay productos para fabricar.</p>
            ) : (
              <ul className="list-group">
                {productsToFabricate.map(product => (
                  <li key={product.id} className="list-group-item d-flex justify-content-between align-items-center">
                    <div>
                      <h5>{product.name}</h5>
                      <p>{product.description}</p>
                      <p>Precio: ${product.price.toFixed(2)}</p>
                    </div>
                    <div>
                      <button className="btn btn-success mr-2" onClick={() => handleAcceptProduct(product.id)}>
                        Aceptar
                      </button>
                      <button className="btn btn-danger" onClick={() => handleRejectProduct(product.id)}>
                        Rechazar
                      </button>
                    </div>
                  </li>
                ))}
              </ul>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminPanel;
