import axios from "axios";

const API_URL = "http://localhost:8080/api/pokemon";

export const getPokemon = async(name) => {

    try{
        const resp = await axios.get(`${API_URL}/${name}`);
        return resp.data;
    }
    catch(error)
    {
        throw error;
    }
};