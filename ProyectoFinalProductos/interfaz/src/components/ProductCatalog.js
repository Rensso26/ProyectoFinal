import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const ProductCatalog = ({ products, onAddProduct }) => {
  return (
    <div className="product-catalog">
      <h3>Catálogo de Productos</h3>
      {products.length === 0 ? (
        <p>No hay productos disponibles.</p>
      ) : (
        <div className="row">
          {products.map(product => (
            <div key={product.id} className="col-md-4 mb-4">
              <div className="card">
                <img src={product.image} className="card-img-top" alt={product.name} />
                <div className="card-body">
                  <h5 className="card-title">{product.name}</h5>
                  <p className="card-text">{product.description}</p>
                  <p className="card-text">${product.price}</p>
                  <button className="btn btn-primary" onClick={() => onAddProduct(product)}>Agregar al Carrito</button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default ProductCatalog;
