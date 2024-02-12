import React, { useState, useEffect } from 'react';
import { getAllProducts, updateProduct, deleteProduct } from '../../../services/admin.services';
import AddProduct from './AddProduct';

const Product = () => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [editingProduct, setEditingProduct] = useState(null);
    const [editedData, setEditedData] = useState({});
    const [showAddProductForm, setShowAddProductForm] = useState(false);

    // Fetch all products from the server
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

    // Function to handle adding a new product
    const handleAddProduct = async (newProduct) => {
        try {
            // Add the new product to the product list
            setProducts([...products, newProduct]);
            // Hide the add product form
            setShowAddProductForm(false);
        } catch (error) {
            console.error('Error adding product:', error);
            // Handle error
        }
    };

    const handleEdit = (id) => {
        setEditingProduct(id);
        const productToEdit = products.find(product => product.id === id);
        setEditedData(productToEdit);
    };

    const handleSave = async (id) => {
        try {
            await updateProduct(id, editedData);
            setEditingProduct(null);
            // You may want to fetch updated product list here if necessary
        } catch (error) {
            console.error('Error updating product:', error);
            // Handle error
        }
    };

    const handleDelete = async (id) => {
        try {
            await deleteProduct(id);
            setProducts(products.filter(product => product.id !== id));
            // You may want to fetch updated product list here if necessary
        } catch (error) {
            console.error('Error deleting product:', error);
            // Handle error
        }
    };

    const handleInputChange = (field, value) => {
        setEditedData(prevState => ({
            ...prevState,
            [field]: value
        }));
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="container-lg">
            <div className="table-responsive">
                <div className="table-wrapper">
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-8">
                                <h2>Products</h2>
                            </div>
                            <div className="col-sm-4">
                                <button type="button" className="btn btn-info add-new" onClick={() => setShowAddProductForm(true)}><i className="fa fa-plus"></i> Add New</button>
                            </div>
                        </div>
                    </div>
                    {showAddProductForm && <AddProduct onAdd={handleAddProduct} />}
                    <table className="table table-bordered">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Pre-order Quantity</th>
                                <th>Category</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {products.map(product => (
                                <tr key={product.id}>
                                    <td>
                                        {editingProduct === product.id ? (
                                            <input type="text" className="form-control" value={editedData.name} onChange={(e) => handleInputChange('name', e.target.value)} />
                                        ) : (
                                            product.name
                                        )}
                                    </td>
                                    <td>
                                        {editingProduct === product.id ? (
                                            <input type="text" className="form-control" value={editedData.price} onChange={(e) => handleInputChange('price', e.target.value)} />
                                        ) : (
                                            product.price
                                        )}
                                    </td>
                                    <td>
                                        {editingProduct === product.id ? (
                                            <input type="text" className="form-control" value={editedData.quantity} onChange={(e) => handleInputChange('quantity', e.target.value)} />
                                        ) : (
                                            product.quantity
                                        )}
                                    </td>
                                    <td>
                                        {editingProduct === product.id ? (
                                            <input type="text" className="form-control" value={editedData.pre_order_quantity} onChange={(e) => handleInputChange('pre_order_quantity', e.target.value)} />
                                        ) : (
                                            product.pre_order_quantity
                                        )}
                                    </td>
                                    <td>
                                        {editingProduct === product.id ? (
                                            <input type="text" className="form-control" value={product.category.name} readOnly />
                                        ) : (
                                            product.category.name // Assuming category is an object with a 'name' property
                                        )}
                                    </td>

                                    <td>
                                        {editingProduct === product.id ? (
                                            <button onClick={() => handleSave(product.id)} className="btn btn-success">Save</button>
                                        ) : (
                                            <>
                                                <button onClick={() => handleEdit(product.id)} className="btn btn-primary">Edit</button>
                                                <button onClick={() => handleDelete(product.id)} className="btn btn-danger ml-2">Delete</button>
                                            </>
                                        )}
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Product;
