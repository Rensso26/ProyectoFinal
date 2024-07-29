import React, { useEffect, useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { ProductContext } from '../context/ProductContext';
import { listPeticiones } from '../services/PeticionesServices';
import { getToyById } from '../services/ToyServices';
import { createManufacturing } from '../services/ManufacturingService';
import { getMessage } from '../services/MessageService'; // Importar el servicio de mensajes
import 'bootstrap/dist/css/bootstrap.min.css';

const AdminPanel = ({ user, onLogout }) => {
  const { setSelectedProducts } = useContext(ProductContext);
  const [currentTime, setCurrentTime] = useState(new Date());
  const [peticiones, setPeticiones] = useState([]);
  const [toys, setToys] = useState({});
  const [fabricationMessage, setFabricationMessage] = useState(""); // Estado para el mensaje de fabricación
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  useEffect(() => {
    listPeticiones()
      .then(response => {
        setPeticiones(response.data);
        response.data.forEach(peticion => {
          if (peticion.id) {
            getToyById(peticion.id)
              .then(response => {
                setToys(prevToys => ({ ...prevToys, [peticion.id]: response.data }));
              })
              .catch(error => {
                console.error('Error al obtener el juguete:', error);
              });
          } else {
            console.error('ID del juguete es undefined en la petición:', peticion);
          }
        });
      })
      .catch(error => {
        console.error('Error al obtener las peticiones:', error);
      });
  }, []);

  useEffect(() => {
    const messageInterval = setInterval(() => {
      getMessage()
        .then(response => {
          setFabricationMessage(response.data.content);
        })
        .catch(error => {
          console.error('Error al obtener el mensaje de fabricación:', error);
        });
    }, 1000);

    return () => clearInterval(messageInterval);
  }, []);

  const handleCreateProduct = () => {
    navigate('/create-product');
  };

  const handleManufactureProduct = () => {
    navigate('/manufacture-product');
  };

  const handleAcceptRequest = (request) => {
    createManufacturing(request.id, request.cantidad)
      .then(() => {
        console.log("Aceptada solicitud de fabricación:", request);
        setPeticiones(peticiones.filter(peticion => peticion.id !== request.id));
      })
      .catch(error => {
        console.error('Error al aceptar la solicitud de fabricación:', error);
      });
  };

  const handleRejectRequest = (request) => {
    console.log("Rechazada solicitud de fabricación:", request);
    setPeticiones(peticiones.filter(peticion => peticion.id !== request.id));
  };

  return (
    <div className="container mt-5">
      <div className="d-flex justify-content-end mb-3">
        <button className="btn btn-danger" onClick={onLogout}>
          Cerrar Sesión
        </button>
      </div>

      <div className="card shadow">
        <div className="card-header bg-primary text-white text-center">
          <h2>Panel de Administrador</h2>
        </div>
        <div className="card-body">
          <p className="text-center">Bienvenido, {user}</p>

          <div className="d-flex justify-content-between">
            <p>Fecha actual: {currentTime.toLocaleDateString()}</p>
            <p>Hora actual: {currentTime.toLocaleTimeString()}</p>
          </div>

          <div className="d-flex justify-content-around mt-4">
            <button className="btn btn-success" onClick={handleCreateProduct}>
              Crear Producto
            </button>
            <button className="btn btn-warning" onClick={handleManufactureProduct}>
              Fabricar Producto
            </button>
          </div>

          <div className="mt-5">
            <h3>Solicitudes de Fabricación</h3>
            <ul className="list-group">
              {peticiones.length > 0 ? (
                peticiones.map((peticion, index) => (
                  <li key={index} className="list-group-item d-flex justify-content-between align-items-center">
                    {toys[peticion.id] ? toys[peticion.id].name : 'Cargando...'} - {peticion.cantidad}
                    <div>
                      <button className="btn btn-success btn-sm mr-2" onClick={() => handleAcceptRequest(peticion)}>Aceptar</button>
                      <button className="btn btn-danger btn-sm" onClick={() => handleRejectRequest(peticion)}>Rechazar</button>
                    </div>
                  </li>
                ))
              ) : (
                <p>No hay solicitudes de fabricación.</p>
              )}
            </ul>
          </div>

          <div className="mt-5">
            <h3>Mensaje de Fabricación</h3>
            <p>{fabricationMessage}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminPanel;
