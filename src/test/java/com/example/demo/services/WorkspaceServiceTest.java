package com.example.demo.services;

import com.example.demo.dtos.WorkspaceDto;
import com.example.demo.models.User;
import com.example.demo.models.Workspace;
import com.example.demo.repositories.UserRepository;
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
    private UserRepository userRepository;

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
    public void addUserToWorkspaceTest() throws Exception {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("TestUser");
        Workspace workspace1 = new Workspace();
        workspace1.setWorkspaceId(1L);
        given(workspaceRepository.findById(1L)).willReturn(Optional.of(workspace1));
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(workspaceRepository.save(any(Workspace.class))).willAnswer(invocation -> invocation.getArgument(0));
        given(userRepository.save(any(User.class))).willAnswer(invocation -> invocation.getArgument(0));

        User actual = workspaceService.addUserToWorkspace(1L, 1L);

        assertNotNull(actual);
        assertEquals("TestUser", actual.getUsername());
        assertTrue(actual.getWorkspaces().contains(workspace1));
        assertTrue(workspace1.getUsers().contains(actual));
    }

    @Test
    public void addUserToWorkspaceUserNotFoundTest() {
        User user = new User();
        user.setUserId(1L);
        Workspace workspace1 = new Workspace();
        workspace1.setWorkspaceId(1L);
        given(workspaceRepository.findById(1L)).willReturn(Optional.of(workspace1));
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(workspaceRepository.save(any(Workspace.class))).willAnswer(invocation -> invocation.getArgument(0));

        assertThrows(Exception.class, () -> workspaceService.addUserToWorkspace(1L, 2L));
    }

    @Test
    public void addUserToWorkspaceWorkspaceNotFoundTest() {
        User user = new User();
        user.setUserId(1L);
        Workspace workspace1 = new Workspace();
        workspace1.setWorkspaceId(1L);
        given(workspaceRepository.findById(1L)).willReturn(Optional.of(workspace1));
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(workspaceRepository.save(any(Workspace.class))).willAnswer(invocation -> invocation.getArgument(0));

        assertThrows(Exception.class, () -> workspaceService.addUserToWorkspace(2L, 1L));
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
