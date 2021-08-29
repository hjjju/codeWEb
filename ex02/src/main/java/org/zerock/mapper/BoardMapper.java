package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	//SQL문으로 처리했으니 어노테이션은 생략
//	@Select("select * from tbl_board where bno > 0 ")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO vo);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public List<BoardVO> getListWithPaging(Criteria cri);	
}
