package com.cos.blog.controller.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;

import com.cos.blog.service.BoardService;


@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;


	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, 
	@AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글쓰기(board, principal.getName()); //네임물고오기
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //성공했다는것이 200
	}
	

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteByid(@PathVariable int id){
			boardService.글삭제하기(id);
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //성공했다는것이 200
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, 
			@RequestBody Board board){ 	
		System.out.println(board.getContent());
		System.out.println("id : " + id);
		
			boardService.글수정하기(id, board);
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //성공했다는것이 200
	}
	
	
	@PostMapping("/api/board/${boardid}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto ) {
		boardService.댓글쓰기( replySaveRequestDto); //네임물고오기
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //성공했다는것이 200
	}
	
}
