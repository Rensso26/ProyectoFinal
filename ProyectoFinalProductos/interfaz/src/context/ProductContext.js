// src/context/ProductContext.js
import React, { createContext, useState } from 'react';

export const ProductContext = createContext();

const ProductProvider = ({ children }) => {
  const [selectedProducts, setSelectedProducts] = useState([]);

  return (
    <ProductContext.Provider value={{ selectedProducts, setSelectedProducts }}>
      {children}
    </ProductContext.Provider>
  );
};

export default ProductProvider;
