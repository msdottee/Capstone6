package co.grandcircus.Capstone6.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.Capstone6.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmailAndPassword(String email, String password);
}
