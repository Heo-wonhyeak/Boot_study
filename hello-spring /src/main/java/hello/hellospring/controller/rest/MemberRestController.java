package hello.hellospring.controller.rest;

import hello.hellospring.enums.ErrorCodeEnum;
import hello.hellospring.framework.exception.BaseException;
import hello.hellospring.logic.MemberLogic;
import hello.hellospring.req.model.HugoLoginReqModel;
import hello.hellospring.req.model.HugoUserSaveReqModel;
import hello.hellospring.req.model.TestReqModel;
import hello.hellospring.res.model.ApiResultObjectDto;
import hello.hellospring.res.model.TestResModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "*")
@RequestMapping(value = "/api/member")
@RestController
public class MemberRestController {

    @Autowired
    private MemberLogic memberLogic;

    @GetMapping(value = "/list/all")
    public ResponseEntity<ApiResultObjectDto> getAllMemberList() {
        ApiResultObjectDto apiResultObjectDto = memberLogic.getAllHugoMemberLogic();
        return ResponseEntity.ok(apiResultObjectDto);
    }

    /**
     * 회원가입 API
     * @param reqModel
     * @return
     */
    @PostMapping(value = "/sign-up")
    public ResponseEntity<ApiResultObjectDto> saveMember(@Valid @RequestBody HugoUserSaveReqModel reqModel) {
        ApiResultObjectDto apiResultObjectDto = memberLogic.saveHugoUserLogic(reqModel);
        return ResponseEntity.ok(apiResultObjectDto);
    }

    /**
     * 로그인 API
     * @param reqModel
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity<ApiResultObjectDto> loginMember(@Valid @RequestBody HugoLoginReqModel reqModel, HttpServletRequest request) {
        ApiResultObjectDto apiResultObjectDto = memberLogic.loginByIdLogic(reqModel, request);
        return ResponseEntity.ok(apiResultObjectDto);
    }

    @GetMapping(value = "/info/{id}")
    public ResponseEntity<ApiResultObjectDto> getMemberInfo(@Valid @RequestParam String id) {
        ApiResultObjectDto apiResultObjectDto = memberLogic.getHugoMemberInfo(id);
        return ResponseEntity.ok(apiResultObjectDto);
    }
}
