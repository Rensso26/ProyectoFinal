import axios from "axios";

const REST_API_BASE_URL = "http://localhost:8080/fabric/toy"

export const listToys = () => axios.get(REST_API_BASE_URL)