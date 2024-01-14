import React, { useState, useEffect } from 'react';
import './Channels.css';
import { Menu, Icon, Modal, Button, Form, Segment } from 'semantic-ui-react';

const userId = sessionStorage.getItem('userId');
// TODO: Un-hardcode me pls!
const workspaceId = 1;

const Channels = (props) => {
    const [modalOpenState, setModalOpenState] = useState(false);
    const [channelAddState, setChannelAddState] = useState({ name: ''});
    const [isLoadingState, setLoadingState] = useState(false);
    const [channels, setChannels] = useState([]);

    const openModal = () => {
        setModalOpenState(true);
    }

    const closeModal = () => {
        setModalOpenState(false);
    }

    const displayChannels = () => {
        const workspaceId = 1;

        fetch(`http://localhost:8080/api/workspaces/${workspaceId}/channels`, {
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
            .then(data => {
                console.log('Raw API data:', data);
                if (Array.isArray(data)) {
                    setChannels(data);
                } else {
                    console.log('Data is not an array:', data);
                    setChannels([]);
                }
            })
            .catch((error) => {
                console.error('Fetch error:', error);
            });
    }

    useEffect(() => {
        displayChannels();
    }, []);

    const goToMessages = (event) => {
        event.preventDefault();
        window.location = '/message'
    }

    const onSubmit = (event) => {
        event.preventDefault();

        if (!channelAddState.channelName) {
            console.log("Form is not valid");
            return;
        }

        const channel = {
            channelName: channelAddState.channelName,
            accessibility: true,
            visible: true,
            workspaceId: workspaceId
        };

        console.log("Submitting channel:", channel);

        fetch(`http://localhost:8080/api/channels/workspaces/${workspaceId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(channel)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                closeModal();
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }


    const handleInput = (e) => {
        const { name, value } = e.target;
        setChannelAddState((currentState) => {
            return { ...currentState, [name]: value };
        });
    }


    return <> <Menu.Menu style={{ marginTop: '35px' }}>
        <Menu.Item style={{fontSize : '17px'}}>
            <span>
                <Icon name="exchange" /> Channels
            </span>
            ({channels.length})
        </Menu.Item>
        {channels.map(channel => (
            <Menu.Item key={channel.channelId}>
                {channel.channelName}
            </Menu.Item>
        ))}
        <Menu.Item>
            <span className="clickable" onClick={openModal} >
                <Icon name="add" /> New Channel
            </span>
        </Menu.Item>
        <Menu.Item>
            <span className="clickable" onClick={goToMessages} >
                <Icon name="mail" /> Send Message
            </span>
        </Menu.Item>
    </Menu.Menu>
        <Modal open={modalOpenState} onClose={closeModal}>
            <Modal.Header>
                Create Channel
            </Modal.Header>
            <Modal.Content>
                <Form onSubmit={onSubmit}>
                    <Segment stacked>
                        <Form.Input
                            name="channelName"
                            value={channelAddState.channelName}
                            onChange={handleInput}
                            type="text"
                            placeholder="Enter Channel Name"
                        />
                    </Segment>
                    <Button loading={isLoadingState} type="submit">
                        <Icon name="checkmark" /> Save
                    </Button>
                    <Button onClick={closeModal}>
                        <Icon name="remove" /> Cancel
                    </Button>
                </Form>
            </Modal.Content>
        </Modal>
    </>
}

export default Channels;