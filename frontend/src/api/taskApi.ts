import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8082/api/v1/task"
});

export const createTask = (data) => API.post("", data);

export const deleteTask = (id) => API.delete(`/${id}`);

export const getActiveTasks = () => API.get("/active");

export const getTaskById = (id) => API.get(`/${id}`); // если есть endpoint