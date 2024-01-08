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
    public void updateWorkspaceTest() throws Exception {
        Workspace original = new Workspace();
        original.setName("Original");
        original.setAccessible(false);
        original.setVisible(false);
        original.setWorkspaceId(1L);
        given(workspaceRepository.findById(1L)).willReturn(Optional.of(original));
        given(workspaceRepository.save(any(Workspace.class))).willAnswer(invocation -> invocation.getArgument(0));

        WorkspaceDto updated = new WorkspaceDto();
        updated.setName("New");
        updated.setAccessible(true);
        updated.setVisible(true);
        Workspace actual = workspaceService.updateWorkspace(1L, updated);

        assertNotNull(actual);
        assertEquals("New", actual.getName());
        assertTrue(actual.getAccessible());
        assertTrue(actual.getVisible());
    }

    @Test
    public void updateWorkspaceNotFoundTest() {
        Workspace original = new Workspace();
        original.setWorkspaceId(1L);
        given(workspaceRepository.findById(1L)).willReturn(Optional.of(original));
        given(workspaceRepository.save(any(Workspace.class))).willAnswer(invocation -> invocation.getArgument(0));

        WorkspaceDto updated = new WorkspaceDto();

        assertThrows(Exception.class, () -> workspaceService.updateWorkspace(2L, updated));
    }

    @Test
    public void deleteWorkspaceTest() throws Exception {
        given(workspaceRepository.findById(1L)).willReturn(Optional.of(workspace));

        workspaceService.deleteWorkspace(1L);

        verify(workspaceRepository).delete(workspace);
    }

    @Test
    public void deleteWorkspaceNotFoundTest() {
        Workspace original = new Workspace();
        original.setWorkspaceId(1L);
        given(workspaceRepository.findById(1L)).willReturn(Optional.of(original));
        given(workspaceRepository.save(any(Workspace.class))).willAnswer(invocation -> invocation.getArgument(0));

        assertThrows(Exception.class, () -> workspaceService.deleteWorkspace(2L));
    }

    @Test
    public void addUserToWorkspaceTest() {

    }

    @Test
    public void addUserToWorkspaceUserNotFoundTest() {

    }

    @Test
    public void addUserToWorkspaceWorkspaceNotFoundTest() {

    }

    @Test
    public void removeUserFromWorkspaceTest() {

    }

    @Test
    public void removeUserFromWorkspaceUserNotFoundTest() {

    }

    @Test
    public void removeUserFromWorkspaceWorkspaceNotFoundTest() {

    }

    @Test
    public void getAllUsersInWorkspaceTest() {

    }
}
