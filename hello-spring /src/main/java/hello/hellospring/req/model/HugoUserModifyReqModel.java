package hello.hellospring.req.model;

import lombok.Data;

import java.util.Date;

@Data
public class HugoUserModifyReqModel {

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
