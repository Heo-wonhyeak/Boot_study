package hello.hellospring.mybatis.dao;

import hello.hellospring.mybatis.model.HugoBoardModel;
import hello.hellospring.req.model.board.HugoUpdateBoardReqModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HugoBoardDao {

    /**
     * 단순 리스트 가져오기 - 초기 테스트용
     *
     * @return
     */
    @Select("SELECT * FROM HUGO_BOARD")
    List<HugoBoardModel> getHugoBoardList();

    /**
     * 게시판 글쓰기
     *
     * @param hugoBoardModel
     */
    @Insert("insert into HUGO_BOARD (title,content,event_period,id,ofile,write_header,boarder) \n" +
            "values (#{title } , #{content , jdbcType=VARCHAR}, #{eventPeriod , jdbcType=VARCHAR}, " +
            "#{id } ,#{oFile , jdbcType=VARCHAR},#{writeHeader,jdbcType=VARCHAR},#{boarder})")
    void writeHugoBoard(HugoBoardModel hugoBoardModel);

    /**
     * 게시글 상세보기
     *
     * @param boardIdx
     * @return
     */
    @Select("select * from HUGO_BOARD where board_idx = #{boardIdx}")
    HugoBoardModel selectHugoBoard(Long boardIdx);

    /**
     * 게시글 삭제
     *
     * @param boardIdx
     */
    @Delete("delete from HUGO_BOARD where board_idx = #{boardIdx}")
    void deleteHugoBoard(Long boardIdx);

    @Update("update hugo_board " +
            "set " +
            "title = #{title }, " +
            "content = #{content , jdbcType=VARCHAR}, " +
            "event_period = #{eventPeriod , jdbcType=VARCHAR}, " +
            "ofile = #{oFile , jdbcType=VARCHAR}, " +
            "write_header = #{writeHeader,jdbcType=VARCHAR}, " +
            "boarder = #{boarder} " +
            " where board_idx = #{boardIdx}")
    void updateHugoBoard(HugoUpdateBoardReqModel reqModel);
}
