import React, {PropsWithChildren} from "react";
import {BaseAttributes, InputAttributes} from "../../types";
import Input from "../Input";
import "./index.css";

type CheckboxAttributes = InputAttributes & {label?: string, checked?: boolean};

const CheckboxInput: React.FC<BaseAttributes & CheckboxAttributes> = (props: PropsWithChildren<BaseAttributes & CheckboxAttributes>) => {
    return (
        <label className={`checkbox__container ${props.className}`}>
            <Input
                id={props.id}
                name={props.name}
                className="input--checkbox"
                onChange={props.onChange}
                type="checkbox"
                checked={props.checked}
            />
            <span className="input__label">{props.label}</span>
        </label>
    )
}

export default CheckboxInput;