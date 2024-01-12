import React, { useState, useEffect } from 'react';
import './Channels.css';
import { Menu, Icon, Modal, Button, Form, Segment } from 'semantic-ui-react';
import TextField from "@mui/material/TextField";
import Box from '@mui/material/Box';

const Channels = (props) => {
    const [modalOpenState, setModalOpenState] = useState(false);
    const [channelAddState, setChannelAddState] = useState({ name: ''});
    const [isLoadingState, setLoadingState] = useState(false);
    const [channelsState, setChannelsState] = useState([]);

    // const channelsRef = firebase.database().ref("channels");
    // const usersRef = firebase.database().ref("users");
    //
    // useEffect(() => {
    //     channelsRef.on('child_added', (snap) => {
    //         setChannelsState((currentState) => {
    //             let updatedState = [...currentState];
    //             updatedState.push(snap.val());
    //             return updatedState;
    //         })
    //     });
    //
    //     return () => channelsRef.off();
    // }, [])
    //
    // useEffect(()=> {
    //     if (channelsState.length > 0) {
    //         props.selectChannel(channelsState[0])
    //     }
    // },[!props.channel ?channelsState : null ])

    const openModal = () => {
        setModalOpenState(true);
    }

    const closeModal = () => {
        setModalOpenState(false);
    }

    const checkIfFormValid = () => {
        return channelAddState.channelName;
    }

    const displayChannels = () => {
        fetch('http://localhost:8080/api/channels', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                const dataContainer = document.getElementById('data-container');
                data.forEach(item => {
                    const itemElement = document.createElement('div');
                    itemElement.textContent = `${item.channelname}`;
                    dataContainer.appendChild(itemElement);
                });
            })
            .catch((error) => {
                console.error('Error:', error);
                // TODO: Send an error message to the user
            });

    }

    // const selectChannel = (channel) => {
    //     setLastVisited(props.user,props.channel);
    //     setLastVisited(props.user,channel);
    //     props.selectChannel(channel);
    // }
    //
    // const setLastVisited = (user, channel) => {
    //     const lastVisited = usersRef.child(user.uid).child("lastVisited").child(channel.id);
    //     lastVisited.set(firebase.database.ServerValue.TIMESTAMP);
    //     lastVisited.onDisconnect().set(firebase.database.ServerValue.TIMESTAMP);
    // }

    const goToMessages = (event) => {
        event.preventDefault();
        window.location = '/message'
    }

        const onSubmit = (event) => {

        // if (!checkIfFormValid()) {
        //     return;
        // }

        event.preventDefault();
        const channelData = new FormData(event.currentTarget);
        const channelName = channelData.get('channelName');
        // const channelName =  event.currentTarget;
        console.log({channelName});
    }

    const onSubmit = (event) => {
        event.preventDefault();

        if (!checkIfFormValid()) {
            console.log("Form is not valid");
            return;
        }

        const channel = {
            channelName: channelAddState.channelName,
            accessibility: true,
            visible: true
        };

        console.log("Submitting channel:", channel);

        fetch('http://localhost:8080/api/channels', {
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
                // Update state or perform other actions as necessary
            })
            .catch((error) => {
                console.error('Error:', error);
                // Handle the error appropriately
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
            ({channelsState.length})
        </Menu.Item>
        {/*{displayChannels()}*/}
        <Menu.Item>
            <span className="clickable" onClick={openModal} >
                <Icon name="add" /> New Channel
            </span>
        </Menu.Item>
        <Menu.Item>
            <span className="clickable" onClick={displayChannels} >
                <Icon name="eye" /> View Channels
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