package com.cos.blog.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Name;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.NameRepository;
import com.cos.blog.repository.ReplyRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. loc를 해준다
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;
	@Autowired
	private NameRepository nameRepository;
	@Transactional
	public void 글쓰기(Board board, Name name) { //title, content
		board.setCount(0);
		board.setName(name);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) { 
		return boardRepository.findAll(pageable);
	}
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을수 없습니다.");
				});
	}
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 수정하기 실패 : 아이디를 찾을수 없습니다.");
				}); //영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		
		//해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 -자동업데이트가 됨
		
	}
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) { //title, content

		Name name = nameRepository.findById(replySaveRequestDto.getNameid()).orElseThrow(()->{
			return new IllegalArgumentException("댓글쓰기실패 : 유저 id 엄슴");
		});
		
		Board board = boardRepository.findById(replySaveRequestDto.getBoardid()).orElseThrow(()->{
				return new IllegalArgumentException("댓글쓰기실패 : 게시글 id 엄슴");
			});
		Reply reply =Reply.builder()
				.name(name)
				.board(board)
				.content(replySaveRequestDto.getContent())
				.build();

		replyRepository.save(reply);
	}
	
}
	
	
	
	
	
	
	
	
	//전통적인방식
//	@Transactional(readOnly = true)//select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//	public Name 로그인(Name name) {
//		return nameRepository.findByUsernameAndPassword(name.getUsername(),name.getPassword());
//		
//	}
	

