package hello.hellospring.mybatis.model;

import lombok.Data;

import java.util.Date;

@Data
public class HugoBoardModel {

    private int boardIdx;

    private String title;

    private String content;

    private String eventPeriod;

    private String id;

    private String oFile;

    private int likeCount;

    private int visitCount;

    private Date createDate;

    private String writeHeader;

    private String boarder;
}
