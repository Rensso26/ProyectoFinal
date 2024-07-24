import React from 'react';

const ProductCatalog = ({ products, onAddProduct }) => {
  return (
    <div className="product-catalog">
      <div className="row">
        {products.map(product => (
          <div key={product.id} className="col-md-4 mb-4">
            <div className="card">
              <img src={product.image} alt={product.name} className="card-img-top" />
              <div className="card-body">
                <h5 className="card-title">{product.name}</h5>
                <p className="card-text">{product.description}</p>
                <p className="card-text">${product.price.toFixed(2)}</p>
                <button className="btn btn-primary" onClick={() => onAddProduct(product)}>Agregar a producción</button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductCatalog;
