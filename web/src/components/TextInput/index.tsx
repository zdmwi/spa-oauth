import React, {PropsWithChildren} from "react";
import {BaseAttributes, InputAttributes} from "../../types";
import Input from "../Input";
import "./index.css"

const TextInput: React.FC<BaseAttributes & InputAttributes> = (props: PropsWithChildren<BaseAttributes & InputAttributes>) => {
    return (
        <Input
            id={props.id}
            name={props.name}
            value={props.value}
            autoComplete={props.autoComplete}
            type="text"
            className={`input--text ${props.className}`}
            onChange={props.onChange}
        />
    );
}

export default TextInput;