package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	//컨트롤러가 요청을 받으면 적절한 서비스에 전달하고 전달받은 서비스는 비즈니스 로직을 처리한다.
	
	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(Long bno);
	
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
}
