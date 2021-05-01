import React from "react";

export interface BaseAttributes {
    id ?: string;
    className ?: string;
}

export interface InputAttributes {
    type?: string;
    name?: string;
    value?: string;
    autoComplete?: string;
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => any;
    checked?: boolean;
}