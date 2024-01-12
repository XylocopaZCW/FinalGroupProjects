import React, { useState, useEffect } from 'react';
import './Channels.css';
import { Menu, Icon, Modal, Button, Form, Segment } from 'semantic-ui-react';


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

    // const displayChannels = () => {
    //     if (channelsState.length > 0) {
    //         return channelsState.map((channel) => {
    //             return <Menu.Item
    //                 key={channel.id}
    //                 name={channel.name}
    //                 onClick={() => selectChannel(channel)}
    //                 active={props.channel && channel.id === props.channel.id && !props.channel.isFavourite}
    //             >
    //                 <Notification user={props.user} channel={props.channel}
    //                               notificationChannelId={channel.id}
    //                               displayName= {"# " + channel.name} />
    //
    //             </Menu.Item>
    //         })
    //     }
    // }

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
            {/*<Modal.Actions>*/}
            {/*    */}
            {/*</Modal.Actions>*/}
        </Modal>
    </>
}

export default Channels;