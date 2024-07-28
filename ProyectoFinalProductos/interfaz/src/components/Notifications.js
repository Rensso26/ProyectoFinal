import React from 'react';

const Notifications = ({ products, onIncrement, onDecrement, onFabricate }) => {
  return (
    <div>
      <h3>Productos Seleccionados</h3>
      {products.length > 0 ? (
        <ul>
          {products.map(product => (
            <li key={product.id}>
              <span>{product.name} - </span>
              <button onClick={() => onDecrement(product)}>-</button>
              <span> {product.quantity} </span>
              <button onClick={() => onIncrement(product)}>+</button>
            </li>
          ))}
        </ul>
      ) : (
        <p>No hay productos seleccionados.</p>
      )}
      {products.length > 0 && (
        <button className="btn btn-primary mt-3" onClick={onFabricate}>
          Fabricar
        </button>
      )}
    </div>
  );
};

export default Notifications;
