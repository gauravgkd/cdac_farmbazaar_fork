import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import 'bootstrap-icons/font/bootstrap-icons.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Admin from './components/Users/AdminUsers/Admin';
import AdminUsers from './components/Users/AdminUsers/AdminUsers';
import FarmerUsers from './components/Users/AdminUsers/FarmerUsers';
import AssignProduct from './components/Users/AdminUsers/AssignProduct';
import Category from './components/Users/AdminUsers/Category';
import Product from './components/Users/AdminUsers/Product';
import SignUp from './pages/SignUp';

function App() {
  return (
    <>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Admin/>} />
        <Route path="/admin/admin-users" element={<AdminUsers/>} />
        <Route path="/admin/farmer-users" element={<FarmerUsers/>} />
        <Route path="/admin/assign-product-to-farmer" element={<AssignProduct/>} />
        <Route path="/admin/categories" element={<Category/>} />
        <Route path="/admin/products" element={<Product/>} />
        <Route path="/signup" element={<SignUp/>} />
      </Routes>
    </BrowserRouter>
    </>
  );
}

export default App;
