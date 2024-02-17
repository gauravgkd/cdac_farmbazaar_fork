// customer.services.js
import http from './http-common';

const getAllProducts = () => {
  return http.get(`/customer/products`);
};



export { getAllProducts };

/*
Author: Shubham Samarth
Date: February 18, 2024
Description: This component provides mappings to the controller at backend.
*/