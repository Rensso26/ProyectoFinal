// src/components/ClientPanel.js
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './Navbar';
import ProductCatalog from './ProductCatalog';
import Notifications from './Notifications';
const categories = [
  {
    name: 'Juguetes Educativos',
    products: [
      { id: 1, name: 'Rompecabezas', description: 'Rompecabezas de madera', price: 19.99, image: require('../assets/images/productos/Educativos/rompecabezas.png') },
      { id: 2, name: 'Juego de Química', description: 'Kit de experimentos', price: 39.99, image: require('../assets/images/productos/Educativos/quimica.png') },
    ],
  },
  {
    name: 'Juguetes de Acción y Figuras',
    products: [
      { id: 3, name: 'Vehículos de juguete', description: 'Edición especial', price: 29.99, image: require('../assets/images/productos/Accion/vehiculo.png') },
      { id: 4, name: 'Figura de Acción', description: 'Figura de superhéroe', price: 24.99, image: require('../assets/images/productos/Accion/figura.png') },
    ],
  },
  {
    name: 'Juguetes Electrónicos',
    products: [
      { id: 5, name: 'Robot', description: 'Robot interactivo', price: 49.99, image: require('../assets/images/productos/Electronicos/robot.png') },
      { id: 6, name: 'Dron', description: 'Dron con cámara', price: 89.99, image: require('../assets/images/productos/Electronicos/dron.png') },
    ],
  },
  {
    name: 'Juguetes de Peluche',
    products: [
      { id: 7, name: 'Animales de peluche', description: 'Suaves y duraderos', price: 19.99, image: require('../assets/images/productos/Peluches/peluches.png') },
      { id: 8, name: 'Muñeca', description: 'Muñecas suaves y flexibles', price: 29.99, image: require('../assets/images/productos/Peluches/muñeca.png') },
    ],
  },
  {
    name: 'Juguetes Deportivos',
    products: [
      { id: 9, name: 'Balones y pelotas', description: 'Resistentes y perfectos para jugar', price: 15.99, image: require('../assets/images/productos/Deportivos/balones.jpg') },
      { id: 10, name: 'Raquetas y palos', description: 'Ligeros y duraderos', price: 24.99, image: require('../assets/images/productos/Deportivos/raqueta5.jpg') },
    ],
  },
  {
    name: 'Juguetes de Construcción',
    products: [
      { id: 11, name: 'Bloques de contrucción (plasticos)', description: 'Desarrolla habilidades motoras', price: 34.99, image: require('../assets/images/productos/Construccion/bloques.jpg') },
      { id: 12, name: 'Kits de modelado y trenes', description: 'Estimulan la creatividad', price: 44.99, image: require('../assets/images/productos/Construccion/trenes.jpg') },
    ],
  },
  
];

const ClientPanel = ({ user, onLogout }) => {
  const [selectedCategory, setSelectedCategory] = useState(categories[0].name);
  const [selectedProducts, setSelectedProducts] = useState([]);

  const handleSelectCategory = (categoryName) => {
    setSelectedCategory(categoryName);
  };

  const handleAddProduct = (product) => {
    setSelectedProducts(prevProducts => [...prevProducts, product]);
  };

  const selectedCategoryProducts = categories.find(category => category.name === selectedCategory)?.products || [];

  return (
    <div className="client-panel">
      <Navbar 
        categories={categories} 
        onSelectCategory={handleSelectCategory} 
        selectedCategory={selectedCategory}
      />
      <div className="container mt-5">
        <div className="header d-flex justify-content-between align-items-center mb-4">
          <h2>Bienvenido, {user}</h2>
          <button className="btn btn-danger" onClick={onLogout}>Cerrar Sesión</button>
        </div>
        <ProductCatalog products={selectedCategoryProducts} onAddProduct={handleAddProduct} />
        <Notifications products={selectedProducts} />
      </div>
    </div>
  );
};

export default ClientPanel;