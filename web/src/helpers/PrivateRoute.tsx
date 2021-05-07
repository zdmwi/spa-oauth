import React from "react";
import {Redirect, Route, RouteProps} from "react-router-dom";

type PrivateRouteProps = RouteProps & {routeComponent: React.ElementType};

const PrivateRoute: React.FC<PrivateRouteProps> = ({children, ...rest}: PrivateRouteProps) => {
    const accessToken = localStorage.getItem("accessToken");
    const refreshToken = localStorage.getItem("refreshToken");

    const isAuthenticated = (): boolean => accessToken !== null && refreshToken !== null;

    return (
        <Route
            {...rest}
            render={({location}) =>
                isAuthenticated()
                    ? (<rest.routeComponent />)
                    : (
                        <Redirect
                            to={{
                                pathname: "/login",
                                state: {from: location}
                            }}
                        />
                    )
            }
        />
    );
}

export default PrivateRoute;