import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {Icon} from "semantic-ui-react";

// TODO remove, this demo shouldn't need to reset the theme.

const defaultTheme = createTheme();

export default function Messages() {

    const onSubmitHandler = (event) => {
        event.preventDefault();
        submitMessage(event);
        clearMessages();
        viewMessages();
        clearMessages();
        viewMessages();
    };

    const clearMessages = () => {
        const dataContainer = document.getElementById('message-container');
        dataContainer.innerHTML = '';
    }

    const viewMessages = () => {
        fetch('http://localhost:8080/api/messages/getAllMessages', {
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
                    itemElement.textContent = `${item.date} ${sessionStorage.getItem('username') ?? "Sudo Kode"} : "${item.body}"`;
                    dataContainer.appendChild(itemElement);
                });
            })
            .catch((error) => {
                console.error('Error:', error);
                // TODO: Send an error message to the user
            });
    }

    const submitMessage = (event) => {
        event.preventDefault();
        const messageData = new FormData(event.currentTarget);
        const messagebody = messageData.get('message');
        console.log({messagebody});

        const message = {
            body: messagebody
        };

        const userId = sessionStorage.getItem("userId") ?? "1"

        fetch(`http://localhost:8080/api/messages/sendMessage/${userId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(message)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
                // TODO: Send an error message to the user
            });
    };

    return (<>
            <div id="message-container" align="center"></div>
            <ThemeProvider theme={defaultTheme}>
                <Container component="main" maxWidth="xs">
                    <CssBaseline/>
                    <Box
                        sx={{
                            marginTop: 8,
                            display: 'flex',
                            flexDirection: 'column',
                            alignItems: 'center',
                        }}
                    >
                        <Box component="form" onSubmit={onSubmitHandler} noValidate sx={{mt: 1}}>
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="message"
                                label="Enter message"
                                name="message"
                                autoComplete="message"
                                autoFocus
                            />
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                sx={{mt: 3, mb: 2}}
                            >
                                Send Message
                            </Button>
                        </Box>
                    </Box>
                </Container>
            </ThemeProvider>
        </>
    );
}