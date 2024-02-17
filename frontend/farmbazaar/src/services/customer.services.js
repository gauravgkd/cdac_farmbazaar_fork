// customer.services.js
import http from './http-common';

const getAllProducts = () => {
  return http.get(`/customer/products`);
};



export { getAllProducts };

