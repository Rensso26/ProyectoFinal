import React, { useState, useEffect } from 'react';
import { getOrders } from '../services/OrderService';

const ManufactureProduct = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    getOrders()
      .then(response => {
        setOrders(response.data);
      })
      .catch(error => {
        console.error('Error fetching orders:', error);
      });
  }, []);

  return (
    <div>
      <h3>Órdenes para Fabricación</h3>
      {orders.length > 0 ? (
        <ul>
          {orders.map((order, index) => (
            <li key={index}>
              {order.map(product => (
                <div key={product.id}>
                  <span>{product.name} - {product.quantity}</span>
                </div>
              ))}
            </li>
          ))}
        </ul>
      ) : (
        <p>No hay órdenes pendientes.</p>
      )}
    </div>
  );
};

export default ManufactureProduct;
