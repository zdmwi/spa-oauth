import routes from "./pageRoutes";

const baseUrl: string = process.env.BASE_URL || "http://localhost:4288";

const config = {
    baseUrl,
    routes
}

export default config;