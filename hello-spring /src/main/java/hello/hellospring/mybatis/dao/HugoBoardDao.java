package hello.hellospring.mybatis.dao;

import hello.hellospring.mybatis.model.HugoBoardModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HugoBoardDao {

    @Select("SELECT * FROM HUGO_BOARD")
    List<HugoBoardModel> getHugoBoardList();
}
