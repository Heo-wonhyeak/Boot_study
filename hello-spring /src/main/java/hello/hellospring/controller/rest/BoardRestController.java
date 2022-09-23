package hello.hellospring.controller.rest;

import hello.hellospring.logic.HugoBoardLogic;
import hello.hellospring.res.model.ApiResultObjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
