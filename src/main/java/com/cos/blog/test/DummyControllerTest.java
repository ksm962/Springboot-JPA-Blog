package com.cos.blog.test;

import java.sql.Timestamp;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.Name;
import com.cos.blog.model.RoleType;
import com.cos.blog.repository.NameRepository;



//html파일이 아니라 data 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {

	
	
	@Autowired //의존성 주입(di)
	private NameRepository nameRepository;
	 
	@GetMapping("/dummy/names")
	public List<Name> list(){
		return nameRepository.findAll();
	}
	

	//save 함수는 id를 전달하지않으면 insert해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.
	
	@DeleteMapping("/dummy/name/{id}")
	public String delete(@PathVariable int id) {
		try {
			nameRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다.";
		}
		return "삭제되었습니다. id : " +id;
	}
	
	@Transactional //함수종료시 자동 commit
	@PutMapping("/dummy/name/{id}")
	public Name updateName(@PathVariable int id,
			@RequestBody Name requestName) { //requestBody는 json으로 받을떄 사용함.
	
		
		System.out.println("id : " + id);
		System.out.println("password : "+ requestName.getPassword());
		System.out.println("email : " + requestName.getEmail());
		//람다식
		Name name = nameRepository.findById(id).orElseThrow(()->{
				return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		name.setPassword(requestName.getPassword());
		name.setEmail(requestName.getEmail());

//		nameRepository.save(requestName);
//		save함수가 없어도 @Transactional 어노테이션을 사용하면 update되는데  더티채킹이라고 한다
		return null;
	}
	@GetMapping("/dummy/name")
	public List<Name> pageList(@PageableDefault(size = 2, direction = Sort.Direction.DESC)Pageable pageable){
		Page<Name> pagingName =  nameRepository.findAll(pageable);
		
		List<Name> names = pagingName.getContent();
		return names;
	}
	
	
	
	
	//{id} 주소로 파마레터를 받을 수 있음
	//http://localhost:8000/blog/dummy/name/{id}
	@GetMapping("/dummy/name/{id}")
	public Name detail(@PathVariable int id) {
		
		//참고로 findByld타입이 Optional 이유
		//id값을 못찾으면 null이 될것 아닌가?
		//그럼 return null이 되는데 문제가 있지 않나?
		//Optional로 너의 Name 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해
		//에외식
		Name name = nameRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당유저는 없습니다. id : " + id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 : 자바 오브젝트
		// 변환 -> json
		//스프링 부트 = MessageConverter라는 애가 응답시에 자동 작동
		//
		return name;
	}
	
	@GetMapping("/dummy/join")
	public String join(Name name) {
	
//		name.setUsername("cos");
//		name.setPassword("1234");
//		name.setEmail("cos@naver.com");
//		name.setRole(RoleType.USER);
		
		System.out.println("id : "+name.getId());
		System.out.println("username : "+name.getUsername());
		System.out.println("password : "+name.getPassword());
		System.out.println("email : "+name.getEmail());
		System.out.println("role : "+ name.getRole());
		
		nameRepository.save(name); //insert 할때 사용
		
		return "더미조인";
	}
}
