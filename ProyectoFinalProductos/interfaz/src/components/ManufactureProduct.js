// src/components/ManufactureProduct.js
import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const ManufactureProduct = () => {
  const [productId, setProductId] = useState('');
  const [quantity, setQuantity] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aquí va la lógica para enviar la solicitud de fabricación al backend
    console.log('Fabricar producto:', { productId, quantity });
    navigate('/admin'); // Redirige al panel de administración después de solicitar la fabricación
  };

  return (
    <div className="container mt-5">
      <div className="d-flex justify-content-start mb-3">
        <Link to="/admin" className="btn btn-outline-secondary">
          ← Volver al Panel de Administrador
        </Link>
      </div>
      <h2>Fabricar Producto</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="productId" className="form-label">ID del Producto</label>
          <input
            type="text"
            className="form-control"
            id="productId"
            value={productId}
            onChange={(e) => setProductId(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="quantity" className="form-label">Cantidad a Fabricar</label>
          <input
            type="number"
            className="form-control"
            id="quantity"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            min="1" // Establece el valor mínimo a 1
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Solicitar Fabricación</button>
      </form>
    </div>
  );
};

export default ManufactureProduct;
