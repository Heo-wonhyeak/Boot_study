package hello.hellospring.mybatis.dao;

import hello.hellospring.mybatis.model.HugoUserInfoModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HugoUserInfoDao {

    @Select("SELECT * FROM HUGO_USER_INFO")
    List<HugoUserInfoModel> getHugoUserInfoList();

    @Select("SELECT count(*) FROM HUGO_USER_INFO WHERE id = #{id}")
    int getCountHugoUserInfoById(@Param("id") String id);

    @Insert("INSERT INTO HUGO_USER_INFO " +
            " (id, pwd, name, nick_name, email, birth_day, gender, call_num, interest) " +
            " VALUES " +
            "( #{id}, #{pwd}, #{name}, #{nickName}, #{email}, #{birthDay}, #{gender}, #{callNum}, #{interest} )" )
    void saveHugoUserInfo(HugoUserInfoModel hugoUserInfoModel);

}
