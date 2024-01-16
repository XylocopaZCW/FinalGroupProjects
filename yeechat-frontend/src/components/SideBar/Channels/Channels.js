import React, { useState, useEffect } from 'react';
import './Channels.css';
import { Menu, Icon, Modal, Button, Form, Segment } from 'semantic-ui-react';
import {getChannelsFromWorkspace, createChannelInWorkspace} from "../../../api/channelApi";
// import { GrChannel } from "react-icons/gr";
import { FaCaretDown } from "react-icons/fa";
import { MdAddToHomeScreen } from "react-icons/md";

const Channels = () => {
    const [modalOpenState, setModalOpenState] = useState(false);
    const [channelAddState, setChannelAddState] = useState({ name: ''});
    const [isLoadingState] = useState(false);
    const [channels, setChannels] = useState([]);
    const [channelMap, setChannelMap] = useState({});

    const openModal = () => { setModalOpenState(true); }
    const closeModal = () => { setModalOpenState(false); }

    const displayChannels = () => {
        const workspaceId = sessionStorage.getItem('workspaceId')
        getChannelsFromWorkspace(workspaceId)
            .then(data => {
                console.log('Raw API data:', data);
                if (Array.isArray(data)) {
                    setChannels(data);
                    const map = {};
                    data.forEach(channel => {
                        map[channel.channelId] = channel.channelName;
                    });
                    sessionStorage.setItem('channelMap', JSON.stringify(map));
                    setChannelMap(map);
                } else {
                    console.log('Data is not an array:', data);
                    setChannels([]);
                }
            })
            .catch((error) => {
                console.error('Error fetching channels:', error);
            });
    }
        // worksoace to update the channel
    useEffect(() => {
        const handleWorkspaceChange = () => {
            refreshChannels();
        };

        window.addEventListener('workspaceSelect', handleWorkspaceChange);
        refreshChannels();

        return () => {
            window.removeEventListener('workspaceSelect', handleWorkspaceChange);
        };
    }, []);

    const refreshChannels = () => {
        const updatedWorkspaceId = sessionStorage.getItem('workspaceId'); // Retrieve the updated workspaceId
        getChannelsFromWorkspace(updatedWorkspaceId)
            .then(data => {
                setChannels(data);
                const map = {};
                data.forEach(channel => {
                    map[channel.channelId] = channel.channelName;
                });
                sessionStorage.setItem('channelMap', JSON.stringify(map));
                setChannelMap(map);
            })
            .catch((error) => {
                console.error('Error fetching channels:', error);
            });
    }

    useEffect(() => {
        displayChannels();
    }, []);

    const onSubmit = (event) => {
        event.preventDefault();
        const workspaceId = sessionStorage.getItem('workspaceId');

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

        createChannelInWorkspace(workspaceId, channel)
            .then(data => {
                console.log('Success:', data);
                closeModal();
                refreshChannels();
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    };


    const handleInput = (e) => {
        const { name, value } = e.target;
        setChannelAddState((currentState) => {
            return { ...currentState, [name]: value };
        });
    }

    const handleChannelClick = (channelId, channelName) => {
        console.log(`Redirect to channel (${channelId}) ${channelName}`);
        sessionStorage.setItem('channelId', channelId);
        sessionStorage.setItem('channelName', channelName);
        displayMessages(channelId);
        const event = new Event('channelSelect');
        window.dispatchEvent(event);
    };

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

    return <> <Menu.Menu style={{ marginTop: '20px' }}>
        <Menu.Item style={{fontSize : '2rem'}}>
            <span>
                {/*<Icon name="exchange" /> Channels*/}
                {/*<GrChannel /> Channels*/}
                <FaCaretDown /> Channels
            </span>
            ({channels.length})
        </Menu.Item>
        {channels.map(channel => (
            <Menu.Item key={channel.channelId} onClick={() => handleChannelClick(channel.channelId, channel.channelName)}>
                {channel.channelName}
            </Menu.Item>
        ))}
        <Menu.Item>
            <span className="clickable" onClick={openModal} >
                {/*<Icon name="add" /> New Channel*/}
                <MdAddToHomeScreen/> New Channel
            </span>
        </Menu.Item>
    </Menu.Menu>
        <Modal open={modalOpenState} onClose={closeModal}>
            <Modal.Header style={{fontSize : '2rem'}}>
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