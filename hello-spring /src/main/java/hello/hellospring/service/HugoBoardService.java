package hello.hellospring.service;

import hello.hellospring.mybatis.dao.HugoBoardDao;
import hello.hellospring.mybatis.model.HugoBoardLikeModel;
import hello.hellospring.mybatis.model.HugoBoardModel;
import hello.hellospring.mybatis.model.HugoBoardReplyModel;
import hello.hellospring.req.model.board.HugoUpdateBoardReqModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HugoBoardService {

    @Autowired
    private HugoBoardDao hugoBoardDao;

    /**
     * test 용
     * @return
     */
    public List<HugoBoardModel> findHugoBoardList() {
        return hugoBoardDao.getHugoBoardList();
    }

    /**
     * 게시판 글쓰기
     * @param hugoBoardModel
     */
    public void writeHugoBoard(HugoBoardModel hugoBoardModel) {
        // 제목이 입력되었을때 게시글 작성
        if(!"".equals(hugoBoardModel.getTitle())) {
            hugoBoardDao.writeHugoBoard(hugoBoardModel);
        }
    }

    /**
     * 게시판 상세보기
     * @param boardIdx
     * @return
     */
    public HugoBoardModel selectHugoBoard(Long boardIdx) {
        HugoBoardModel hugoBoardModel = hugoBoardDao.selectHugoBoard(boardIdx);
        return hugoBoardModel;
    }

    /**
     * 게시글 삭제
     * @param boardIdx
     */
    public void deleteHugoBoard(Long boardIdx) {
        hugoBoardDao.deleteHugoBoard(boardIdx);
    }

    /**
     * 게시글 업데이트
     * @param reqModel
     */
    public void updateHugoBoard(HugoUpdateBoardReqModel reqModel) {
        hugoBoardDao.updateHugoBoard(reqModel);
    }

    /**
     * 게시글 조회수 증가
     * @param boardIdx
     */
    public void updateVisitCount(Long boardIdx) {
        hugoBoardDao.updateVisitCount(boardIdx);
    }

    /**
     * 좋아요 테이블 만들기
     * @param hugoBoardLikeModel
     */
    public void insertLikeCountBoard(HugoBoardLikeModel hugoBoardLikeModel) {
        hugoBoardDao.insertLikeCountBoard(hugoBoardLikeModel);
    }

    /**
     * 좋아요시 테이블 상태변경
     * @param likeIdx
     */
    public void updateLikeCountBoard(Long likeIdx) {
        hugoBoardDao.updateLikeCountBoard(likeIdx);
    }

    /**
     * 좋아요 취소시 테이블 상태변경
     * @param likeIdx
     */
    public void updateDislikeCountBoard(Long likeIdx) {
        hugoBoardDao.updateDislikeCountBoard(likeIdx);
    }

    /**
     * 좋아요 수 증가
     * @param boardIdx
     */
    public void updateLikeCount(Long boardIdx) {
        hugoBoardDao.updateLikeCount(boardIdx);
    }

    /**
     * 좋아요 수 감소
     * @param boardIdx
     */
    public void updateDisLikeCount(Long boardIdx) {
        hugoBoardDao.updateDisLikeCount(boardIdx);
    }

    /**
     * id 와 boardIdx 로 좋아요 테이블 존재 여부 확인
     * @param id
     * @param boardIdx
     */
    public HugoBoardLikeModel selectHugoBoardLike(String id, Long boardIdx) {
        return hugoBoardDao.selectHugoBoardLike(id, boardIdx);
    }

    /**
     * 댓글 쓰기
     * @param hugoBoardReplyModel
     */
    public void writeHugoBoardReply(HugoBoardReplyModel hugoBoardReplyModel) {
        hugoBoardDao.writeHugoBoardReply(hugoBoardReplyModel);
    }
}
