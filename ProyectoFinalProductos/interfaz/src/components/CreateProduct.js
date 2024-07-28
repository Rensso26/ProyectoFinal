import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { createToy } from '../services/ToyServices';

const CreateProduct = () => {
  const [name, setName] = useState('');
  const [parts, setParts] = useState('');
  const [price, setPrice] = useState('');
  const [color, setColor] = useState('');
  const [description, setDescription] = useState('');
  const [image, setImage] = useState('');
  const [timeEnsable, setTimeEnsable] = useState('');
  const [timePinter, setTimePinter] = useState('');
  const [timePackaging, setTimePackaging] = useState('');
  const [category, setCategory] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    const newProduct = {
      name,
      parts,
      price: parseFloat(price),
      color,
      description,
      image,
      creationForm: {
        timeEnsable: parseFloat(timeEnsable),
        timePinter: parseFloat(timePinter),
        timePackaging: parseFloat(timePackaging),
      },
      category: {
        name: category,
      },
    };

    createToy(newProduct)
      .then(response => {
        console.log('Producto creado:', response.data);
        navigate('/admin');
      })
      .catch(error => {
        console.error('Error al crear el producto:', error);
      });
  };

  return (
    <div className="container mt-5">
      <div className="d-flex justify-content-start mb-3">
        <Link to="/admin" className="btn btn-outline-secondary">
          ← Volver al Panel de Administrador
        </Link>
      </div>
      <h2>Crear Producto</h2>
      <form onSubmit={handleSubmit}>
        <div className="row mb-3">
          <label htmlFor="name" className="col-sm-2 col-form-label">Nombre del Producto</label>
          <div className="col-sm-10">
            <input
              type="text"
              className="form-control"
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="row mb-3">
          <label htmlFor="parts" className="col-sm-2 col-form-label">Partes</label>
          <div className="col-sm-10">
            <input
              type="text"
              className="form-control"
              id="parts"
              value={parts}
              onChange={(e) => setParts(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="row mb-3">
          <label htmlFor="price" className="col-sm-2 col-form-label">Precio</label>
          <div className="col-sm-10">
            <input
              type="number"
              className="form-control"
              id="price"
              value={price}
              onChange={(e) => setPrice(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="row mb-3">
          <label htmlFor="color" className="col-sm-2 col-form-label">Color</label>
          <div className="col-sm-10">
            <input
              type="text"
              className="form-control"
              id="color"
              value={color}
              onChange={(e) => setColor(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="row mb-3">
          <label htmlFor="description" className="col-sm-2 col-form-label">Descripción</label>
          <div className="col-sm-10">
            <textarea
              className="form-control"
              id="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="row mb-3">
          <label htmlFor="image" className="col-sm-2 col-form-label">URL de la Imagen</label>
          <div className="col-sm-10">
            <input
              type="url"
              className="form-control"
              id="image"
              value={image}
              onChange={(e) => setImage(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="row mb-3">
          <label htmlFor="timeEnsable" className="col-sm-2 col-form-label">Tiempo de Ensamblaje (horas)</label>
          <div className="col-sm-10">
            <input
              type="number"
              className="form-control"
              id="timeEnsable"
              value={timeEnsable}
              onChange={(e) => setTimeEnsable(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="row mb-3">
          <label htmlFor="timePinter" className="col-sm-2 col-form-label">Tiempo de Pintura (horas)</label>
          <div className="col-sm-10">
            <input
              type="number"
              className="form-control"
              id="timePinter"
              value={timePinter}
              onChange={(e) => setTimePinter(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="row mb-3">
          <label htmlFor="timePackaging" className="col-sm-2 col-form-label">Tiempo de Empaque (horas)</label>
          <div className="col-sm-10">
            <input
              type="number"
              className="form-control"
              id="timePackaging"
              value={timePackaging}
              onChange={(e) => setTimePackaging(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="row mb-3">
          <label htmlFor="category" className="col-sm-2 col-form-label">Categoría</label>
          <div className="col-sm-10">
            <input
              type="text"
              className="form-control"
              id="category"
              value={category}
              onChange={(e) => setCategory(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="d-grid gap-2">
          <button type="submit" className="btn btn-primary">Crear Producto</button>
        </div>
      </form>
    </div>
  );
};

export default CreateProduct;
