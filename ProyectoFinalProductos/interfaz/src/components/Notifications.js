import React from 'react';

const Notifications = ({ products }) => {
  return (
    <div className="notifications mt-4">
      <h4>Notificaciones</h4>
      {products.length === 0 ? (
        <p>No hay productos seleccionados.</p>
      ) : (
        <ul className="list-group">
          {products.map((product, index) => (
            <li key={index} className="list-group-item">
              {product.name} - ${product.price.toFixed(2)}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Notifications;
