import React, { useState, useEffect } from 'react';
import { createProduct } from '../../../services/admin.services';

const AddProduct = ({ onAdd }) => {
    const [formData, setFormData] = useState({
        name: '',
        price: '',
        quantity: '',
        preOrderQuantity: '',
        category: '' // This will hold the selected category object
    });
    const [categories, setCategories] = useState([]); // State to hold the fetched categories

    // Fetch categories from the backend when the component mounts
    useEffect(() => {
        const fetchCategories = async () => {
            try {
                // Make API call to fetch categories
                const response = await fetch('http://localhost:8080/admin/categories');
                const data = await response.json();
                setCategories(data);
            } catch (error) {
                console.error('Error fetching categories:', error);
                // Handle error
            }
        };

        fetchCategories();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Retrieve the selected category object
            const selectedCategory = categories.find(category => category.id === Number(formData.category));
            // Create the payload with the category object included
            const payload = {
                ...formData,
                category: selectedCategory
            };
            const response = await createProduct(payload);
            onAdd(response.data); // Notify parent component about the new product
            setFormData({
                name: '',
                price: '',
                quantity: '',
                preOrderQuantity: '',
                category: ''
            });
        } catch (error) {
            console.error('Error adding product:', error);
            // Handle error
        }
    };

    return (
        <div className="container">
            <h2>Add New Product</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Name</label>
                    <input type="text" className="form-control" id="name" name="name" value={formData.name} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label htmlFor="price" className="form-label">Price</label>
                    <input type="text" className="form-control" id="price" name="price" value={formData.price} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label htmlFor="quantity" className="form-label">Quantity</label>
                    <input type="text" className="form-control" id="quantity" name="quantity" value={formData.quantity} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label htmlFor="preOrderQuantity" className="form-label">Pre-order Quantity</label>
                    <input type="text" className="form-control" id="preOrderQuantity" name="preOrderQuantity" value={formData.preOrderQuantity} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label htmlFor="category" className="form-label">Category</label>
                    <select className="form-select" id="category" name="category" value={formData.category} onChange={handleChange} required>
                        <option value="">Select category...</option>
                        {categories.map(category => (
                            <option key={category.id} value={category.id}>{category.name}</option>
                        ))}
                    </select>
                </div>
                <button type="submit" className="btn btn-primary">Add</button>
            </form>
        </div>
    );
};

export default AddProduct;
