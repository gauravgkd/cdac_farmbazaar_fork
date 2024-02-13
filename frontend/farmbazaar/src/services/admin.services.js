// admin.services.js
import http from './http-common';

const createAdminUser = (data) => {
  return http.post('/admin/admin-users', data);
};

const updateAdminUser = (id, data) => {
  return http.put(`/admin/admin-users/${id}`, data);
};

const deleteAdminUser = (id) => {
  return http.delete(`/admin/admin-users/${id}`);
};

const getAllAdminUsers = () => {
  return http.get('/admin/admin-users');
};

const createFarmerUser = (data) => {
  return http.post('/admin/farmer-users', data);
};

const updateFarmerUser = (id, data) => {
  return http.put(`/admin/farmer-users/${id}`, data);
};

const deleteFarmerUser = (id) => {
  return http.delete(`/admin/farmer-users/${id}`);
};

const getAllFarmerUsers = () => {
  return http.get('/admin/farmer-users');
};

const createDeliveryPartnerUser = (data) => {
  return http.post('/admin/delivery-partner-users', data);
};

const updateDeliveryPartnerUser = (id, data) => {
  return http.put(`/admin/delivery-partner-users/${id}`, data);
};

const deleteDeliveryPartnerUser = (id) => {
  return http.delete(`/admin/delivery-partner-users/${id}`);
};

const getAllDeliveryPartnerUsers = () => {
  return http.get('/admin/delivery-partner-users');
};

const createCustomerUser = (data) => {
  return http.post('/admin/customer-users', data);
};

const updateCustomerUser = (id, data) => {
  return http.put(`/admin/customer-users/${id}`, data);
};

const deleteCustomerUser = (id) => {
  return http.delete(`/admin/customer-users/${id}`);
};

const getAllCustomerUsers = () => {
  return http.get('/admin/customer-users');
};

const createCategory = (data) => {
  return http.post('/admin/categories', data);
};

const updateCategory = (id, data) => {
  return http.put(`/admin/categories/${id}`, data);
};

const deleteCategory = (id) => {
  return http.delete(`/admin/categories/${id}`);
};

const getAllCategories = () => {
  return http.get('/admin/categories');
};

const createProduct = (data) => {
  return http.post('/admin/products', data);
};

const updateProduct = (id, data) => {
  return http.put(`/admin/products/${id}`, data);
};

const deleteProduct = (id) => {
  return http.delete(`/admin/products/${id}`);
};

const getAllProducts = () => {
  return http.get('/admin/products');
};

const assignProductsToFarmer = (farmerId, productIds) => {
  return http.post(`/admin/assign/${farmerId}`, productIds);
};

const getProductsByFarmerId = (farmerId) => {
  return http.get(`/admin/${farmerId}/products`);
};

export {
  createAdminUser,
  updateAdminUser,
  deleteAdminUser,
  getAllAdminUsers,
  createFarmerUser,
  updateFarmerUser,
  deleteFarmerUser,
  getAllFarmerUsers,
  createDeliveryPartnerUser,
  updateDeliveryPartnerUser,
  deleteDeliveryPartnerUser,
  getAllDeliveryPartnerUsers,
  createCustomerUser,
  updateCustomerUser,
  deleteCustomerUser,
  getAllCustomerUsers,
  createCategory,
  updateCategory,
  deleteCategory,
  getAllCategories,
  createProduct,
  updateProduct,
  deleteProduct,
  getAllProducts,
  assignProductsToFarmer,
  getProductsByFarmerId
};
