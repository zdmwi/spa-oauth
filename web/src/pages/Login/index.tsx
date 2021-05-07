import React, {useState} from "react";
import Card from "../../components/Card";
import TextInput from "../../components/TextInput";
import CheckboxInput from "../../components/CheckboxInput";
import Button from "../../components/Button";
import "./index.css";
import {OAuthProvider} from "../../types";
import OAuthLink from "../../components/OAuthLink";
import services from "../../services";
import {Link, useHistory} from 'react-router-dom';
import oAuthProviders from "../../config/oAuthProviders";
import useAuthQueryParams from "../../hooks/useAuthQueryParams";

const LoginPage: React.FC = () => {
  useAuthQueryParams();

  const history = useHistory();
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [rememberMe, setRememberMe] = useState<boolean>(false);

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setEmail(value);
  }

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setPassword(value);
  }

  const handleRememberMeChange = () => {
    setRememberMe(prevState => !prevState);
  }

  const isDisabled = (): boolean => {
    return email.length === 0 || password.length === 0;
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const {accessToken, refreshToken} = await services.login({email, password, rememberMe});
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);
      history.push("/home");
    } catch (err: any) {
      console.log(err);
    }
  }

  return (
  <div className="auth__page">
    <Card>
      <header className="card__header">
        <h1 className="card__header__title">Login</h1>
        <span className="card__header__advice">Need an account? <Link
        to="/register">Register now</Link></span>
      </header>
      <main className="card__content">
        <p className="card__content__instructions">Enter your email and password to login</p>
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
          checked={rememberMe}
          onChange={handleRememberMeChange}
          />
          <Button type="submit" disabled={isDisabled()}>Login</Button>
        </form>
        <span className="text--separator my-8">Or continue with</span>
        <div className="oauth_link__container mt-4">
          {oAuthProviders.map(({url, icon}: OAuthProvider) =>
          <OAuthLink
          key={url}
          url={url}
          icon={icon}
          />
          )}
        </div>
      </main>
    </Card>
  </div>
  );
}

export default LoginPage;