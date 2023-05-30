import React, { useState } from 'react';

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        // Wysłanie żądania logowania do API Spring Security
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Nazwa użytkownika:
                <input type="text" value={username} onChange={handleUsernameChange} />
            </label>
            <label>
                Hasło:
                <input type="password" value={password} onChange={handlePasswordChange} />
            </label>
            <button type="submit">Zaloguj</button>
        </form>
    );
};

export default LoginForm;