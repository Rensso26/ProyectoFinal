import React, { createContext, useState } from 'react';

export const ProductContext = createContext();

const ProductProvider = ({ children }) => {
  const [selectedProducts, setSelectedProducts] = useState([]);
  const [productsToFabricate, setProductsToFabricate] = useState([]);

  return (
    <ProductContext.Provider value={{ selectedProducts, setSelectedProducts, productsToFabricate, setProductsToFabricate }}>
      {children}
    </ProductContext.Provider>
  );
};

export default ProductProvider;