// src/services/CategoryServices.js
import axios from 'axios';

const REST_API_BASE_URL = "http://localhost:8080/fabric/category/all";

export const listCategories = () => axios.get(REST_API_BASE_URL);
