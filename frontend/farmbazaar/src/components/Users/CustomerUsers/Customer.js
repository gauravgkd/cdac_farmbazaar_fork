import React, { useState, useEffect } from 'react';
import { getAllProducts, addToCart } from '../../../services/customer.services';

const Customer = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedCategory, setSelectedCategory] = useState('All');
  const [productQuantities, setProductQuantities] = useState({}); // State to track quantities for each product

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await getAllProducts();
        setProducts(response.data);
        // Initialize product quantities with default values
        const initialQuantities = response.data.reduce((acc, product) => {
          acc[product.id] = 1; // Default quantity is 1
          return acc;
        }, {});
        setProductQuantities(initialQuantities);
      } catch (error) {
        console.error('Error fetching products:', error);
        setError('Error fetching products. Please try again later.');
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  // Get unique categories from products
  const categories = [...new Set(products.map(product => product.category.name))];

  // Filter products based on selected category
  const filteredProducts = selectedCategory === 'All' ? products : products.filter(product => product.category.name === selectedCategory);

  const handleAddToCart = async (productId) => {
    try {
      const customerId = JSON.parse(sessionStorage.getItem('userData')).id; // Get customer ID from session
      const quantity = productQuantities[productId]; // Get quantity for the selected product
      const response = await addToCart(customerId, { productId, quantity });
      console.log(response.data); // Log the response from the server
      // You can add further logic here such as showing a success message
    } catch (error) {
      console.error('Error adding product to cart:', error);
      // You can handle errors here, such as displaying an error message to the user
    }
  };

  const handleQuantityChange = (productId, newQuantity) => {
    setProductQuantities(prevQuantities => ({
      ...prevQuantities,
      [productId]: newQuantity,
    }));
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-12 mb-4">
          <div className="form-group">
            <label htmlFor="categorySelect">Filter by Category:</label>
            <select
              id="categorySelect"
              className="form-control"
              value={selectedCategory}
              onChange={(e) => setSelectedCategory(e.target.value)}
            >
              <option value="All">All</option>
              {categories.map(category => (
                <option key={category} value={category}>{category}</option>
              ))}
            </select>
          </div>
        </div>
      </div>
      <div className="row">
        {loading ? (
          <div>Loading...</div>
        ) : error ? (
          <div>Error: {error}</div>
        ) : (
          filteredProducts.map(product => (
            <div key={product.id} className="col-md-4 mb-4">
              <div className="card" style={{ width: '18rem' }}>
                <img
                  src={`data:image/jpeg;base64,${product.imageBase64}`}
                  alt={product.name}
                  style={{ width: '100%', height: '200px', objectFit: 'cover' }}
                />
                <div className="card-body">
                  <h5 className="card-title">{product.name}</h5>
                  <p className="card-text">
                    Price: {product.price}<br />
                    Quantity: {product.quantity}<br />
                    Pre-order Quantity: {product.pre_order_quantity}
                  </p>
                  <div className="form-group">
                    <input 
                      type="number" 
                      className="form-control" 
                      placeholder="Enter quantity" 
                      value={productQuantities[product.id]}
                      onChange={(e) => handleQuantityChange(product.id, parseInt(e.target.value))}
                    />
                  </div>
                  <button 
                    className="btn btn-primary"
                    onClick={() => handleAddToCart(product.id)}
                  >
                    Add to Cart
                  </button>
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Customer;
