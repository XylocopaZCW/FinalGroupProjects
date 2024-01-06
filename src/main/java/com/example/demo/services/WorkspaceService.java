package com.example.demo.services;

import com.example.demo.dtos.WorkspaceDto;
import com.example.demo.models.Workspace;
import com.example.demo.repositories.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public Workspace createWorkspace(WorkspaceDto workspaceDto) {
        Workspace newWorkspace = new Workspace();
        newWorkspace.setName(workspaceDto.getName());
        newWorkspace.setAccessible(workspaceDto.getAccessible());
        newWorkspace.setVisible(workspaceDto.getVisible());
        return workspaceRepository.save(newWorkspace);
    }

    public Workspace getWorkspaceById(Long workspaceId) throws Exception {
        return workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new Exception("Workspace doesn't exist"));
    }

    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    public Workspace updateWorkspace(Long workspaceId, WorkspaceDto workspaceDto) throws Exception {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new Exception("Workspace doesn't exist"));
        workspace.setName(workspaceDto.getName());
        workspace.setAccessible(workspaceDto.getAccessible());
        workspace.setVisible(workspaceDto.getVisible());
        return workspaceRepository.save(workspace);
    }

    public void deleteWorkspace(Long workspaceId) throws Exception {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new Exception("Workspace doesn't exist"));
        workspaceRepository.delete(workspace);
    }
}
