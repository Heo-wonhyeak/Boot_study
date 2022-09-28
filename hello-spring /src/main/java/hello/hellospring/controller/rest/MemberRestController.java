package hello.hellospring.controller.rest;

import hello.hellospring.enums.ErrorCodeEnum;
import hello.hellospring.framework.exception.BaseException;
import hello.hellospring.logic.MemberLogic;
import hello.hellospring.req.model.HugoLoginReqModel;
import hello.hellospring.req.model.HugoUserDeleteReqModel;
import hello.hellospring.req.model.HugoUserSaveReqModel;
import hello.hellospring.req.model.TestReqModel;
import hello.hellospring.res.model.ApiResultObjectDto;
import hello.hellospring.res.model.TestResModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity<ApiResultObjectDto> loginMember(@Valid @RequestBody HugoLoginReqModel reqModel) {
        //ApiResultObjectDto apiResultObjectDto = memberLogic.loginByIdLogic(reqModel, request);
        ApiResultObjectDto apiResultObjectDto = memberLogic.loginLogic(reqModel);
        return ResponseEntity.ok(apiResultObjectDto);
    }

    /**
     * 회원정보 API
     * @param id
     * @return
     */
    @GetMapping(value = "/info/{id}")
    public ResponseEntity<ApiResultObjectDto> getMemberInfo(@RequestParam String id) {
        ApiResultObjectDto apiResultObjectDto = memberLogic.getHugoMemberInfo(id);
        return ResponseEntity.ok(apiResultObjectDto);
    }

    /**
     * 회원정보 수정 API
     * @param id
     * @param pwd
     * @return
     */
    @PostMapping(value = "/info/{id}")
    public ResponseEntity<ApiResultObjectDto> updateMemberInfo(@RequestParam String id, @RequestParam String pwd) {
        ApiResultObjectDto apiResultObjectDto = memberLogic.updateHugoMemberInfo(id, pwd);
        return ResponseEntity.ok(apiResultObjectDto);
    }

    /**
     * 회원 탈퇴 API
     * @param reqModel
     * @return
     */
    @PostMapping(value = "/resign")
    public ResponseEntity<ApiResultObjectDto> deleteMemberInfo(@RequestBody HugoUserDeleteReqModel reqModel) {
        ApiResultObjectDto apiResultObjectDto = memberLogic.deleteHugoUserInfo(reqModel);
        return ResponseEntity.ok(apiResultObjectDto);
    }

    /**
     * TODO 회원 비밀번호 검증 API
     * @param id
     * @param request
     * @return
     */


    @Deprecated
    @GetMapping(value = "/logout/{id}")
    public ResponseEntity<ApiResultObjectDto> logoutMember(@RequestParam String id ,HttpServletRequest request) {
        ApiResultObjectDto apiResultObjectDto = memberLogic.logoutByIdLogic(id, request);
        return ResponseEntity.ok(apiResultObjectDto);
    }
}
