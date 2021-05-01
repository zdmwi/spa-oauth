import React from "react";
import './App.css';
import {Switch} from "react-router-dom";
import pageRoutes from "./config/pageRoutes";

function App() {
    return (
        <div className="app">
            <Switch>
                {pageRoutes}
            </Switch>
        </div>
    );
}

export default App;
