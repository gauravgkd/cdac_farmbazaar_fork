import React, { useState, useEffect } from 'react';
import { getCartItems } from '../../../services/customer.services';

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Retrieve customer ID from session storage
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    const customerId = userData ? userData.id : null;

    // Fetch cart items using customer ID
    if (customerId) {
      getCartItems(customerId)
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
            <button type="button" className="btn btn-primary btn-lg btn-block">
              Go to Checkout
            </button>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Cart;
