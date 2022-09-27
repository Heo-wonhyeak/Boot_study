package hello.hellospring.logic;

import hello.hellospring.enums.ErrorCodeEnum;
import hello.hellospring.framework.exception.BaseException;
import hello.hellospring.mybatis.model.HugoUserInfoModel;
import hello.hellospring.req.model.HugoLoginReqModel;
import hello.hellospring.req.model.HugoUserSaveReqModel;
import hello.hellospring.res.model.ApiResultObjectDto;
import hello.hellospring.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class MemberLogic {

    @Autowired
    private MemberService memberService;

    /**
     * 회원 전체를 가져오는 로직
     * @return
     */
    public ApiResultObjectDto getAllHugoMemberLogic() {
        int resultCode = 200;

        //사용자 리스트
        List<HugoUserInfoModel> userInfoModelList = memberService.findHugoUserList();
        // 리스트가 없으면 예외처리
        if (userInfoModelList.size() == 0 || userInfoModelList.isEmpty()) {
            //throw new BaseException(ErrorCodeEnum.CUSTOM_EMPTY_MEMBER_LIST.msg(), ErrorCodeEnum.CUSTOM_EMPTY_MEMBER_LIST);
            resultCode = ErrorCodeEnum.CUSTOM_EMPTY_MEMBER_LIST.code();
        } else {
            throw new BaseException(ErrorCodeEnum.CUSTOM_EMPTY_MEMBER_LIST.msg(), ErrorCodeEnum.CUSTOM_EMPTY_MEMBER_LIST);
        }
        return ApiResultObjectDto.builder()
                .resultCode(resultCode)
                .result(userInfoModelList)
                .build();
    }

    /**
     * 회원가입 비지니스 로직
     * @param reqModel
     * @return
     */
    @Transactional
    public ApiResultObjectDto saveHugoUserLogic(HugoUserSaveReqModel reqModel) {
        //결과값 선언 ( http ok code )
        int resultCode = HttpStatus.OK.value();
        //리턴해줄 object map 선언
        Map<String, String>resultMap = new HashMap<>();

        //회원 아이디가 비웠으면 551번 에러처리
        if ( "".equals(reqModel.getId()) ) {
            resultCode = 551;
        } else {
            //회원 아이디가 있으면 회원정보 저장하기 시작

            //존재하는 아이디가 있는지 개수 확인
            int userCount = memberService.findCountHugoUserById(reqModel.getId());

            //아이디가 존재하면 에러 코드 값 주입
            if (userCount > 0) {
                resultCode = 552;
                log.error("이미 존재하는 아이디 입니다. ID :: " + reqModel.getId());
            }
            //아이디가 존재하지 않으므로 저장 로직 사직
            if (userCount == 0) {
                HugoUserInfoModel hugoUserInfoModel = new HugoUserInfoModel();
                hugoUserInfoModel.setId(reqModel.getId());
                hugoUserInfoModel.setPwd(reqModel.getPwd());
                hugoUserInfoModel.setName(reqModel.getName());
                hugoUserInfoModel.setNickName(reqModel.getNickName());
                hugoUserInfoModel.setEmail(reqModel.getEmail());
                hugoUserInfoModel.setBirthDay(reqModel.getBirthDay());
                hugoUserInfoModel.setGender(reqModel.getGender());
                hugoUserInfoModel.setCallNum(reqModel.getCallNum());
                hugoUserInfoModel.setInterest(reqModel.getInterest());

                try {
                    //memberService.saveHugoUserInfo(hugoUserInfoModel);
                    memberService.saveHugoUserInfo(hugoUserInfoModel);
                    log.error("key >> " + hugoUserInfoModel.getIdx());
                } catch (Exception e) {
                    log.error("error >> "+ e.toString());
                }
                resultMap.put("savedUserId", reqModel.getId());
            }
        }
        return ApiResultObjectDto.builder()
                .resultCode(resultCode)
                .result(resultMap)
                .build();
    }

    @Transactional
    public ApiResultObjectDto loginByIdLogic(HugoLoginReqModel reqModel , HttpServletRequest request) {
        //결과값 선언 ( http ok code )
        int resultCode = HttpStatus.OK.value();
        //리턴해줄 object map 선언
        Map<String, String>resultMap = new HashMap<>();

        // reqModel 에 요청된 id 로 HugoUserInfoModel 객체를 reqUser 로불러옴
        HugoUserInfoModel reqUser = memberService.findHugoUserById(reqModel.getId());

        // reqUser 요청 유저가 있으면
        if(reqUser != null) {
            // 요청유저에 저장된 Pwd 와 요청 모델의 pwd 가 같으면 로그인 성공!
            if(reqUser.getPwd().equals(reqModel.getPwd())) {
                HttpSession session = request.getSession();
                // 세션에 요청 유저 정보 저장
                session.setAttribute("loginMember",reqUser);
                // 세션에 로그인 여부 저장
                session.setAttribute("isLogOn",true);
                // hashMap 에 nickName 저장 - 로그인 결과 뿌려줄때 사용
                resultMap.put("nickName", reqUser.getNickName());
            }
        } else {
            // 요청 유저가 없다면
            resultCode = 510;
            log.error("요청한 정보가 없습니다 id 혹은 패스워드를 확인하세요 Id : "+reqModel.getId());
        }

        return ApiResultObjectDto.builder()
                .resultCode(resultCode)
                .result(resultMap)
                .build();
    }
}
