import http from './http-common';

const getAllProducts = () => {
  return http.get(`/customer/products`);
};

const addToCart = (customerId, cartItemRequest) => {
  return http.post(`/customer/cart/${customerId}/add`, cartItemRequest);
};

export { getAllProducts, addToCart };
