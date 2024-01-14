import React, { useState, useEffect } from 'react';
import './Workspaces.css';
import { Menu, Icon, Modal, Button, Form, Segment } from 'semantic-ui-react';
import { getWorkspacesForUser, createWorkspaceByUser } from "../../../api/workspaceApi";
import { BsPersonWorkspace } from "react-icons/bs";


const Workspaces = () => {
    const [workspaces, setWorkspaces] = useState([]);
    const userId = sessionStorage.getItem('userId');

    useEffect(() => {
        getWorkspacesForUser(userId)
            .then(data => {
                setWorkspaces(data);
            })
            .catch(error => {
                console.error('Error fetching workspaces:', error);
            });
    }, [userId]);

    return <> <Menu.Menu style={{ marginTop: '13px' }}>
        <Menu.Item style={{fontSize : '18px'}}>
            <span>
                {/*<Icon name="exchange" /> Workspaces*/}
               <BsPersonWorkspace /> Workspace

            </span>
            ({workspaces.length})
        </Menu.Item>
        {workspaces.map(workspace => (
            <Menu.Item key={workspace.workspaceId}>
                {workspace.workspaceName}
            </Menu.Item>
        ))}
        {/*<Menu.Item>*/}
        {/*    <span className="clickable" onClick={openModal} >*/}
        {/*        <Icon name="add" /> New Workspace*/}
        {/*    </span>*/}
        {/*</Menu.Item>*/}
    </Menu.Menu>
        {/*<Modal open={modalOpenState} onClose={closeModal}>*/}
        {/*    <Modal.Header>*/}
        {/*        Create Workspace*/}
        {/*    </Modal.Header>*/}
        {/*    <Modal.Content>*/}
        {/*        <Form onSubmit={onSubmit}>*/}
        {/*            <Segment stacked>*/}
        {/*                <Form.Input*/}
        {/*                    name="workspaceName"*/}
        {/*                    value={workspaceAddState.workspaceName}*/}
        {/*                    onChange={handleInput}*/}
        {/*                    type="text"*/}
        {/*                    placeholder="Enter Workspace Name"*/}
        {/*                />*/}
        {/*            </Segment>*/}
        {/*            <Button loading={isLoadingState} type="submit">*/}
        {/*                <Icon name="checkmark" /> Save*/}
        {/*            </Button>*/}
        {/*            <Button onClick={closeModal}>*/}
        {/*                <Icon name="remove" /> Cancel*/}
        {/*            </Button>*/}
        {/*        </Form>*/}
        {/*    </Modal.Content>*/}
        {/*</Modal>*/}
    </>
    ;
};

export default Workspaces;