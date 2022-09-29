package hello.hellospring.mybatis.dao;

import hello.hellospring.mybatis.model.HugoBoardModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HugoBoardDao {

    /**
     * 단순 리스트 가져오기 - 초기 테스트용
     * @return
     */
    @Select("SELECT * FROM HUGO_BOARD")
    List<HugoBoardModel> getHugoBoardList();

    /**
     * 게시판 글쓰기
     * @param hugoBoardModel
     */
    @Insert("insert into HUGO_BOARD (title,content,event_period,id,ofile,write_header,boarder) \n" +
            "values (#{title } , #{content , jdbcType=VARCHAR}, #{eventPeriod , jdbcType=VARCHAR}, " +
            "#{id } ,#{oFile , jdbcType=VARCHAR},#{writeHeader,jdbcType=VARCHAR},#{boarder})")
    void writeHugoBoard(HugoBoardModel hugoBoardModel);

    /**
     * 게시판 상세보기
     * @param boardIdx
     * @return
     */
    @Select("select * from HUGO_BOARD where board_idx = #{boardIdx}")
    HugoBoardModel selectBoard(Long boardIdx);
}
