// src/api/userService.js
import axiosInstance from './axiosConfig';

export const getAllUsers = async () => {
    const response = await axiosInstance.get('/users');
    return response.data;
};

export const getUserByName = async (name) => {
    const response = await axiosInstance.get(`/users/${name}`);
    return response.data;
};

export const createUser = async (user) => {
    const response = await axiosInstance.post('/users', user);
    return response.data;
};

export const updateUser = async (name, user) => {
    const response = await axiosInstance.put(`/users/${name}`, user);
    return response.data;
};

export const deleteUser = async (name) => {
    const response = await axiosInstance.delete(`/users/${name}`);
    return response.data;
};
