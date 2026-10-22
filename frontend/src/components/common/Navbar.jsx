
import React, { useEffect, useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import ApiService from '../../service/ApiService';

function Navbar() {
    const isAuthenticated = ApiService.isAuthenticated();
    const isAdmin = ApiService.isAdmin();
    const isUser = ApiService.isUser();
    const navigate = useNavigate();

const [userName, setUserName] = useState("Account");

useEffect(() => {
    const loadUserProfile = async () => {
        if (isAuthenticated && isUser) {
            try {
                const user = await ApiService.getUserProfile();

                setUserName(
                    user?.name ||
                    user?.firstName ||
                    user?.username ||
                    "Account"
                );
            } catch (error) {
                console.error("Failed to load user profile:", error);
            }
        }
    };

    loadUserProfile();
}, [isAuthenticated, isUser]);




















    const handleLogout = () => {
        const isLogout = window.confirm('Are you sure you want to logout this user?');

        if (isLogout) {
            ApiService.logout();
            navigate('/home');
        }
    };

    return (
        <nav className="navbar">

            {/* Logo */}
            <div className="navbar-brand">
                <NavLink to="/home">
                    <span className="brand-icon">P</span>
                    <span className="brand-name">Prem Hotel</span>
                </NavLink>
            </div>

            {/* Navigation */}
            <div className="navbar-right">

                <ul className="navbar-ul">

                    <li>
                        <NavLink
                            to="/home"
                            className={({ isActive }) =>
                                isActive ? 'nav-link active' : 'nav-link'
                            }
                        >
                            <span>Home</span>
                        </NavLink>
                    </li>

                    <li>
                        <NavLink
                            to="/rooms"
                            className={({ isActive }) =>
                                isActive ? 'nav-link active' : 'nav-link'
                            }
                        >
                            <span>Rooms</span>
                        </NavLink>
                    </li>

                    <li>
                        <NavLink
                            to="/find-booking"
                            className={({ isActive }) =>
                                isActive ? 'nav-link active' : 'nav-link'
                            }
                        >
                            <span>Find my Booking</span>
                        </NavLink>
                    </li>

                    {isUser && (
                        <li>
                            <NavLink
                                to="/profile"
                                className={({ isActive }) =>
                                    isActive ? 'nav-link active' : 'nav-link'
                                }
                            >
                                <span>Profile</span>
                            </NavLink>
                        </li>
                    )}

                    {isAdmin && (
                        <li>
                            <NavLink
                                to="/admin"
                                className={({ isActive }) =>
                                    isActive ? 'nav-link active' : 'nav-link'
                                }
                            >
                                <span>Admin</span>
                            </NavLink>
                        </li>
                    )}

                </ul>

                {/* Authentication buttons */}
                <div className="navbar-auth">

                    {!isAuthenticated && (
                        <>
                            <NavLink to="/login" className="login-btn">
                                Login
                            </NavLink>

                            <NavLink to="/register" className="register-btn">
                                Register
                            </NavLink>
                        </>
                    )}

                    {isAuthenticated && (
                        <>
                            <button
                                type="button"
                                className="user-profile-btn"
                                onClick={() => navigate('/profile')}
                            >
                                <span className="user-avatar">P</span>
                                <span className="user-name">{userName}</span>
                                <span className="user-arrow">⌄</span>
                            </button>

                            <button
                                type="button"
                                className="logout-btn"
                                onClick={handleLogout}
                            >
                                Logout
                            </button>
                        </>
                    )}

                </div>

            </div>
        </nav>
    );
}

export default Navbar;