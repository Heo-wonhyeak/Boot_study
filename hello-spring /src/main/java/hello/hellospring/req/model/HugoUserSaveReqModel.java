package hello.hellospring.req.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class HugoUserSaveReqModel {

    private String id;

    private String pwd;

    private String name;

    private String nickName;

    private String email;

    private Date birthDay;

    private String gender;

    private String callNum;

    private String interest;
}
