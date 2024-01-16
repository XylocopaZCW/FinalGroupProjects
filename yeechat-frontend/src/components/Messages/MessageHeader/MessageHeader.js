import React, {useState} from 'react';
import { Segment, Header, Input, Icon } from 'semantic-ui-react';

const MessageHeader = (props) => {

    const [channelName, setChannelName] = useState("");

    const fetchChannelName = (channelId) => {
        fetch(`http://localhost:8080/api/channels/${channelId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                setChannelName(data.channelName)
            })
            .catch((error) => {
                console.error('Error:', error);
                // TODO: Send an error message to the user
            });
    }

    fetchChannelName(sessionStorage.getItem('channelId'));
    const channelMapString = sessionStorage.getItem('channelMap');
    const channelMap = channelMapString ? JSON.parse(channelMapString) : {};

    return <Segment clearing>
        <Header floated="left" fluid="true" as="h2"  style={{fontSize : '2.2rem'}}>
            <span>
                {(props.isPrivateChat ? "@ " : "# ") + channelMap[sessionStorage.getItem('channelId')]}
                {!props.isPrivateChat && <Icon
                    onClick={props.starChange}
                    name={props.starred ? "star" : "star outline"}
                    color={props.starred ? "yellow" : "black"} />}
            </span>
            <Header.Subheader  style={{fontSize : '1.4rem'}}> {props.uniqueUsers} User{props.uniqueUsers === 1 ? "" : "s"}</Header.Subheader>
        </Header>
        {/*<Header floated="right">*/}
        {/*    <Input*/}
        {/*        name="search"*/}
        {/*        icon="search"*/}
        {/*        placeholder="Search Messages"*/}
        {/*        size="mini"*/}
        {/*        onChange={props.searchTermChange}*/}
        {/*    />*/}
        {/*</Header>*/}

    </Segment>
}

export default MessageHeader;