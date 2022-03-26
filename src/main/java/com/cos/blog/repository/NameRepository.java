package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.Name;


//DAO
//자동으로 빈 등록
//@Repository //생략가능
public interface NameRepository extends JpaRepository<Name, Integer> {

}
