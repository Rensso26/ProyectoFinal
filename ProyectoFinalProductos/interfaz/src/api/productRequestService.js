// src/api/productRequestService.js
import axiosInstance from './axiosConfig';

export const getAllProductRequests = async () => {
    const response = await axiosInstance.get('/productRequests');
    return response.data;
};

export const getProductRequestById = async (id) => {
    const response = await axiosInstance.get(`/productRequests/${id}`);
    return response.data;
};

export const createProductRequest = async (productRequest) => {
    const response = await axiosInstance.post('/productRequests', productRequest);
    return response.data;
};

export const updateProductRequest = async (id, productRequest) => {
    const response = await axiosInstance.put(`/productRequests/${id}`, productRequest);
    return response.data;
};

export const deleteProductRequest = async (id) => {
    const response = await axiosInstance.delete(`/productRequests/${id}`);
    return response.data;
};

export const getPendingProductRequests = async () => {
    const response = await axiosInstance.get('/productRequests/status/pending');
    return response.data;
};

export const approveProductRequest = async (id) => {
    const response = await axiosInstance.post(`/productRequests/${id}/approve`);
    return response.data;
};

export const rejectProductRequest = async (id) => {
    const response = await axiosInstance.post(`/productRequests/${id}/reject`);
    return response.data;
};
