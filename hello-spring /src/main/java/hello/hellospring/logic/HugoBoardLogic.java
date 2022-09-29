package hello.hellospring.logic;

import hello.hellospring.enums.ErrorCodeEnum;
import hello.hellospring.mybatis.dao.HugoBoardDao;
import hello.hellospring.mybatis.model.HugoBoardModel;
import hello.hellospring.req.model.board.HugoWriteBoardReqModel;
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
}
