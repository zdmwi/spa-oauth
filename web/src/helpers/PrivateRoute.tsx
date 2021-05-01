import React from "react";
import {Redirect, Route, RouteProps} from "react-router-dom";

type PrivateRouteProps = RouteProps & {routeComponent: React.ElementType};

const PrivateRoute: React.FC<PrivateRouteProps> = ({children, ...rest}: PrivateRouteProps) => {
    // TODO: Refactor this to use the proper auth methods later on
    const isAuthenticated = (): boolean => false;

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