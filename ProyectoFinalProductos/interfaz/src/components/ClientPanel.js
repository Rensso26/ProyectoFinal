import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './Navbar';
import ProductCatalog from './ProductCatalog';
import Notifications from './Notifications';
import { listCategories } from '../services/CategoryServices';
import { useNavigate } from 'react-router-dom';

const defaultCategories = [
  {
    name: 'Juguetes Educativos',
    products: [
      { id: 1, name: 'Rompecabezas', description: 'Rompecabezas de madera', price: 19.99, image: require('../assets/images/productos/Educativos/rompecabezas.png') },
      { id: 2, name: 'Juego de Química', description: 'Kit de experimentos', price: 39.99, image: require('../assets/images/productos/Educativos/quimica.png') },
    ],
  },
];

const getImagePath = (image) => {
  try {
    return require(`../assets/images/productos/${image}`);
  } catch (err) {
    console.error(`Error loading image: ${image}`, err);
    return image.startsWith('http') ? image : null;
  }
};

const ClientPanel = ({ user, onLogout }) => {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(defaultCategories[0].name);
  const [selectedProducts, setSelectedProducts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    listCategories()
      .then((response) => {
        console.log("API response:", response.data);

        const apiCategories = response.data.map(category => {
          console.log("Category from API:", category);

          const products = category.toys.map(toy => {
            console.log("Toy from API:", toy);

            return {
              id: toy.id,
              name: toy.name,
              description: toy.description,
              price: toy.price,
              image: getImagePath(toy.image),
            };
          });

          return {
            name: category.name,
            products,
          };
        });

        console.log("Mapped API categories:", apiCategories);

        setCategories([...defaultCategories, ...apiCategories]);
      })
      .catch(error => {
        console.error("API error:", error);
        setCategories(defaultCategories);
      });
  }, []);

  const handleSelectCategory = (categoryName) => {
    setSelectedCategory(categoryName);
  };

  const handleAddProduct = (product) => {
    setSelectedProducts(prevProducts => [...prevProducts, product]);
  };

  const handleLogoutAndClear = () => {
    setSelectedProducts([]);
    onLogout();
    navigate('/');
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
          <button className="btn btn-danger" onClick={handleLogoutAndClear}>Cerrar Sesión</button>
        </div>
        <ProductCatalog products={selectedCategoryProducts} onAddProduct={handleAddProduct} />
        <Notifications products={selectedProducts} />
      </div>
    </div>
  );
};

export default ClientPanel;
