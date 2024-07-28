import React, { useContext } from 'react';
import { createPeticion } from '../services/PeticionesServices';
import { ProductContext } from '../context/ProductContext';

const Notifications = ({ onIncrement, onDecrement }) => {
  const { selectedProducts, clearSelectedProducts } = useContext(ProductContext);

  const handleFabricate = () => {
    selectedProducts.forEach(product => {
      const peticion = {
        id: product.id,
        cantidad: product.quantity
      };
      createPeticion(peticion)
        .then(response => {
          console.log('Petición creada:', response.data);
          // Llama a clearSelectedProducts después de crear la petición
          if (product === selectedProducts[selectedProducts.length - 1]) {
            clearSelectedProducts();
          }
        })
        .catch(error => {
          console.error('Error al crear la petición:', error);
        });
    });
  };

  return (
    <div>
      <h3>Productos Seleccionados</h3>
      {selectedProducts.length > 0 ? (
        <ul>
          {selectedProducts.map(product => (
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
      {selectedProducts.length > 0 && (
        <button className="btn btn-primary mt-3" onClick={handleFabricate}>
          Fabricar
        </button>
      )}
    </div>
  );
};

export default Notifications;
