package com.example.demo.controllers;

import com.example.demo.dtos.WorkspaceDto;
import com.example.demo.services.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @Autowired
    public WorkspaceController(WorkspaceService workspaceService) {

        this.workspaceService = workspaceService;
    }

    @PostMapping()
    public ResponseEntity<?> createWorkspace(@RequestBody WorkspaceDto workspaceDto) {
        return new ResponseEntity<>(workspaceService.createWorkspace(workspaceDto), HttpStatus.CREATED);
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<?> getWorkspaceById(@PathVariable Long workspaceId) throws Exception {
        return new ResponseEntity<>(workspaceService.getWorkspaceById(workspaceId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllWorkspaces() {
        return new ResponseEntity<>(workspaceService.getAllWorkspaces(), HttpStatus.OK);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<?> updateWorkspace(@PathVariable Long workspaceId, @RequestBody WorkspaceDto workspaceDto) throws Exception {
        return new ResponseEntity<>(workspaceService.updateWorkspace(workspaceId, workspaceDto), HttpStatus.OK);
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<?> deleteWorkspace(@PathVariable Long workspaceId) throws Exception {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{workspaceId}/users")
    public ResponseEntity<?> addUserToWorkspace(@PathVariable Long workspaceId, @RequestBody Long userId) throws Exception {
        return new ResponseEntity<>(workspaceService.addUserToWorkspace(workspaceId, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{workspaceId}/users/{userId}")
    public ResponseEntity<?> removeUserFromWorkspace(@PathVariable Long workspaceId, @PathVariable Long userId) throws Exception {
        workspaceService.removeUserFromWorkspace(workspaceId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{workspaceId}/users")
    public ResponseEntity<?> getAllUsersInWorkspace(@PathVariable Long workspaceId) throws Exception {
        return new ResponseEntity<>(workspaceService.getAllUsersInWorkspace(workspaceId), HttpStatus.OK);
    }

    @GetMapping("{workspaceId}/channels")
    public ResponseEntity<?> getAllChannelsInWorkspace(@PathVariable Long workspaceId) throws Exception {
        return new ResponseEntity<>(workspaceService.getAllChannelsInWorkspace(workspaceId), HttpStatus.OK);
    }

    @PostMapping("{workspaceId}/channels/{channelId}")
    public ResponseEntity<?> addChannelToWorkspace(@PathVariable Long workspaceId, @PathVariable Long channelId) throws Exception {
        return new ResponseEntity<>(workspaceService.addChannelToWorkspace(workspaceId, channelId), HttpStatus.OK);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<?> getWorkspacesForUser(@PathVariable Long userId) throws Exception {
        return new ResponseEntity<>(workspaceService.getWorkspacesForUser(userId), HttpStatus.OK);
    }
}
