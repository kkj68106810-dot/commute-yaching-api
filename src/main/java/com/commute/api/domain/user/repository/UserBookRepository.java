package com.commute.api.domain.user.repository;

import com.commute.api.domain.user.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserBookRepository
 *
 * @author Administrator
 * @version 1.0.0
 * @since 2026/10/08
 */
public interface UserBookRepository extends JpaRepository<UserBook, Long> {

}
