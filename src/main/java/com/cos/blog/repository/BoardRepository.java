package com.cos.blog.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.Board;
import com.cos.blog.model.Name;




//DAO
//자동으로 빈 등록
//@Repository //생략가능
public interface BoardRepository extends JpaRepository<Board, Integer> {


}









//방법1
//JPA Naming 전략
// SELECT * FROM name where username = ? and password = ?;
//Name findByUsernameAndPassword(String username, String password);

//방법2
//@Query(value="SELECT * FROM name where username = ? and password = ?", nativeQuery = true)
//Name login(String username, String password);