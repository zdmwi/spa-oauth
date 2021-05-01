import React, {PropsWithChildren} from "react";
import {BaseAttributes, InputAttributes} from "../../types";
import "./index.css";

const Input: React.FC<BaseAttributes & InputAttributes> = (props: PropsWithChildren<BaseAttributes & InputAttributes>) => {
    return (
        <input
            id={props.id}
            name={props.name}
            value={props.value}
            autoComplete={props.autoComplete}
            type={props.type ? props.type : 'text'}
            className={`input ${props.className}`}
            onChange={props.onChange}
            checked={props.checked}
        />
    );
}

export default Input;