package org.zerock.mapper;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// Java Config
//@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class BoardmapperTests {

	//BoardMapper인터페이스의 구현체를 주입받아 동작.
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	  @Test public void testInsert() { BoardVO board = new BoardVO();
	  board.setTitle("새로 작성하는글"); board.setContent("새로 작성하는 내용");
	  board.setWriter("newbie");
	  
	  mapper.insert(board);
	  
	  log.info(board); }
	 
	
	
	/*
	 * @Test public void testInsertSelectKey() { BoardVO board = new BoardVO();
	 * board.setTitle("새로 작성하는글 select key");
	 * board.setContent("새로 작성하는 내용 select key"); board.setWriter("newbie");
	 * 
	 * mapper.insertSelectKey(board); log.info(board); }
	 */
	
	/*
	 * @Test public void testRead() {
	 * 
	 * //존재하는 게시물 번호로 테스트 BoardVO board = mapper.read(5L);
	 * 
	 * log.info(board); }
	 */
	
	/*
	 * @Test public void testDelete() { log.info("DELETE COUNT: " +
	 * mapper.delete(3L)); }
	 */
	
	/*
	 * @Test public void update() { BoardVO board = new BoardVO(); //실행전 존재하는 번호인지
	 * 확인할것 board.setBno(5L); board.setTitle("수정된 제목"); board.setContent("수정된 내용");
	 * board.setWriter("writer");
	 * 
	 * int count = mapper.update(board); log.info("UPDATE COUNT: "+ count);
	 * 
	 * }
	 */
	
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		
		//10개씩 출력하는 3페이지에 해당하는 데이터
		cri.setPageNum(2);
		cri.setAmount(10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
	}
	
}
