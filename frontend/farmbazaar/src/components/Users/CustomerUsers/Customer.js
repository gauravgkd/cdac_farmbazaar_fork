import React, { useState, useEffect } from 'react';
import { getAllProducts } from '../../../services/customer.services';

const Customer = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedCategory, setSelectedCategory] = useState('All');

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await getAllProducts();
        setProducts(response.data);
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
                    <input type="number" className="form-control" placeholder="Enter quantity" />
                  </div>
                  <button className="btn btn-success mr-2">Pre-order</button>
                  <button className="btn btn-primary">Add to Cart</button>
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

/*
Author: Shubham Samarth
Date: February 18, 2024
Description: This component displays products for the customer.
*/