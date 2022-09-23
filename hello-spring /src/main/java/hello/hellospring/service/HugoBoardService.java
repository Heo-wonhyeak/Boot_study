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
     * HugoBoardList 가져오는 Service
     * @return
     */
    public List<HugoBoardModel> findHugoBoardList() {
        return hugoBoardDao.getHugoBoardList();
    }
}
