import React, { useState, useEffect } from 'react';
import './Workspaces.css';
import { Menu, Icon, Dropdown, Modal, Button, Form, Segment } from 'semantic-ui-react';
import { getWorkspacesForUser, createWorkspaceByUser } from "../../../api/workspaceApi";
import { BsPersonWorkspace } from "react-icons/bs";


const Workspaces = () => {
    const [workspaces, setWorkspaces] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
    const userId = sessionStorage.getItem('userId');
    const [workspaceAddState, setWorkspaceAddState] = useState({ workspaceName: '' });

    useEffect(() => {
        getWorkspacesForUser(userId)
            .then(data => {
                setWorkspaces(data);
            })
            .catch(error => {
                console.error('Error fetching workspaces:', error);
            });
    }, [userId]);

    const openModal = () => setModalOpen(true);
    const closeModal = () => setModalOpen(false);

    const handleInput = (event) => {
        const { name, value } = event.target;
        setWorkspaceAddState(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        const workspacePayload = {
            workspaceName: workspaceAddState.workspaceName,
            accessible: true,
            visible: true
        };

        console.log('Creating workspace:', workspacePayload);
        createWorkspaceByUser(userId, workspacePayload)
            .then(() => {
                closeModal();
                return getWorkspacesForUser(userId);
            })
            .then(setWorkspaces)
            .catch(error => {
                console.error('Error creating workspace:', error);
            });
    };

    return <> <Menu.Menu style={{ marginTop: '13px' }}>
        <Dropdown item text='Workspaces' style={{fontSize : '20px'}}>
            <Dropdown.Menu>
                {workspaces.map(workspace => (
                    <Dropdown.Item key={workspace.workspaceId}>
                        {workspace.workspaceName}
                    </Dropdown.Item>
                ))}
                <Dropdown.Divider />
                <Dropdown.Item onClick={openModal}>
                    <Icon name="add" /> New Workspace
                </Dropdown.Item>
            </Dropdown.Menu>
        </Dropdown>
    </Menu.Menu>

        <Modal open={modalOpen} onClose={closeModal}>
            <Modal.Header>
                Create Workspace
            </Modal.Header>
            <Modal.Content>
                <Form onSubmit={handleSubmit}>
                    <Segment stacked>
                        <Form.Input
                            name="workspaceName"
                            value={workspaceAddState.workspaceName}
                            onChange={handleInput}
                            type="text"
                            placeholder="Enter Workspace Name"
                        />
                    </Segment>
                    <Button type="submit">
                        <Icon name="checkmark" /> Save
                    </Button>
                    <Button onClick={closeModal}>
                        <Icon name="remove" /> Cancel
                    </Button>
                </Form>
            </Modal.Content>
        </Modal>
    </>
    ;
};

export default Workspaces;