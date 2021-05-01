import React, {useState} from "react";
import Card from "../../components/Card";
import TextInput from "../../components/TextInput";
import CheckboxInput from "../../components/CheckboxInput";
import Button from "../../components/Button";
import "./index.css";
import {OAuthProvider} from "../../types";
import OAuthLink from "../../components/OAuthLink";
import GithubIcon from "../../assets/icons/github-icon.svg";
import TwitterIcon from "../../assets/icons/twitter-icon.svg";
import config from "../../config";

const oAuthProviders: OAuthProvider[] = [
    {url: `${config.baseUrl}/oauth/github/redirect`, icon: GithubIcon},
    {url: `${config.baseUrl}/oauth/twitter/redirect`, icon: TwitterIcon}
];

const LoginPage: React.FC = () => {
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [shouldRemember, setShouldRemember] = useState<boolean>(false);

    const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setEmail(value);
    }

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setPassword(value);
    }

    const handleRememberMeChange = () => {
        setShouldRemember(prevState => !prevState);
    }

    const isDisabled = (): boolean => {
        return email.length === 0 || password.length === 0;
    }

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        console.log(email, password, shouldRemember);
    }

    return (
        <div className="login__page">
            <Card>
                <header className="card__header"><h1>Login</h1></header>
                <main className="card__content">
                    <p className="card__content__instructions">Enter your email and password to sign in</p>
                    <form onSubmit={handleSubmit} noValidate={true} className="form">
                        <label className="input__label" htmlFor="email">Email Address</label>
                        <TextInput
                            value={email}
                            name="email"
                            type="email"
                            autoComplete="username"
                            onChange={handleEmailChange}
                        />
                        <label className="input__label mt-4" htmlFor="password">Password</label>
                        <TextInput
                            value={password}
                            name="password"
                            autoComplete="current-password"
                            type="password"
                            onChange={handlePasswordChange}
                        />
                        <CheckboxInput
                            className="mt-4"
                            label="Remember me"
                            checked={shouldRemember}
                            onChange={handleRememberMeChange}
                        />
                        <Button type="submit" disabled={isDisabled()}>Login</Button>
                    </form>
                    <span className="text--separator my-8">Or continue with</span>
                    <div className="oauth_link__container mt-4">
                        {oAuthProviders.map(({url, icon}: OAuthProvider) => <OAuthLink key={url} url={url} icon={icon}/>)}
                    </div>
                </main>
            </Card>
        </div>
    );
}

export default LoginPage;