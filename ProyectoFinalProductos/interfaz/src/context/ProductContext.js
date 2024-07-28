import React, { createContext, useState } from 'react';

export const ProductContext = createContext();

const ProductProvider = ({ children }) => {
  const [selectedProducts, setSelectedProducts] = useState([]);
  const [productsToFabricate, setProductsToFabricate] = useState([]);

  // Función para limpiar los productos seleccionados
  const clearSelectedProducts = () => {
    setSelectedProducts([]);
  };

  return (
    <ProductContext.Provider
      value={{
        selectedProducts,
        setSelectedProducts,
        productsToFabricate,
        setProductsToFabricate,
        clearSelectedProducts, // Agrega esta función
      }}
    >
      {children}
    </ProductContext.Provider>
  );
};

export default ProductProvider;
