package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

// 얘는 DAO라고 보면됨
// 자동으로 bean으로 등록이 된다.
// @Repository  -> 생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{

}
