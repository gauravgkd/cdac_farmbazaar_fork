import React, { useState, useEffect } from 'react';
import { Badge, Card, ListGroup } from 'react-bootstrap';
import { getOrdersByCustomerId } from '../../../services/customer.services';

const OrderList = () => {
  const [orders, setOrders] = useState([]);
  const [userData, setUserData] = useState(null);

  const deliveryStatusMilestones = [
    'Confirmed Order',
    'Processing Order',
    'Order Dispatched',
    'Order in Route',
    'Out for Delivery',
    'Delivered'
  ];

  const getStatusBadgeVariant = (status) => {
    switch (status) {
      case 'Confirmed Order':
        return 'secondary';
      case 'Processing Order':
        return 'primary';
      case 'Order Dispatched':
        return 'info';
      case 'Order in Route':
        return 'warning';
      case 'Out for Delivery':
        return 'danger';
      case 'Delivered':
        return 'success';
      default:
        return 'secondary';
    }
  };

  useEffect(() => {
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    setUserData(userData);

    if (userData) {
      const fetchOrders = async () => {
        try {
          const response = await getOrdersByCustomerId(userData.id);
          console.log('Fetched orders:', response.data);
          setOrders(response.data);
        } catch (error) {
          console.error('Error fetching orders:', error);
        }
      };

      fetchOrders();
    }
  }, []);

  return (
    <>
      {orders.length === 0 ? (
        <div>No orders found</div>
      ) : (
        orders.map((order) => (
          <Card key={order.id} className="my-3">
            <Card.Header>Order #{order.id}</Card.Header>
            <Card.Body>
              <Card.Text>
                <strong>Total Amount:</strong> ${order.totalAmount}
              </Card.Text>
              <ListGroup variant="flush">
                <ListGroup.Item>
                  <strong>Delivery Address:</strong> {order.deliveryAddress}
                </ListGroup.Item>
                <ListGroup.Item>
                  <strong>Placed Date:</strong>{' '}
                  {order.placedDate ? new Date(order.placedDate).toLocaleDateString() : 'Not specified'}
                </ListGroup.Item>
                <ListGroup.Item>
                  <strong>Expected Delivery Date:</strong>{' '}
                  {order.expectedDeliveryDate ? new Date(order.expectedDeliveryDate).toLocaleDateString() : 'Not specified'}
                </ListGroup.Item>
                <ListGroup.Item>
                  <strong>Delivery Date:</strong>{' '}
                  {order.deliveryDate ? new Date(order.deliveryDate).toLocaleDateString() : 'Not specified'}
                </ListGroup.Item>
                <ListGroup.Item>
                  <strong>Delivery Status:</strong>{' '}
                  <Badge bg={getStatusBadgeVariant(order.deliveryStatus)}>
                    {order.deliveryStatus}
                  </Badge>
                </ListGroup.Item>
              </ListGroup>
            </Card.Body>
          </Card>
        ))
      )}
    </>
  );
};

export default OrderList;
