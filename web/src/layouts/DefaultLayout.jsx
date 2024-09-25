import "./defaultLayout.css"
import React, {useState, useEffect} from 'react'
import {NavLink, Outlet} from 'react-router-dom'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faBitcoin} from '@fortawesome/free-brands-svg-icons'
import {
    faRightFromBracket,
    faUser,
    faHouse,
    faArrowLeft,
    faArrowRight,
    faWallet, faCreditCard, faArrowCircleLeft, faArrowCircleRight
} from '@fortawesome/free-solid-svg-icons'
import {useAuth} from '../hooks/useAuth'
import Api from '../util/api.js'
import {useLocalStorage} from "../hooks/useLocalStorage";

export default function DefaultLayout() {
    let api = new Api()
    let {user} = useAuth()
    let [accounts, setAccounts] = useState([])
    let [activeAccount, setActiveAccount] = useState({})

    useEffect(() => {
        const callback = async (userAccounts) => {
            console.log(userAccounts)

            setAccounts(userAccounts)
            setActiveAccount(userAccounts[0])
        }

        api.getAccounts(user, callback);
    }, []);

    function selectNextAccount() {
        let currAccIndex = accounts.findIndex(acc => acc.id === activeAccount.id);
        setActiveAccount(accounts[(currAccIndex + 1) % accounts.length])
    }

    function selectPreviousAccount() {
        let currAccIndex = accounts.findIndex(acc => acc.id === activeAccount.id);
        setActiveAccount(accounts[(currAccIndex + accounts.length - 1) % accounts.length])
    }

    return (
        <div className="default-layout">
            <nav className='header-navbar'>
                <div className='header-navbar-content'>
                    <div className='header-navbar-left'>
                        <FontAwesomeIcon className='logo-icon' icon={faBitcoin}/>
                        <div className="bank-name">
                            eBANK
                        </div>
                    </div>
                    <div className='header-navbar-right'>
                        <div className="user-name">{user.name}</div>
                        <FontAwesomeIcon className='nav-icon' tooltip={"Log out"} icon={faRightFromBracket}/>
                        <FontAwesomeIcon className='nav-icon' icon={faUser}/>
                    </div>
                </div>
            </nav>
            <div className="component account-selector">
            <FontAwesomeIcon className='nav-link-icon' color="seagreen" fontSize="50px" icon={faArrowCircleLeft} onClick={selectPreviousAccount}/>
                <div className="account-name">
                    <div className="account-number">
                        {activeAccount.accountNumber}
                    </div>
                    <div className="account-name-details">
                        <div>{activeAccount.type}</div>
                        <div>{activeAccount.balance} {activeAccount.currency}</div>
                        <div>{activeAccount.status}</div>
                    </div>
                </div>
                <FontAwesomeIcon className='nav-link-icon' color="seagreen" fontSize="50px" icon={faArrowCircleRight} onClick={selectNextAccount}/>
            </div>
            <nav className='page-navbar'>
                <NavLink className='nav-link' to='/home'>
                    <FontAwesomeIcon className='nav-link-icon' icon={faCreditCard}/>
                    <div>ACCOUNTS</div>
                </NavLink>
                <NavLink className='nav-link' to='/transactions'>
                    <FontAwesomeIcon className='nav-link-icon' icon={faWallet}/>
                    <div>TRANSACTIONS</div>
                </NavLink>
            </nav>
            <Outlet context={[activeAccount, accounts]}/>
        </div>
    )
}
