// src/components/AdminPanel.js
import React, { useEffect, useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ProductContext } from '../context/ProductContext';
import 'bootstrap/dist/css/bootstrap.min.css';

const AdminPanel = ({ user, onLogout }) => {
  const { selectedProducts, setSelectedProducts } = useContext(ProductContext);
  const [currentTime, setCurrentTime] = useState(new Date());
  const navigate = useNavigate();

  useEffect(() => {
    // Actualizar la hora cada segundo
    const timer = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);

    // Limpiar el intervalo cuando el componente se desmonte
    return () => clearInterval(timer);
  }, []);

  // Navegar a la página de creación de productos
  const handleCreateProduct = () => {
    navigate('/create-product');
  };

  // Navegar a la página de fabricación de productos
  const handleManufactureProduct = () => {
    navigate('/manufacture-product');
  };

  return (
    <div className="container mt-5">
      {/* Botón de cierre de sesión */}
      <div className="d-flex justify-content-end mb-3">
        <button className="btn btn-danger" onClick={onLogout}>
          Cerrar Sesión
        </button>
      </div>

      {/* Tarjeta del panel de administrador */}
      <div className="card shadow">
        <div className="card-header bg-primary text-white text-center">
          <h2>Panel de Administrador</h2>
        </div>
        <div className="card-body">
          {/* Mensaje de bienvenida */}
          <p className="text-center">Bienvenido, {user}</p>

          {/* Mostrar la fecha y hora actual */}
          <div className="d-flex justify-content-between">
            <p>Fecha actual: {currentTime.toLocaleDateString()}</p>
            <p>Hora actual: {currentTime.toLocaleTimeString()}</p>
          </div>

          {/* Botones de acciones */}
          <div className="d-flex justify-content-around mt-4">
            <button className="btn btn-success" onClick={handleCreateProduct}>
              Crear Producto
            </button>
            <button className="btn btn-warning" onClick={handleManufactureProduct}>
              Fabricar Producto
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminPanel;
