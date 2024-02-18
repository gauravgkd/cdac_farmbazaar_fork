import http from './http-common';

const getAllProducts = () => {
  return http.get(`/customer/products`);
};

const addToCart = (customerId, cartItemRequest) => {
  return http.post(`/customer/cart/${customerId}/add`, cartItemRequest);
};

const getCartItems = (customerId) => {
  return http.get(`/customer/cart/${customerId}/items`);
};

export { getAllProducts, addToCart, getCartItems};
