// delivery-partner.services.js
import http from './http-common';

const getAllOrdersForDeliveryPartner = (deliveryPartnerId) => {
  return http.get(`/delivery-partner/${deliveryPartnerId}/orders`);
};

const updateDeliveryStatus = (orderId, deliveryStatus) => {
  return http.put(`/delivery-partner/orders/${orderId}`, deliveryStatus);
};

export { getAllOrdersForDeliveryPartner, updateDeliveryStatus };
