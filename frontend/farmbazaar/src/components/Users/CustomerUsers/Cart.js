import React, { useState, useEffect } from 'react';
import { getCartItems, checkoutOrder } from '../../../services/customer.services';
import Payment from './Payment';

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [orderType, setOrderType] = useState('regular');
  const [deliveryDate, setDeliveryDate] = useState('');
  const [deliveryAddress, setDeliveryAddress] = useState('');
  const [userData, setUserData] = useState(null);
  const [showPayment, setShowPayment] = useState(false);

  useEffect(() => {
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    setUserData(userData);

    if (userData) {
      getCartItems(userData.id)
        .then(response => {
          setCartItems(response.data);
        })
        .catch(error => {
          console.error(error);
        });
    }
  }, []);

  useEffect(() => {
    const totalPrice = cartItems.reduce((acc, item) => acc + (item.price * item.quantity), 0);
    setTotalPrice(totalPrice);
  }, [cartItems]);

  const handleOrderTypeChange = (event) => {
    setOrderType(event.target.value);
  };

  const handleDeliveryDateChange = (event) => {
    setDeliveryDate(event.target.value);
  };

  const handleDeliveryAddressChange = (event) => {
    setDeliveryAddress(event.target.value);
  };

  const handleCheckout = () => {
    // Call the checkout service with customer ID and checkout request data
    if (userData) {
      const checkoutRequest = {
        orderType: orderType,
        deliveryDate: deliveryDate,
        deliveryAddress: deliveryAddress || userData.address
      };

      checkoutOrder(userData.id, checkoutRequest)
        .then(response => {
          console.log(response.data);
        })
        .catch(error => {
          console.error(error);
        });
    }
  };

  return (
    <section className="h-100 gradient-custom">
      <div className="container py-5">
        <div className="row row-cols-1 row-cols-md-4 g-4">
          {cartItems.map((item, index) => (
            <div className="col" key={index}>
              <div className="card h-100" style={{ maxWidth: '250px' }}>
                {item.product.imageBase64 && (
                  <img
                    src={`data:image/jpeg;base64,${item.product.imageBase64}`}
                    className="card-img-top"
                    alt={item.product.name}
                    style={{ height: '200px', objectFit: 'cover' }}
                  />
                )}
                <div className="card-body">
                  <h5 className="card-title">{item.product.name}</h5>
                  <p className="card-text text-success">Price: ${item.product.price}</p>
                  <p className="card-text">Quantity: {item.quantity}</p>
                </div>
              </div>
            </div>
          ))}
        </div>
        <div className="card">
          <div className="card-body">
            <h5 className="card-title">Total Amount</h5>
            <p className="card-text">Total Price: ${totalPrice}</p>
            <div className="form-group">
              <label htmlFor="orderType">Order Type:</label>
              <select id="orderType" className="form-control" value={orderType} onChange={handleOrderTypeChange}>
                <option value="regular">Regular</option>
                <option value="pre-order">Pre-order</option>
              </select>
            </div>
            {orderType === 'pre-order' && (
              <div className="form-group">
                <label htmlFor="deliveryDate">Delivery Date (Max 5 days from today):</label>
                <input
                  type="date"
                  id="deliveryDate"
                  className="form-control"
                  value={deliveryDate}
                  onChange={handleDeliveryDateChange}
                  min={(new Date()).toISOString().split('T')[0]}
                  max={(new Date(Date.now() + 5 * 24 * 60 * 60 * 1000)).toISOString().split('T')[0]}
                />
              </div>
            )}
            <div className="form-group">
              <label htmlFor="deliveryAddress">Delivery Address:</label>
              <input
                type="text"
                id="deliveryAddress"
                className="form-control"
                value={deliveryAddress}
                onChange={handleDeliveryAddressChange}
                placeholder={userData ? userData.address : 'Enter delivery address'}
              />
            </div>
            <button type="button" className="btn btn-primary btn-lg btn-block" onClick={() => setShowPayment(true)}>
              Go to Checkout
            </button>
            {showPayment && (
              <Payment totalPrice={totalPrice} onPayAmount={handleCheckout} />
            )}
          </div>
        </div>
      </div>
    </section>
  );
};

export default Cart;
