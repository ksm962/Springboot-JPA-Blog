package com.cos.blog.api;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Name;
import com.cos.blog.model.RoleType;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.NameService;

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

}
