import React, {useState} from "react";
import './App.css';
import Card from "./components/Card";
import TextInput from "./components/TextInput";
import Button from "./components/Button";
import CheckboxInput from "./components/CheckboxInput";

function App() {
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
        <div className="app">
            <Card>
                <header className="card__header"><h1>Sign in</h1></header>
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
                        <Button type="submit" disabled={isDisabled()}>Sign In</Button>
                    </form>
                </main>
            </Card>
        </div>
    );
}

export default App;
