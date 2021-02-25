package edu.bit.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.vo.BoardVO;

@Repository
@Mapper
public interface BoardMapper {

	@Select("select * from mvc_board2 order by bGroup desc, bStep asc")
	public List<BoardVO> getList();
	
	@Update("update mvc_board2 set bHit = bHit+1 where bId = #{bId}")
	public void boardCount(int bId);
	
	@Select("select * from (select rownum rnum, a.* from (select * from mvc_board2 order by bGroup desc, bStep asc) a where rownum <= #{pageNum} * #{amount})where rnum > (#{pageNum}-1) * #{amount}")
	public List<BoardVO> getListWithPaging(Criteria cri);

	@Select("select count(*) from mvc_board2")
	public int getTotalCount(Criteria cri);

	@Insert("insert into mvc_board2 (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board2_seq.nextval, #{bName}, #{bTitle}, #{bContent}, 0, mvc_board2_seq.currval, 0, 0)")
	public void write(BoardVO boardVO);
	
	@Insert("insert into mvc_board2 (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (mvc_board2_seq.nextval, #{bName}, #{bTitle}, #{bContent}, #{bGroup},  #{bStep}+1, #{bIndent}+1)")
	public void writeReply(BoardVO boardVO);
	
	@Update("update mvc_board2 set bStep = bStep + 1 where bGroup = #{bGroup} and bStep > #{bStep}")
	public void replyShape(BoardVO boardVO);

	@Update("update mvc_board2 set bHit = bHit+1 where bId = #{bId}")
	public void upHit(int bno);

	@Select("select * from mvc_board2 where bId = #{bId}")
	public BoardVO getContent(int bno);

	@Delete("delete from mvc_board2 where bId = #{bId}")
	public void delete(int bId);
	
	@Update("update mvc_board2 set bName = #{bName}, bTitle = #{bTitle}, bContent = #{bContent} where bId=#{bId}")
	public void modify(BoardVO boardVO);
	
}
