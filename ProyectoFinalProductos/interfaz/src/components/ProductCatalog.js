import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const ProductCatalog = ({ products }) => {
  return (
    <div className="container mt-5">
      <div className="row">
        {products.map((product, index) => (
          <div key={index} className="col-md-4 mb-4">
            <div className="card h-100 shadow-sm">
              <img src={product.image} alt={product.name} className="card-img-top" />
              <div className="card-body d-flex flex-column">
                <h5 className="card-title">{product.name}</h5>
                <p className="card-text">{product.description}</p>
                <p className="card-text text-primary">${product.price.toFixed(2)}</p>
                <button className="btn btn-primary mt-auto">Agregar al Carrito</button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductCatalog;
