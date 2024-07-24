import React from 'react';

const Notifications = ({ products }) => {
  // Verificar que products es un array
  if (!Array.isArray(products)) {
    console.error('Expected products to be an array');
    return <p>Error: Datos de productos inválidos.</p>;
  }

  return (
    <div className="notifications mt-4">
      <h4>Notificaciones</h4>
      {products.length === 0 ? (
        <p>No hay productos seleccionados.</p>
      ) : (
        <ul className="list-group">
          {products.map((product, index) => {
            // Verificar que cada producto tiene las propiedades esperadas
            if (!product || typeof product.name !== 'string' || typeof product.price !== 'number') {
              console.error('Producto inválido:', product);
              return <li key={index} className="list-group-item">Producto inválido</li>;
            }
            return (
              <li key={index} className="list-group-item">
                {product.name} - ${product.price.toFixed(2)}
              </li>
            );
          })}
        </ul>
      )}
    </div>
  );
};

export default Notifications;
