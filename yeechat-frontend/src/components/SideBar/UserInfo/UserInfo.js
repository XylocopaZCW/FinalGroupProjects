import React from 'react';
import { Grid, Header, Dropdown } from 'semantic-ui-react';
// import AccountCircleIcon from '@mui/icons-material/AccountCircle';
// import SentimentVerySatisfiedIcon from '@mui/icons-material/SentimentVerySatisfied';
import "./UserInfo.css";
import { TbEyeEdit } from "react-icons/tb";
import { CgUserlane } from "react-icons/cg";


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
                    <Header as="h1">
                        {/*<SentimentVerySatisfiedIcon></SentimentVerySatisfiedIcon>*/}
                        <TbEyeEdit></TbEyeEdit>
                        <Header.Content style={{fontSize : '3rem'}}>YeeChat</Header.Content>
                    </Header>
                    <Header className="userinfo_displayname" as="h3">
                        <Dropdown
                            trigger={
                                <span style={{fontSize : '1.6rem'}}>
                                        {/*<AccountCircleIcon></AccountCircleIcon>*/}
                                        <CgUserlane></CgUserlane>

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