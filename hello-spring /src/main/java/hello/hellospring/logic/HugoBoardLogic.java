package hello.hellospring.logic;

import hello.hellospring.mybatis.dao.HugoBoardDao;
import hello.hellospring.mybatis.model.HugoBoardModel;
import hello.hellospring.res.model.ApiResultObjectDto;
import hello.hellospring.service.HugoBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
