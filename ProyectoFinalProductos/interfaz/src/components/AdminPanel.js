import { useEffect, useState, useContext } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import { ProductContext } from '../context/ProductContext';
import { listPeticiones } from '../services/PeticionesServices';
import { getToyById } from '../services/ToyServices';
import { createManufacturing } from '../services/ManufacturingService';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

const AdminPanel = ({ user, onLogout }) => {
  const { setSelectedProducts } = useContext(ProductContext);
  const [currentTime, setCurrentTime] = useState(new Date());
  const [peticiones, setPeticiones] = useState([]);
  const [toys, setToys] = useState({});
  const [stompClient, setStompClient] = useState(null);
  const [subscription, setSubscription] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Actualizar la hora cada segundo
    const timer = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);

    // Limpiar el intervalo cuando el componente se desmonte
    return () => clearInterval(timer);
  }, []);

  useEffect(() => {
    // Configurar STOMP client
    const client = new Client({
      brokerURL: 'ws://localhost:8080/fabrication-websocket', // Reemplaza con la URL de tu servidor WebSocket
      connectHeaders: {
        login: 'user',
        passcode: 'password',
      },
      debug: function (str) {
        console.log(str);
      },
      onConnect: () => {
        console.log('Connected to WebSocket');
        const sub = client.subscribe('/topic/fabrication', (message) => {
          console.log('Received message:', message.body);
          // Actualiza tu estado o maneja el mensaje recibido aquí
        });
        setSubscription(sub);
      },
      onStompError: (frame) => {
        console.error('Broker reported error:', frame);
      },
      onWebSocketError: (error) => {
        console.error('WebSocket error:', error);
      },
    });

    client.activate();
    setStompClient(client);

    // Cleanup on unmount
    return () => {
      if (subscription) {
        subscription.unsubscribe();
      }
      if (client) {
        client.deactivate();
      }
    };
  }, []);

  useEffect(() => {
    // Obtener las peticiones cuando el componente se monte
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

  // Navegar a la página de creación de productos
  const handleCreateProduct = () => {
    navigate('/create-product');
  };

  // Navegar a la página de fabricación de productos
  const handleManufactureProduct = () => {
    navigate('/manufacture-product');
  };

  // Aceptar una solicitud de fabricación
  const handleAcceptRequest = (request) => {
    createManufacturing(request.id, request.cantidad)
      .then(() => {
        console.log("Aceptada solicitud de fabricación:", request);
        // Ejemplo: eliminar la solicitud aceptada de la lista
        setPeticiones(peticiones.filter(peticion => peticion.id !== request.id));
      })
      .catch(error => {
        console.error('Error al aceptar la solicitud de fabricación:', error);
      });
  };

  // Rechazar una solicitud de fabricación
  const handleRejectRequest = (request) => {
    console.log("Rechazada solicitud de fabricación:", request);
    // Ejemplo: eliminar la solicitud rechazada de la lista
    setPeticiones(peticiones.filter(peticion => peticion.id !== request.id));
  };

  return (
    <div className="container mt-5">
      {/* Botón de cierre de sesión */}
      <div className="d-flex justify-content-end mb-3">
        <button className="btn btn-danger" onClick={onLogout}>
          Cerrar Sesión
        </button>
      </div>

      {/* Tarjeta del panel de administrador */}
      <div className="card shadow">
        <div className="card-header bg-primary text-white text-center">
          <h2>Panel de Administrador</h2>
        </div>
        <div className="card-body">
          {/* Mensaje de bienvenida */}
          <p className="text-center">Bienvenido, {user}</p>

          {/* Mostrar la fecha y hora actual */}
          <div className="d-flex justify-content-between">
            <p>Fecha actual: {currentTime.toLocaleDateString()}</p>
            <p>Hora actual: {currentTime.toLocaleTimeString()}</p>
          </div>

          {/* Botones de acciones */}
          <div className="d-flex justify-content-around mt-4">
            <button className="btn btn-success" onClick={handleCreateProduct}>
              Crear Producto
            </button>
            <button className="btn btn-warning" onClick={handleManufactureProduct}>
              Fabricar Producto
            </button>
          </div>

          {/* Listado de solicitudes de fabricación */}
          <div className="mt-5">
            <h3>Solicitudes de Fabricación</h3>
            <ul className="list-group">
              {peticiones.length > 0 ? (
                peticiones.map((peticion, index) => (
                  <li key={index} className="list-group-item d-flex justify-content-between align-items-center">
                    {toys[peticion.id] ? toys[peticion.id].name : 'Cargando...'} - {peticion.cantidad}
                    <div>
                      <button
                        className="btn btn-success btn-sm mr-2"
                        onClick={() => handleAcceptRequest(peticion)}
                        disabled={peticion.processed}
                      >
                        Aceptar
                      </button>
                      <button
                        className="btn btn-danger btn-sm"
                        onClick={() => handleRejectRequest(peticion)}
                        disabled={peticion.processed}
                      >
                        Rechazar
                      </button>
                    </div>
                  </li>
                ))
              ) : (
                <p>No hay solicitudes de fabricación.</p>
              )}
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminPanel;
