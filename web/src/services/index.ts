import {LoginRequest} from "../types";
import config from "../config";

const login = async (request: LoginRequest) => {
    return fetch(`${config.baseUrl}/login`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(request)
    })
        .then((response: any) => {
            return response;
        })
        .catch((err: any) => {
            return err;
        })
}

const services = {
    login
}

export default services;