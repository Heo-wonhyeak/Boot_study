package hello.hellospring.mybatis.dao;

import hello.hellospring.mybatis.model.HugoUserInfoModel;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Options(useGeneratedKeys = true, keyProperty = "idx", keyColumn = "idx")
    void saveHugoUserInfo(HugoUserInfoModel hugoUserInfoModel);

    @Select("select * from hugo_user_info where id = #{id} and pwd= #{pwd}")
    int loginById(HugoUserInfoModel hugoUserInfoModel);

    @Select("select * from hugo_user_info where id = #{id}")
    HugoUserInfoModel findUserById(@Param("id") String id);

    @Select("select * from hugo_user_info where id = #{id}")
    HugoUserInfoModel findHugoMemberInfoById(@RequestParam("id") String id);
}
