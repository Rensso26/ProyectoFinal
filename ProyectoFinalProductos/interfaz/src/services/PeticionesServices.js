// src/services/PeticionesServices.js
import axios from 'axios';

const REST_API_BASE_URL = "http://localhost:8080/fabric/peticion/";

export const createPeticion = (peticiones) => axios.post(REST_API_BASE_URL, peticiones);

export const listPeticiones = () => axios.get(REST_API_BASE_URL);

