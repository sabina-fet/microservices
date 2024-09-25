import './transactions.css';
import React, {useEffect, useState} from 'react';
import {useAuth} from '../../hooks/useAuth'
import {redirect, useNavigate, useOutletContext} from "react-router-dom";
import Api from '../../util/api.js'

const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleString('en-GB', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        hour12: false,
    }).replace(',', ''); // removes comma between date and time
};

export default function Transactions() {
    let api = new Api()
    let [activeAccount] = useOutletContext();
    let {user} = useAuth()
    let [transactions, setTransactions] = useState([]);
    let navigate = useNavigate()

    // this needs to be encapsulated in Api class
    // and we need to be able to refresh access token
    useEffect(() => {

        console.log("active " + activeAccount.id)
        if (activeAccount.id == undefined)
            return

        const callback = (transactions) => {
            setTransactions(transactions)
        }

        api.getTransactions(activeAccount.id, user, callback);
    }, [activeAccount]);

    function redirectToTransactionForm() {
        navigate('/transactioncreate')
    }

    return (
        <div className="component transactions">
            <button className="app-btn" onClick={redirectToTransactionForm}>NEW TRANSACTION</button>

            {transactions.map((trans, index) => (
                <div className="transaction" key={index}>
                    <div className="transaction-item">
                        <span className={trans.amount > 0 ? "big-plus" : "big-minus"}>
                            {trans.amount > 0 ? "+" : "-"}
                        </span>
                        {trans.destination}
                    </div>
                    <div className="transaction-item">
                        {trans.amount} {activeAccount.currency}
                    </div>
                    <div className="transaction-item">
                        {formatDate(new Date(trans.datetime).toUTCString())}
                    </div>
                    <div className="transaction-item">
                        {trans.status}
                    </div>
                </div>
            ))}
        </div>
    );
}
