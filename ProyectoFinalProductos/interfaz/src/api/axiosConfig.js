// src/api/axiosConfig.js
import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api', // Cambia esto a la URL base de tu API
    headers: {
        'Content-Type': 'application/json',
    },
});

export default axiosInstance;
