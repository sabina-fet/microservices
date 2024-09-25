import "./login.css"
import React, { useState } from "react";
import axios from "axios"
import { useAuth } from "../../hooks/useAuth";
import { useNavigate } from "react-router-dom";
import Api from "../../util/api.js"

export default function Login() {
    let api = new Api()
    let [username, setUsername] = useState("")
    let [password, setPassword] = useState("")
    let { login } = useAuth()
    let navigate = useNavigate()

    let handleLogin = (e) => {
        e.preventDefault()
        try {
            let callback = async (user) => {
                await login({
                    "id": user.userId,
                    "accessToken": user.accessToken,
                    "refreshToken": user.refreshToken,
                    "name": user.name
                });
                navigate('/home')
            }
            let body = { username, password }
            api.getUser(body, callback)
        } catch (error) {
            console.log("Error during login: " + error);
        }
    };
    return (
        <form className="login-form" onSubmit={handleLogin}>
            <div className="form-row">
                <label htmlFor="username">Username:</label>
                <input
                  id="username"
                  type="text"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
            </div>
            <div className="form-row">
                <label htmlFor="password">Password:</label>
                <input
                  id="password"
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
            </div>
            <button type="submit">LOGIN</button>
        </form>
    );
};
