import "./formLayout.css"
import React from 'react'
import { Outlet } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBitcoin } from '@fortawesome/free-brands-svg-icons'

export default function FormLayout({ children }) {
    return (
        <div className="form-layout">
            <div className="form-component">
                <div className="form-banner">
                    <FontAwesomeIcon className='banner-icon' icon={faBitcoin} />
                </div>
                <div className="form-container">{ children }</div>
            </div>
        </div>
    )
}
