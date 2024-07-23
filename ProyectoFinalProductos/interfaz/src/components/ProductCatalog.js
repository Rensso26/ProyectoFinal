// src/components/ProductCatalog.js
import React from 'react';

const ProductCatalog = ({ categories }) => {
  return (
    <div className="product-catalog">
      {categories.map(category => (
        <div key={category.name} className="category">
          <h3 className="category-title">{category.name}</h3>
          <div className="products">
            {category.products.map(product => (
              <div key={product.id} className="product-card">
                <img src={product.image} alt={product.name} className="product-image" />
                <h4>{product.name}</h4>
                <p>{product.description}</p>
                <p className="product-price">${product.price.toFixed(2)}</p>
              </div>
            ))}
          </div>
        </div>
      ))}
    </div>
  );
};

export default ProductCatalog;
