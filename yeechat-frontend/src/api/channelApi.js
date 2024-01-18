const API_BASE_URL = 'https://yeechat.zipcode.rocks:8084/api';

export function getChannelsFromWorkspace(workspaceId) {
    return fetch(`${API_BASE_URL}/workspaces/${workspaceId}/channels`, {
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

export function createChannelInWorkspace(workspaceId, channelData) {
    return fetch(`${API_BASE_URL}/channels/workspaces/${workspaceId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(channelData)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(data => {
                    throw new Error(`Server error: ${data.message || response.status}`);
                });
            }
            return response.json();
        })
        .catch((error) => {
            console.error('Error creating channel:', error);
            throw error;
        });
}
