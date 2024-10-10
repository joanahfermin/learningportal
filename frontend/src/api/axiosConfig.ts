import axios from "axios";
import { getToken } from '../services/TokenService';

const baseURL =
  window.location.port === "5173"
    ? "http://localhost:8080/api" // Development base URL
    : "/api"; // Production base URL

const axiosInstance = axios.create({
  baseURL, // Dynamically set the baseURL
  headers: {
    "Content-Type": "application/json",
  },
});

axiosInstance.interceptors.request.use(
  (config) => {
    const token = getToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosInstance;
