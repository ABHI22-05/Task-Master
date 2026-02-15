package com.sneakerhead.backend.repository;

import com.sneakerhead.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssigneeId(Long assigneeId);

    List<Task> findByCreatorId(Long creatorId);

    List<Task> findByProjectId(Long projectId);

    List<Task> findByStatus(Task.Status status);

    List<Task> findByAssigneeIdAndStatus(Long assigneeId, Task.Status status);

    @Query("SELECT t FROM Task t WHERE t.assignee.id = :userId OR t.creator.id = :userId")
    List<Task> findTasksByUserId(@Param("userId") Long userId);

    @Query("SELECT t FROM Task t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Task> searchTasks(@Param("keyword") String keyword);
}
