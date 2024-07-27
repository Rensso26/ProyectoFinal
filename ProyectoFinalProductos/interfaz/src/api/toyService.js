// src/api/toyService.js
import axiosInstance from './axiosConfig';

export const getAllToys = async () => {
    const response = await axiosInstance.get('/toys');
    return response.data;
};

export const getToyByName = async (name) => {
    const response = await axiosInstance.get(`/toys/${name}`);
    return response.data;
};

export const createToy = async (toy) => {
    const response = await axiosInstance.post('/toys', toy);
    return response.data;
};

export const updateToy = async (name, toy) => {
    const response = await axiosInstance.put(`/toys/${name}`, toy);
    return response.data;
};

export const deleteToy = async (name) => {
    const response = await axiosInstance.delete(`/toys/${name}`);
    return response.data;
};
