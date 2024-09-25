import './home.css';
import React from 'react'
import {useAuth} from '../../hooks/useAuth'
import {useOutletContext} from "react-router-dom";

export default function Home() {
    let {user} = useAuth()
    let [ activeAccount, accounts ] = useOutletContext();
    // let [accounts] = useOutletContext();

    return (
        <div className="component accounts">
            {Array.from(accounts).map((acc, index) => (
                <div className="account" key={index}>
                    <div>
                        {acc.type}
                    </div>
                    <div>
                        {acc.accountNumber}
                    </div>
                    <div>
                        {acc.balance}
                    </div>
                    <div>
                        {acc.currency}
                    </div>
                    <div>
                        {acc.status}
                    </div>
                </div>
            ))}
        </div>);
}
