const API_BASE_URL = 'https://yeechat.zipcode.rocks:8084/api';

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

export function createWorkspaceByUser(userId, workspaceData) {
    return fetch(`${API_BASE_URL}/workspaces/users/${userId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(workspaceData)
    })
        .then(response => {
            if (response.status !== 201) {
                return response.json().then(data => {
                    throw new Error(`Server error: ${data.message || response.status}`);
                });
            }
            return response.json();
        })
        .catch((error) => {
            console.error('Error creating workspace:', error);
            throw error;
        });
}

// ADD USER TO WORKSPACE