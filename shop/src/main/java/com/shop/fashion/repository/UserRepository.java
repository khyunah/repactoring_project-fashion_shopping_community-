package com.shop.fashion.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.fashion.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	// 회원가입시 아이디 중복체크
	//User findByUsername(String username);
	
	Optional<User> findByUsername(String username);

	@Query(value = "SELECT * FROM user WHERE id = :id", nativeQuery = true)
	Page<User> mFindById(@Param("id") int id, Pageable pageable);

	@Query(value = "SELECT * FROM user WHERE username LIKE %:username%", nativeQuery = true) // %: % 여기에 :콜롬 필수
	Page<User> mFindByUsername(@Param("username") String username, Pageable pageable);

	@Query(value = "SELECT * FROM user WHERE name LIKE %:name%", nativeQuery = true)
	Page<User> mFindByName(@Param("name") String name, Pageable pageable);

	@Query(value = "SELECT * FROM user WHERE address LIKE %:address%", nativeQuery = true)
	Page<User> mFindByAddress(@Param("address") String address, Pageable pageable);

	@Query(value = "SELECT * FROM user WHERE oauth = :oauth", nativeQuery = true)
	Page<User> mFindByOauth(@Param("oauth") String oauth, Pageable pageable);
	
	@Query(value = "SELECT * FROM user WHERE DAYOFYEAR(createDate) = DAYOFYEAR(now())", nativeQuery = true)
	Page<User> mFindByTodayJoinUser(Pageable pageable);

}
