import React from 'react';
import { Grid, Header, Dropdown } from 'semantic-ui-react';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import SentimentVerySatisfiedIcon from '@mui/icons-material/SentimentVerySatisfied';
import "./UserInfo.css";

const UserInfo = () => {

    const getDropDownOptions = () => {
        return [{
            key: 'signout',
            text: <span onClick={signOut} >Sign Out</span>
        }]
    }

    const signOut = () => {
        sessionStorage.clear()
        console.log("user signed out");
        window.location.href = '/signin';
    }

        return (<Grid>
            <Grid.Column>
                <Grid.Row className="userinfo_grid_row">
                    <Header as="h2">
                        <SentimentVerySatisfiedIcon></SentimentVerySatisfiedIcon>
                        <Header.Content>YeeChat</Header.Content>
                    </Header>
                    <Header className="userinfo_displayname" as="h4">
                        <Dropdown
                            trigger={
                                <span>
                                        <AccountCircleIcon></AccountCircleIcon>
                                    {sessionStorage.getItem('username')}
                                    </span>
                            }
                            options={getDropDownOptions()}
                        >
                        </Dropdown>

                    </Header>
                </Grid.Row>
            </Grid.Column>
        </Grid>)
    }

export default UserInfo;