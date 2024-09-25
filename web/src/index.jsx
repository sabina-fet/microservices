import './index.css';
import React from 'react';
import ReactDOM from 'react-dom/client';
import {
    createBrowserRouter,
    RouterProvider,
    Navigate
} from 'react-router-dom';
import ProtectedDefaultLayout from './layouts/ProtectedDefaultLayout'
import FormLayout from './layouts/FormLayout'
import Home from './components/home/Home'
import Transactions from './components/transactions/Transactions'
import TransactionCreate from './components/transactioncreate/TransactionCreate'
import Login from './components/login/Login';
import { AuthProvider } from './hooks/useAuth'

const router2 = createBrowserRouter([
    {
        path: '/login',
        element: <FormLayout> <AuthProvider><Login /> </AuthProvider> </FormLayout>,
    },
    {
        path: '/',
        element: <ProtectedDefaultLayout />,
        children: [
            {
                index: true,
                element: <Navigate to="/home" replace />
            },
            {
                index: true,
                path: '/home',
                element: <Home />
            },
            {
                path: '/transactions',
                element: <Transactions />
            },
            {
                path: '/transactioncreate',
                element: <TransactionCreate />
            }
        ]
    }
])

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <RouterProvider router={router2} />
    </React.StrictMode>
);
