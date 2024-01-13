const API_BASE_URL = 'http://localhost:8080/api';

export function getWorkspacesForUser(userId) {
    return fetch(`${API_BASE_URL}/workspaces/users/${userId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .catch((error) => {
            console.error('Fetch error:', error);
            throw error;
        });
}

// CREATE WORKSPACE

// ADD USER TO WORKSPACE