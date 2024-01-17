import React, { useState } from 'react';
import { Segment, Input, Button } from "semantic-ui-react";

const MessageInput = () => {

    const [messageState, setMessageState] = useState("");

    const [fileDialogState, setFileDialog] = useState(false);

    const channelId = sessionStorage.getItem('channelId');

    const sendMessage = (e) => {
        const userId = sessionStorage.getItem("userId") ?? "1"

        e.preventDefault();
        const body = messageState;
        console.log({body});

        const message = {
            body: body,
            channelId: channelId
        };

        if (messageState) {
            fetch(`http://localhost:8080/api/messages/sendMessage/${userId}/${channelId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(message)
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Success:', data);
                    setMessageState("");
                    displayMessages(channelId);
                })
                .catch((error) => {
                    console.error('Error:', error);
                    // TODO: Send an error message to the user
                });
        }
    }

    const displayMessages = (channelId) => {
        const dataContainer = document.getElementById('message-container');
        dataContainer.innerHTML = '';

        fetch(`http://localhost:8080/api/messages/channel/${channelId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                const dataContainer = document.getElementById('message-container');
                data.forEach(item => {
                    const itemElement = document.createElement('div');
                    const itemElement2 = document.createElement('div')

                    const formattedDate = new Date(item.date).toLocaleString('en-US', {
                        year: 'numeric',
                        month: '2-digit',
                        day: '2-digit',
                        hour: '2-digit',
                        minute: '2-digit',
                        second: '2-digit'
                    });

                    itemElement.textContent = `${formattedDate.replace(/\//g, '-')} ${sessionStorage.getItem('username') ?? "Sudo Kode"}`;
                    itemElement2.textContent = `${item.body}`
                    dataContainer.appendChild(itemElement);
                    dataContainer.appendChild(itemElement2);
                });
            })
            .catch((error) => {
                console.error('Error:', error);
                // TODO: Send an error message to the user
            });
    }

    const onMessageChange = (e) => {
        const target = e.target;
        setMessageState(target.value);
    }

    const createActionButtons = () => {
        return <>
            <Button icon="send" onClick={(e) => {sendMessage(e) }} />
            <Button icon="upload" onClick={() => setFileDialog(true)} />
        </>
    }

    return (<>
            <Segment>
                <Input
                    onChange={onMessageChange}
                    fluid={true}
                    name="message"
                    value={messageState}
                    label={createActionButtons()}
                    labelPosition="right"
                    size={"huge"}
                />
            </Segment>
        </>
    );
}

export default MessageInput;