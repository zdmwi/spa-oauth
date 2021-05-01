import React, {PropsWithChildren} from "react";
import {BaseAttributes} from "../../types";
import "./index.css";

interface Props {
    url: string;
    icon: any;
}

const OAuthLink: React.FC<BaseAttributes & Props> = (props: PropsWithChildren<BaseAttributes & Props>) => {
    return (
        <a href={props.url} className="oauth_link">
            <img className="oauth_link__icon" src={props.icon} alt=""/>
        </a>
    );
}

export default OAuthLink;