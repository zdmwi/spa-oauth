import React, {PropsWithChildren} from "react";
import {BaseAttributes, OAuthProvider} from "../../types";
import "./index.css";

const OAuthLink: React.FC<BaseAttributes & OAuthProvider> = (props: PropsWithChildren<BaseAttributes & OAuthProvider>) => {
    return (
        <a href={props.url} className="oauth_link">
            <img className="oauth_link__icon" src={props.icon} alt=""/>
        </a>
    );
}

export default OAuthLink;