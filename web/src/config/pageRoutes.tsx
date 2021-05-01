import React from "react";
import {PageRoute} from "../types";
import LoginPage from "../pages/Login";
import HomePage from "../pages/Home";
import {Route} from "react-router-dom";
import PrivateRoute from "../helpers/PrivateRoute";
import RegisterPage from "../pages/Register";

const routes: PageRoute[] = [
    {
        path: "/login",
        component: LoginPage,
        isPrivate: false
    },
    {
        path: "/register",
        component: RegisterPage,
        isPrivate: false
    },
    {
        path: "/home",
        component: HomePage,
        isPrivate: true
    },
    {
        path: "/",
        component: HomePage,
        isPrivate: true
    }
];

const pageRoutes = routes.map((route: PageRoute, index: number) => {
    if (route.isPrivate) {
        return (
            <PrivateRoute
                key={index}
                path={route.path}
                routeComponent={route.component}
            />
        )
    }
    return (
        <Route
            key={index}
            path={route.path}
            render={props => (<route.component {...props} />)}
        />
    )
});

export default pageRoutes;