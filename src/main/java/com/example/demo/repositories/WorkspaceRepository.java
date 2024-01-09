package com.example.demo.repositories;

import com.example.demo.models.User;
import com.example.demo.models.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}
