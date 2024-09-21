import axios from "axios";

const baseURL =
  window.location.port === "5173"
    ? "http://localhost:8080/api" // Development base URL
    : "/api"; // Production base URL

console.log("Base URL:", baseURL); // Log the baseURL to the console

const axiosInstance = axios.create({
  baseURL, // Dynamically set the baseURL
  headers: {
    "Content-Type": "application/json",
  },
});

axiosInstance.interceptors.request.use(
  (config) => {
    // Example: Adding token to headers if needed
    const token = localStorage.getItem("token");
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
