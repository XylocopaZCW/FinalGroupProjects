import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import SignUp from "./components/signup/SignUp";
import SignIn from "./components/signin/SignIn";
import Messages from "./components/messages/Messages";

const ProtectedRoute = ({ children }) => {
    if (!sessionStorage.getItem("userId")) {
        // Redirect to the sign-in page
        return <Navigate to="/signin" />;
    }
    return children;
};

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <Router>
            <Routes>
                <Route path="/signup" element={<SignUp />} />
                <Route path="/signin" element={<SignIn />} />
                <Route path="/message" component={SignIn} element={<Messages />}/>
                <Route
                    path="/"
                    element={
                        <ProtectedRoute>
                            <App />
                        </ProtectedRoute>
                    }
                />
            </Routes>
        </Router>
    </React.StrictMode>
);


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
