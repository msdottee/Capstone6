package co.grandcircus.Capstone6.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.Capstone6.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUserId(Long userId);

	Optional<Task> findByUserIdAndId(Long userId, Long id);
}
