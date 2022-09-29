package hello.hellospring.service;

import hello.hellospring.mybatis.dao.HugoBoardDao;
import hello.hellospring.mybatis.model.HugoBoardModel;
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
        HugoBoardModel hugoBoardModel = hugoBoardDao.selectBoard(boardIdx);
        return hugoBoardModel;
    }
}
