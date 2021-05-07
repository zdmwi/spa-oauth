import {LoginRequest, RegisterRequest} from "../types";
import config from "../config";


interface AuthResponse {
  accessToken: string;
  refreshToken: string;
}

const login = async (request: LoginRequest): Promise<AuthResponse> => {
  return fetch(`${config.baseUrl}/auth/login`, {
    method: 'POST',
    headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
    body: JSON.stringify(request)
  })
  .then((response: Response) => {
    return response.json()
  })
  .catch((err: any) => {
    return err;
  })
}

const register = async (request: RegisterRequest): Promise<AuthResponse> => {
  return fetch(`${config.baseUrl}/auth/register`, {
    method: 'POST',
    headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
    body: JSON.stringify(request)
  })
  .then((response: any) => {
    return response.json();
  })
  .catch((err: any) => {
    return err;
  })
}

const services = {
  login,
  register,
}

export default services;