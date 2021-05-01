import React, {useState} from "react";
import './App.css';
import Card from "./components/Card";
import Input from "./components/Input";
import Button from "./components/Button";

function App() {
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

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        console.log(email, password);
    }

    return (
        <div className="app">
            <Card>
                <header className="card__header"><h1>Sign in</h1></header>
                <main className="card__content">
                    <p className="card__content__instructions">Enter your email and password to sign in</p>
                    <form onSubmit={handleSubmit} noValidate={true} className="form">
                        <Input
                            label={"Email Address"}
                            value={email}
                            name="email"
                            type="email"
                            autoComplete="username"
                            onChange={handleEmailChange}
                        />
                        <Input
                            label={"Password"}
                            value={password}
                            name="password"
                            autoComplete="current-password"
                            type="password"
                            onChange={handlePasswordChange}
                        />
                        <Button type="submit" disabled={isDisabled()}>Sign In</Button>
                    </form>
                </main>
            </Card>
        </div>
    );
}

export default App;
