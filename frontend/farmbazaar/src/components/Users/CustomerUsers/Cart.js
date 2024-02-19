import React, { useState, useEffect } from 'react';
import { getCartItems, checkoutOrder } from '../../../services/customer.services';

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [orderType, setOrderType] = useState('regular');
  const [deliveryDate, setDeliveryDate] = useState('');
  const [deliveryAddress, setDeliveryAddress] = useState(''); // State for delivery address
  const [checkedOut, setCheckedOut] = useState(false);
  const [userData, setUserData] = useState(null); // State to store user data retrieved from session

  useEffect(() => {
    // Retrieve user data from session storage
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    setUserData(userData);
    
    // Fetch cart items using customer ID
    if (userData) {
      getCartItems(userData.id)
        .then(response => {
          setCartItems(response.data);
          setLoading(false);
        })
        .catch(error => {
          setError(error);
          setLoading(false);
        });
    }
  }, []);

  useEffect(() => {
    // Calculate total price when cart items change
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
        deliveryAddress: deliveryAddress || userData.address // Use entered address or pre-populated address from session
      };

      checkoutOrder(userData.id, checkoutRequest)
        .then(response => {
          setCheckedOut(true);
          console.log(response.data); // Log the response from the server
        })
        .catch(error => {
          console.error(error); // Log any errors
        });
    }
  };

  // Group cart items by product ID
  const groupedCartItems = cartItems.reduce((acc, item) => {
    const existingItem = acc.find(group => group.product.id === item.product.id);
    if (existingItem) {
      existingItem.quantity += item.quantity;
      existingItem.totalPrice += item.price * item.quantity;
    } else {
      acc.push({
        product: item.product,
        quantity: item.quantity,
        totalPrice: item.price * item.quantity
      });
    }
    return acc;
  }, []);

  return (
    <section className="h-100 gradient-custom">
      <div className="container py-5">
        <div className="row row-cols-1 row-cols-md-4 g-4">
          {groupedCartItems.map(group => (
            <div className="col" key={group.product.id}>
              <div className="card h-100" style={{ maxWidth: '250px' }}>
                {/* Display the image if available */}
                {group.product.imageBase64 && (
                  <img
                    src={`data:image/jpeg;base64,${group.product.imageBase64}`}
                    className="card-img-top"
                    alt={group.product.name}
                    style={{ height: '200px', objectFit: 'cover' }} // Set fixed height and object fit
                  />
                )}
                <div className="card-body">
                  <h5 className="card-title">{group.product.name}</h5>
                  <p className="card-text text-success">Price: ${group.product.price}</p>
                  <p className="card-text">Quantity: {group.quantity}</p>
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
                  min={(new Date()).toISOString().split('T')[0]} // Set the minimum date to the current date
                  max={(new Date(Date.now() + 5 * 24 * 60 * 60 * 1000)).toISOString().split('T')[0]} // Set the maximum date to 5 days from the current date
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
                placeholder={userData ? userData.address : 'Enter delivery address'} // Display pre-populated address or placeholder
              />
            </div>
            <button type="button" className="btn btn-primary btn-lg btn-block" onClick={handleCheckout}>
              Go to Checkout
            </button>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Cart;
