package com.startline.startline.repository.user;

import com.startline.startline.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Eugene Petrov
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
