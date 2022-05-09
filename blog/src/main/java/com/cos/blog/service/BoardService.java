package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;


//트랜잭션 : 일이 처리되기위한 가장 작은 단위, 트랜잭션들이 묶여서 하나의 트랜잭션이 될 수 있다. 
//서비스 : 여러개의 트랜잭션이 묶여서 동작을 수행하는것
@Service //스프링이 컴포넌트 스캔을 통해 빈에 등록(IOC)
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;  
	
	//트랜잭션화 밑의 수행들이 실패가되면 ROLLBACK 성공하면 COMMIT이 수행됨
	@Transactional
	public void 글쓰기(Board board, User user) { //title, content
		board.setCount(0);
		board.setUser(user);
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
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다");
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
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다");
				}); //영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당 함수 종료시(service가 종료될때) 트랜잭션이 종료됨. 이때 더티체킹을 통해 자동업데이트가됨(flush)
	}

}
