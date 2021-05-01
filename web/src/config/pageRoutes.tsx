import React from "react";
import {PageRoute} from "../types";
import LoginPage from "../pages/Login";
import HomePage from "../pages/Home";
import {Route} from "react-router-dom";

const routes: PageRoute[] = [
    {
        path: "/login",
        component: LoginPage,
        isPrivate: false
    },
    {
        path: "/home",
        component: HomePage,
        isPrivate: false
    }
];

const pageRoutes = routes.map((route: PageRoute, index: number) => {
    if (route.isPrivate) {
        return null;
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