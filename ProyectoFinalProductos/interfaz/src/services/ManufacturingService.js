// src/services/ManufacturingService.js
import axios from 'axios';

const REST_API_BASE_URL = "http://localhost:8080/fabric/";

export const createManufacturing = (toyId, copies) => axios.post(`${REST_API_BASE_URL}fabricate/${toyId}/${copies}`);
