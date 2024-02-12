import React, { useState } from 'react';
import { createFarmerUser } from '../../../services/admin.services';

const AddFarmerUser = ({ onClose }) => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        fname: '',
        lname: '',
        phno: '',
        address: ''
    });

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
            await createFarmerUser(formData);
            onClose(); // Call onClose function to close the AddFarmerUser component
            // Handle successful user creation
            console.log('Farmer user created successfully:', formData);
        } catch (error) {
            console.error('Error creating farmer user:', error);
            // Handle error
        }
    };

    return (
        <div className="container-lg">
            <h2>Add New Farmer User</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="username">Username</label>
                    <input type="text" className="form-control" id="username" name="username" value={formData.username} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input type="password" className="form-control" id="password" name="password" value={formData.password} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="fname">First Name</label>
                    <input type="text" className="form-control" id="fname" name="fname" value={formData.fname} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="lname">Last Name</label>
                    <input type="text" className="form-control" id="lname" name="lname" value={formData.lname} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="phno">Phone Number</label>
                    <input type="text" className="form-control" id="phno" name="phno" value={formData.phno} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="address">Address</label>
                    <input type="text" className="form-control" id="address" name="address" value={formData.address} onChange={handleChange} />
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </div>
    );
};

export default AddFarmerUser;
