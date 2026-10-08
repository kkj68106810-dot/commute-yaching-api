package com.commute.api.domain.user.repository;

import com.commute.api.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 *
 * @author Administrator
 * @version 1.0.0
 * @since 2026/10/08
 */
public interface UserRepository extends JpaRepository<User, Long> {


}
