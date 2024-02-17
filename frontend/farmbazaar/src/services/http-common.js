//http-common.js
import axios from 'axios';

export default axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    'Content-Type': 'application/json',
  },
});

/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This component tells that at which port backend is running.
*/