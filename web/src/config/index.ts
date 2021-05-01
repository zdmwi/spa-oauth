import routes from "./pageRoutes";

const baseUrl: string = process.env.BASE_URL || "http://localhost:4288";
const accessTokenKey: string = "access_token";
const refreshTokenKey: string = "refresh_token";

const config = {
    baseUrl,
    accessTokenKey,
    refreshTokenKey,
    routes
}

export default config;