package com.example.demo.services;

import com.example.demo.dtos.WorkspaceDto;
import com.example.demo.models.Workspace;
import com.example.demo.repositories.WorkspaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public void getWorkspaceByIdTest() throws Exception {
        given(workspaceRepository.findById(1L)).willReturn(Optional.of(workspace));

        Workspace found = workspaceService.getWorkspaceById(1L);

        assertNotNull(found);
    }

    @Test
    public void getWorkspaceByIdNotFoundTest() {
        given(workspaceRepository.findById(0L)).willReturn(Optional.empty());

        assertThrows(Exception.class, () -> workspaceService.getWorkspaceById(0L));
    }

    @Test
    public void getAllWorkspacesTest() {
        Workspace workspace1 = new Workspace();
        Workspace workspace2 = new Workspace();
        List<Workspace> expected = Arrays.asList(workspace1, workspace2);
        given(workspaceRepository.findAll()).willReturn(expected);

        List<Workspace> actual = workspaceService.getAllWorkspaces();

        assertEquals(expected, actual);
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
