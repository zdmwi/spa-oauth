import React, {PropsWithChildren} from "react";
import "./index.css";
import {BaseAttributes} from "../../types";

const Card: React.FC<BaseAttributes> = (props: PropsWithChildren<BaseAttributes>) => {
    return (
        <div className={`card ${props.className}`}>
            <div className="card__inner">
                {props.children}
            </div>
        </div>
    )
}

export default Card;