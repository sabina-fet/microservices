import axios from 'axios';

export default class Api {
    constructor() {
        this.transactionsEndpoint = "http://localhost:80/api/transactions"
        this.accountsEndpoint = "http://localhost:80/api/accounts/"
        this.authEndpoint = "http://localhost:80/api/auth"
    }

    getTransactions(accountId, user, callback) {
        axios.get(this.transactionsEndpoint + "/" + accountId, {
            headers: {
                'Authorization': `Bearer ${user.accessToken}`
            }
        })
            .then(response => {
                callback(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the transactions!', error);
            });
    }

    getAccounts(user, callback) {
        axios.get(this.accountsEndpoint + user.id, {
            headers: {
                'Authorization': `Bearer ${user.accessToken}`
            }
        })
            .then(response => {
                callback(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the transactions!', error);
            });
    }

    getUser(body, callback) {
        axios.post(this.authEndpoint, body)
            .then(response => {
                callback(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the transactions!', error);
            });
    }

    createTransaction(body, user, callback) {
        axios.post(this.transactionsEndpoint, body, {
            headers: {
                'Authorization': `Bearer ${user.accessToken}`
            }
        })
            .then(response => {
                callback(response.data);
            })
            .catch(error => {
                console.error('There was an error creating the transactions!', error);
            });
    }
}
