package hello.hellospring.logic;

import hello.hellospring.enums.ErrorCodeEnum;
import hello.hellospring.mybatis.dao.HugoBoardDao;
import hello.hellospring.mybatis.model.HugoBoardLikeModel;
import hello.hellospring.mybatis.model.HugoBoardModel;
import hello.hellospring.mybatis.model.HugoBoardReplyModel;
import hello.hellospring.req.model.board.*;
import hello.hellospring.res.model.ApiResultObjectDto;
import hello.hellospring.service.HugoBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class HugoBoardLogic {

    @Autowired
    private HugoBoardService hugoBoardService;

    /**
     * Board 전체를 가져오는 로직
     * @return
     */
    public ApiResultObjectDto getAllHugoBoardLogic() {
        int resultCode = 200;
        // Board List
        List<HugoBoardModel> hugoBoardList = hugoBoardService.findHugoBoardList();
        // 리스트가 없다면 예외처리
        if(hugoBoardList.size() == 0 || hugoBoardList.isEmpty()) {
            resultCode = 550;
        }

        return ApiResultObjectDto.builder().
                resultCode(resultCode).result(hugoBoardList).build();
    }

    /**
     * 게시판 글쓰기 로직
     * @param reqModel
     * @return
     */
    @Transactional
    public ApiResultObjectDto writeHugoBoardLogic(HugoWriteBoardReqModel reqModel) {
        // 결과 코드 기본 ok
        int resultCode = HttpStatus.OK.value();
        // 결과 선언해줄 resultMap 선언
        Map<String, Object> resultMap = new HashMap<>();

        // 제목이 비었다면 에러처리
        if ("".equals(reqModel.getTitle())) {
            resultCode = ErrorCodeEnum.CUSTOM_ERROR_NOT_TITLE.code();
            log.error("제목이 입력되지 않았습니다");
        } else {
            // 제목이 있다면 입력 시작

            HugoBoardModel hugoBoardModel = new HugoBoardModel();
            hugoBoardModel.setTitle(reqModel.getTitle());
            hugoBoardModel.setContent(reqModel.getContent());
            hugoBoardModel.setEventPeriod(reqModel.getEventPeriod());
            hugoBoardModel.setId(reqModel.getId());
            hugoBoardModel.setOFile(reqModel.getOFile());
            hugoBoardModel.setWriteHeader(reqModel.getWriteHeader());
            hugoBoardModel.setBoarder(reqModel.getBoarder());

            try {
                hugoBoardService.writeHugoBoard(hugoBoardModel);
                // 해당값이 null 이 들어옴.. hugoBoardService.writeHugoBoard(hugoBoardModel); 해당 메서드 생성할때 Idx 값이 생성되는거 아닌지..
                log.debug("key >> {}",hugoBoardModel.getBoardIdx());
            }catch (Exception e) {
                log.error("error >> {}",e.toString());
            }
            // 당연히 여기도 null
            resultMap.put("boardIdx", hugoBoardModel.getBoardIdx());
        }

        return ApiResultObjectDto.builder()
                .resultCode(resultCode)
                .result(resultMap)
                .build();
    }

    public ApiResultObjectDto selectHugoBoard(Long boardIdx , String id) {
        // 결과 코드 기본 ok
        int resultCode = HttpStatus.OK.value();
        // 결과 선언해줄 resultMap 선언
        Map<String, Object> resultMap = new HashMap<>();

        if(boardIdx == null) {
            resultCode = ErrorCodeEnum.CUSTOM_ERROR_NULL_BOARD_IDX.code();
            log.error("boardIdx 값을 입력해주세요");
        }
        HugoBoardModel hugoBoardModel = hugoBoardService.selectHugoBoard(boardIdx);

        // 작성자와 조회하는 사람의 아이디가 같다면
        if(hugoBoardModel.getId().equals(id)) {
            log.debug("게시글 작성자는 조회수가 증가하지 않습니다");
        } else {
            hugoBoardService.updateVisitCount(boardIdx);
            log.debug("조회수 1 증가");
        }
        resultMap.put("hugoBoardModel", hugoBoardModel);

        return ApiResultObjectDto.builder()
                .resultCode(resultCode)
                .result(resultMap)
                .build();
    }

    public ApiResultObjectDto deleteHugoBoard(Long boardIdx) {
        // 결과 코드 기본 ok
        int resultCode = HttpStatus.OK.value();
        // 결과 선언해줄 resultMap 선언
        Map<String, Object> resultMap = new HashMap<>();

        HugoBoardModel hugoBoardModel = hugoBoardService.selectHugoBoard(boardIdx);
        if(hugoBoardModel == null) {
            resultCode = ErrorCodeEnum.CUSTOM_ERROR_NULL_BOARD_DETAIL.code();
            log.error("해당 번호의 게시글은 존재하지 않습니다");
        } else {
            hugoBoardService.deleteHugoBoard(boardIdx);
            resultMap.put("boardIdx", boardIdx);
        }

        return ApiResultObjectDto.builder()
                .resultCode(resultCode)
                .result(resultMap)
                .build();
    }

    public ApiResultObjectDto updateHugoBoard(HugoUpdateBoardReqModel reqModel) {
        // 결과 코드 기본 ok
        int resultCode = HttpStatus.OK.value();
        // 결과 선언해줄 resultMap 선언
        Map<String, Object> resultMap = new HashMap<>();

        HugoBoardModel hugoBoardModel = hugoBoardService.selectHugoBoard(reqModel.getBoardIdx());
        if(hugoBoardModel == null) {
            resultCode = ErrorCodeEnum.CUSTOM_ERROR_NULL_BOARD_DETAIL.code();
            log.error("해당 번호의 게시글은 존재하지 않습니다");
        } else {
            if("".equals(reqModel.getTitle())){
                resultCode = ErrorCodeEnum.CUSTOM_ERROR_NOT_TITLE.code();
                log.error("제목이 입력되지 않았습니다");
            } else {
                hugoBoardService.updateHugoBoard(reqModel);
                resultMap.put("updateBoard",reqModel);
            }
        }
        return ApiResultObjectDto.builder()
                .resultCode(resultCode)
                .result(resultMap)
                .build();
    }

    public ApiResultObjectDto likeHugoBoard(HugoBoardLikeReqModel reqModel) {
        // 결과 코드 기본 ok
        int resultCode = HttpStatus.OK.value();
        // 결과 선언해줄 resultMap 선언
        Map<String, Object> resultMap = new HashMap<>();

        log.error("id = {}", reqModel.getId());

        if ("".equals(reqModel.getId())) {
            resultCode = ErrorCodeEnum.CUSTOM_ERROR_NOT_LOGIN.code();
            log.error("로그인 되어있지 않습니다");
        }

        if ("".equals(reqModel.getBoardIdx())) {
            resultCode = ErrorCodeEnum.CUSTOM_ERROR_NULL_BOARD_IDX.code();
            log.error("게시판 번호를 입력해주세요");
        }

        HugoBoardModel hugoBoardModel = hugoBoardService.selectHugoBoard(reqModel.getBoardIdx());

        if(hugoBoardModel == null) {
            resultCode = ErrorCodeEnum.CUSTOM_ERROR_NULL_BOARD_DETAIL.code();
            log.error("존재하지 않는 게시글");
        } else {
            // 좋아요 테이블 여부확인
            HugoBoardLikeModel likeTable = hugoBoardService.selectHugoBoardLike(reqModel.getId(), reqModel.getBoardIdx());

            // 좋아요 테이블이 없다면 만들기
            if (likeTable == null) {
                HugoBoardLikeModel hugoBoardLikeModel = new HugoBoardLikeModel();
                hugoBoardLikeModel.setBoardIdx(reqModel.getBoardIdx());
                hugoBoardLikeModel.setId(reqModel.getId());

                //좋아요 테이블 만들기
                hugoBoardService.insertLikeCountBoard(hugoBoardLikeModel);

                likeTable = hugoBoardService.selectHugoBoardLike(reqModel.getId(), reqModel.getBoardIdx());
            }

            // getLikeYN 이 0 이라면 좋아요 되어있지 않은상태
            if (likeTable.getLikeYN() == 0) {
                // LikeYN 1 로 변경
                hugoBoardService.updateLikeCountBoard(likeTable.getLikeIdx());
                // 좋아요 숫자 증가
                hugoBoardService.updateLikeCount(likeTable.getBoardIdx());
                resultMap.put("isLiked", true);
            } else {
                // LikeYN 0으로 변경
                hugoBoardService.updateDislikeCountBoard(likeTable.getLikeIdx());
                // 좋아요 숫자 감소
                hugoBoardService.updateDisLikeCount(likeTable.getBoardIdx());
                resultMap.put("isLiked", false);
            }
        }
        return ApiResultObjectDto.builder()
                .resultCode(resultCode)
                .result(resultMap)
                .build();
    }

    public ApiResultObjectDto writeHugoBoardReply(HugoBoardReplyReqModel reqModel) {
        // 결과 코드 기본 ok
        int resultCode = HttpStatus.OK.value();
        // 결과 선언해줄 resultMap 선언
        Map<String, Object> resultMap = new HashMap<>();

        HugoBoardModel getHugoBoard = hugoBoardService.selectHugoBoard(reqModel.getBoardIdx());

        // 게시글 번호로 조회한 게시글이 없다면 게시글 존재 x 에러
        if (getHugoBoard == null) {
            resultCode = ErrorCodeEnum.CUSTOM_ERROR_NULL_BOARD_DETAIL.code();
            log.error("게시글이 존재하지 않습니다");
        } else {
            // 게시글이 존재한다면 댓글 작성
            HugoBoardReplyModel hugoBoardReplyModel = new HugoBoardReplyModel();
            hugoBoardReplyModel.setBoardIdx(reqModel.getBoardIdx());
            hugoBoardReplyModel.setContent(reqModel.getContent());
            hugoBoardReplyModel.setNickName(reqModel.getNickName());

            hugoBoardService.writeHugoBoardReply(hugoBoardReplyModel);
            resultMap.put("HugoBoardReplyModel", hugoBoardReplyModel);
        }

        return ApiResultObjectDto.builder()
                .resultCode(resultCode)
                .result(resultMap)
                .build();
    }
}
