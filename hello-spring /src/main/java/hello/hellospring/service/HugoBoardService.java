package hello.hellospring.service;

import hello.hellospring.mybatis.dao.HugoBoardDao;
import hello.hellospring.mybatis.model.HugoBoardModel;
import hello.hellospring.req.model.board.HugoUpdateBoardReqModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
