package hello.hellospring.controller.rest;

import hello.hellospring.Utils.FileUploadUtil;
import hello.hellospring.logic.HugoBoardLogic;
import hello.hellospring.req.model.board.*;
import hello.hellospring.res.model.ApiResultObjectDto;
import hello.hellospring.res.model.FileUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
        return ResponseEntity.ok(hugoBoardLogic.selectHugoBoard(boardIdx, id));
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<ApiResultObjectDto> deleteHugoBoard(@RequestBody HugoBoardDeleteReqModel reqModel) {
        return ResponseEntity.ok(hugoBoardLogic.deleteHugoBoard(reqModel));
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ApiResultObjectDto> updateHugoBoard(@RequestBody HugoUpdateBoardReqModel reqModel) {
        return ResponseEntity.ok(hugoBoardLogic.updateHugoBoard(reqModel));
    }

    @PostMapping("/like")
    public ResponseEntity<ApiResultObjectDto> likeHugoBoard(@RequestBody HugoBoardLikeReqModel reqModel) {
        return ResponseEntity.ok(hugoBoardLogic.likeHugoBoard(reqModel));
    }

    @PostMapping("/reply/write")
    public ResponseEntity<ApiResultObjectDto> writeHugoBoardReply(@RequestBody HugoBoardReplyReqModel reqModel) {
        return ResponseEntity.ok(hugoBoardLogic.writeHugoBoardReply(reqModel));
    }

    @PostMapping("/reply/update")
    public ResponseEntity<ApiResultObjectDto> updateHugoBoardReply(@RequestBody HugoBoardReplyUpdateReqModel reqModel) {
        return ResponseEntity.ok(hugoBoardLogic.updateHugoBoardReply(reqModel));
    }

    @PostMapping("/reply/delete")
    public ResponseEntity<ApiResultObjectDto> deleteHugoBoardReply(@RequestBody HugoBoardReplyDeleteReqModel reqModel) {
        return ResponseEntity.ok(hugoBoardLogic.deleteHugoBoardReply(reqModel));
    }

    @PostMapping("/reply/declaration")
    public ResponseEntity<ApiResultObjectDto> declarationReply(@RequestBody HugoBoardReplyDeclarationReqModel reqModel) {
        return ResponseEntity.ok(hugoBoardLogic.declarationReply(reqModel));
    }

    @PostMapping("/uploadFile")
    public  ResponseEntity<FileUploadResponse> uploadFiles(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/"+fileCode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
