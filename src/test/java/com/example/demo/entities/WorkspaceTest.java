package com.example.demo.entities;

import com.example.demo.models.Workspace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkspaceTest {

    @Test
    public void workspaceSettersAndGettersTest() {
        Workspace workspace = new Workspace();
        workspace.setWorkspaceId(1L);
        workspace.setName("Test");
        workspace.setAccessible(true);
        workspace.setVisible(true);

        assertEquals(1L, workspace.getWorkspaceId());
        assertEquals("Test", workspace.getName());
        assertTrue(workspace.getAccessible());
        assertTrue(workspace.getVisible());
    }


}
