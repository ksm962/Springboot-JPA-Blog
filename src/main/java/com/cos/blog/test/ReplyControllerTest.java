package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;


public class ReplyControllerTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping({"/test/board/{id}"})
	public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get(); //보드를 호출해서 리플리를 뿌리지만 리플리안에 보드가
																				//또 있기때문에 보드를호출 -> 무한 참조, @JsonIgnoreProperties("board")

	}

}
