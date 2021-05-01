import React, {useState} from "react";
import Card from "../../components/Card";
import TextInput from "../../components/TextInput";
import Button from "../../components/Button";
import "./index.css";
import {OAuthProvider} from "../../types";
import OAuthLink from "../../components/OAuthLink";
import GithubIcon from "../../assets/icons/github-icon.svg";
import TwitterIcon from "../../assets/icons/twitter-icon.svg";
import config from "../../config";
import services from "../../services";
import {Link} from "react-router-dom";

const oAuthProviders: OAuthProvider[] = [
    {url: `${config.baseUrl}/oauth/github/redirect`, icon: GithubIcon},
    {url: `${config.baseUrl}/oauth/twitter/redirect`, icon: TwitterIcon}
];

const RegisterPage: React.FC = () => {
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setEmail(value);
    }

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setPassword(value);
    }

    const isDisabled = (): boolean => {
        return email.length === 0 || password.length === 0;
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await services.register({email, password});
            console.log(response);
        } catch (err: any) {
            console.log(err);
        }
    }

    return (
        <div className="auth__page">
            <Card>
                <header className="card__header">
                    <h1 className="card__header__title">Register</h1>
                    <span className="card__header__advice">Already have an account? <Link to="/login">Login here</Link></span>
                </header>
                <main className="card__content">
                    <p className="card__content__instructions">Enter your email and password to register</p>
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
                        <Button type="submit" disabled={isDisabled()}>Register</Button>
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

export default RegisterPage;