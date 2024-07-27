
import axiosInstance from './axiosConfig';

export const getAllCategories = async () => {
    const response = await axiosInstance.get('/categories');
    return response.data;
};

export const getCategoryByName = async (name) => {
    const response = await axiosInstance.get(`/categories/${name}`);
    return response.data;
};

export const createCategory = async (category) => {
    const response = await axiosInstance.post('/categories', category);
    return response.data;
};

export const updateCategory = async (name, category) => {
    const response = await axiosInstance.put(`/categories/${name}`, category);
    return response.data;
};

export const deleteCategory = async (name) => {
    const response = await axiosInstance.delete(`/categories/${name}`);
    return response.data;
};
