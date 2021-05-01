import React, {ComponentType, ElementType, ReactNode} from "react";

export interface BaseAttributes {
    id?: string;
    className?: string;
}

export interface InputAttributes {
    type?: string;
    name?: string;
    value?: string;
    autoComplete?: string;
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => any;
    checked?: boolean;
}

export interface OAuthProvider {
    url: string;
    icon: string;
}

export interface PageRoute {
    path: string;
    component: ElementType;
    routes?: PageRoute[];
    isPrivate: boolean;
}

export interface UserCredentials {
    email: string;
    password: string;
}

export type UserAccount = UserCredentials;

export type LoginRequest = UserCredentials & {rememberMe?: boolean};

export type RegisterRequest = UserCredentials;