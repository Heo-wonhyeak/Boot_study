package hello.hellospring.controller.rest;

import hello.hellospring.Utils.FileDownloadUtil;
import hello.hellospring.Utils.FileUploadUtil;
import hello.hellospring.logic.HugoBoardLogic;
import hello.hellospring.req.model.board.*;
import hello.hellospring.res.model.ApiResultObjectDto;
import hello.hellospring.res.model.FileUploadResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @ApiOperation(value = "게시판 목록 가져오기")
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

    @GetMapping(value = "/detail/boardIdx/{boardIdx}/id/{id}")
    public ResponseEntity<ApiResultObjectDto> selectHugoBoard(@PathVariable("boardIdx") Long boardIdx,
                                                              @PathVariable("id") String id) {
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

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;

        try {
            // 파일 코드로 소스 가져오기
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("파일을 찾지 못했습니다", HttpStatus.NOT_FOUND);
        }

        // 8비트 데이터 형식의 자료
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    /*
        TODO
        질문 사항 - 리스트 가져오는것은 GetMapping 해야 하는것 아닌가요?
        어제 얘기한대로 reqModel 사용해서 파라미터를 리퀘스트 바디로 가져오면 GetMapping 에선 오류가 발생해서
        POST 로 바꿨습니다.

        REPLY
        @GetMapping 으로 리스트를 받아오는 형식은 파라미터를 받는 않는 순수 엔티티의 리스트를 받아올때 사용
        실질적으로 파라미터를 여러개를 사용해서 받아오는 API는 @PostMapping 을 사용
     */
    @PostMapping("/lists")
    public ApiResultObjectDto getPageBoardList(@RequestBody HugoBoardListReqModel reqModel) {
        log.info("getPageBoardListController - reqModel.getStartPage() : {}",reqModel.getStartPage());
        log.info("getPageBoardListController - reqModel.getListCount() : {}",reqModel.getListCount());
        return hugoBoardLogic.getHugoBoardLists(reqModel.getStartPage(), reqModel.getListCount());
    }

    @PostMapping("/pages")
    public ApiResultObjectDto getHugoBoardPage(@RequestBody HugoBoardPageReqModel reqModel) {
        return hugoBoardLogic.getHugoBoardPage(reqModel.getPage(), reqModel.getCountPage(), reqModel.getListCount());
    }

}
