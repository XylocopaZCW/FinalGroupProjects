import React, { useEffect, useState,useRef } from 'react';

import MessageHeader from './MessageHeader/MessageHeader';
import MessageContent from "./MessageContent/MessageContent";
import MessageInput from "./MessageInput/MessageInput";
import { Segment, Comment } from 'semantic-ui-react';
import "./Messages.css";

const Messages = (props) => {

    const [messagesState, setMessagesState] = useState([]);

    const [searchTermState, setSearchTermState] = useState("");

    let divRef = useRef();

    const displayMessages = () => {
        let messagesToDisplay =  messagesState;
        console.log(messagesToDisplay)
        if (messagesToDisplay.length > 0) {
            return messagesToDisplay.map((message) => {
                return <MessageContent message={message} />
            })
        }
    }

    const uniqueusersCount = () => {
        const uniqueUsers = messagesState.reduce((acc, message) => {
            if (!acc.includes(message.user.name)) {
                acc.push(message.user.name);
            }
            return acc;
        }, []);

        return uniqueUsers.length;
    }

    return <div className="messages"><MessageHeader isPrivateChat={props.channel?.isPrivateChat} channelName={props.channel?.name} uniqueUsers={uniqueusersCount()} />
        <Segment className="messagecontent">
            <Comment.Group>
                <div id="message-container" align="left"></div>
                {displayMessages()}
                <div ref={currentEl => divRef = currentEl}></div>
            </Comment.Group>
        </Segment>
        <MessageInput/></div>
}

export default Messages;