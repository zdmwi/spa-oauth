import React, {PropsWithChildren} from 'react';
import {BaseAttributes} from "../../types";
import "./index.css";

interface Props {
    disabled?: boolean;
    type ?: "button" | "submit" | "reset";
}

const Button: React.FC<BaseAttributes & Props> = (props: PropsWithChildren<BaseAttributes & Props>) => {
    return (
        <button
            id={props.id}
            className={`button ${props.className}`}
            disabled={props.disabled}
            type={props.type}
        >
            {props.children}
        </button>
    );
}

export default Button;