import './transactioncreate.css';
import React, {useEffect, useState} from 'react';
import {useAuth} from '../../hooks/useAuth'
import {useNavigate, useOutletContext} from "react-router-dom";
import Api from '../../util/api.js'

export default function TransactionCreate() {
    let api = new Api()
    let [src, setSrc] = useState("")
    let [dest, setDest] = useState("")
    let [amount, setAmount] = useState("")
    let [activeAccount] = useOutletContext()
    let {user} = useAuth()
    let navigate = useNavigate()

    let handleNewTransaction = (e) => {
        e.preventDefault()
        try {
            let callback = async => {
                navigate('/transactions')
            }
            let body = { src: activeAccount.id, dest, amount }
            api.createTransaction(body, user, callback)
        } catch (error) {
            console.log("Error during creating transaction: " + error);
        }
    };
    return (
        <form className="transaction-form component" onSubmit={handleNewTransaction}>
            <div className="form-row">
                <label htmlFor="dest">Destination account:</label>
                <input
                    id="dest"
                    type="text"
                    value={dest}
                    onChange={(e) => setDest(e.target.value)}
                />
            </div>
            <div className="form-row">
                <label htmlFor="amount">Amount EUR:</label>
                <input
                    id="amount"
                    type="text"
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                />
            </div>
            <div className="form-row submit-btn">
                <button className="app-btn" type="submit">SUBMIT</button>
            </div>
        </form>
);
}
