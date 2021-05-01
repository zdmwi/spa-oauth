import React, {PropsWithChildren} from "react";
import {BaseAttributes} from "../../types";
import "./index.css";

interface Props {
    type?: string;
    name?: string;
    value?: string;
    label?: React.ReactNode;
    autoComplete?: string;
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => any;
}

const Input: React.FC<BaseAttributes & Props> = (props: PropsWithChildren<BaseAttributes & Props>) => {
    return (
        <>
            {props.label &&
            <label
                className="input__label"
                htmlFor={props.name}
            >
                {props.label}
            </label>
            }
            <input
                id={props.id}
                name={props.name}
                value={props.value}
                autoComplete={props.autoComplete}
                type={props.type ? props.type : 'text'}
                className={`input ${props.className}`}
                onChange={props.onChange}
            />
        </>
    );
}

export default Input;