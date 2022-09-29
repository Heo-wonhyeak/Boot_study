package hello.hellospring.controller.rest;

import hello.hellospring.logic.HugoBoardLogic;
import hello.hellospring.req.model.board.HugoBoardLikeReqModel;
import hello.hellospring.req.model.board.HugoSelectBoardReqModel;
import hello.hellospring.req.model.board.HugoUpdateBoardReqModel;
import hello.hellospring.req.model.board.HugoWriteBoardReqModel;
import hello.hellospring.res.model.ApiResultObjectDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "*")
@RequestMapping(value = "/hugo/board")
@RestController
public class BoardRestController {

    @Autowired
    HugoBoardLogic hugoBoardLogic;

    @GetMapping(value = "/list/all")
    public ResponseEntity<ApiResultObjectDto> getAllBoardList() {
        ApiResultObjectDto apiResultObjectDto = hugoBoardLogic.getAllHugoBoardLogic();
        return ResponseEntity.ok(apiResultObjectDto);
    }

    @PostMapping(value = "/write")
    public ResponseEntity<ApiResultObjectDto> writeHugoBoard(@RequestBody HugoWriteBoardReqModel reqModel) {
        ApiResultObjectDto apiResultObjectDto = hugoBoardLogic.writeHugoBoardLogic(reqModel);
        return ResponseEntity.ok(apiResultObjectDto);
    }

    @GetMapping(value = "/detail/{boardIdx}/{id}")
    public ResponseEntity<ApiResultObjectDto> selectHugoBoard(@RequestParam Long boardIdx,
                                                              @RequestParam String id) {
        return ResponseEntity.ok(hugoBoardLogic.selectHugoBoard(boardIdx,id));
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<ApiResultObjectDto> deleteHugoBoard(@RequestParam Long boardIdx) {
        return ResponseEntity.ok(hugoBoardLogic.deleteHugoBoard(boardIdx));
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ApiResultObjectDto> updateHugoBoard(@RequestBody HugoUpdateBoardReqModel reqModel) {
        return ResponseEntity.ok(hugoBoardLogic.updateHugoBoard(reqModel));
    }

    @PostMapping("/like")
    public ResponseEntity<ApiResultObjectDto> likeHugoBoard(@RequestBody HugoBoardLikeReqModel reqModel) {
        return ResponseEntity.ok(hugoBoardLogic.likeHugoBoard(reqModel));
    }
}
