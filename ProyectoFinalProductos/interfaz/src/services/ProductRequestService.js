import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/product-requests';

const ProductRequestService = {
  getPendingRequests: async () => {
    return axios.get(`${API_BASE_URL}/pending`);
  },

  approveRequest: async (id) => {
    return axios.put(`${API_BASE_URL}/approve/${id}`);
  },

  rejectRequest: async (id) => {
    return axios.put(`${API_BASE_URL}/reject/${id}`);
  }
};

export default ProductRequestService;
