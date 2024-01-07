package com.example.demo.services;

import com.example.demo.dtos.WorkspaceDto;
import com.example.demo.models.Workspace;
import com.example.demo.repositories.WorkspaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkspaceServiceTest {

    @Mock
    private WorkspaceRepository workspaceRepository;

    @Mock
    private Workspace workspace;

    @InjectMocks
    private WorkspaceService workspaceService;

    @Test
    public void createWorkspaceTest() {
        given(workspaceRepository.save(any(Workspace.class))).willReturn(new Workspace());

        Workspace created = workspaceService.createWorkspace(new WorkspaceDto());

        assertNotNull(created);
    }

    @Test
    public void getWorkspaceByIdTest() {

    }

    @Test
    public void getWorkspaceByIdNotFoundTest() {

    }

    @Test
    public void getAllWorkspacesTest() {

    }

    @Test
    public void updateWorkspaceTest() {

    }

    @Test
    public void updateWorkspaceNotFoundTest() {

    }

    @Test
    public void deleteWorkspaceTest() {

    }

    @Test
    public void deleteWorkspaceNotFoundTest() {

    }
}
