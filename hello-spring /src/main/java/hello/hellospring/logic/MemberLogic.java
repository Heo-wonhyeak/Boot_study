package hello.hellospring.logic;

import hello.hellospring.mybatis.model.HugoUserInfoModel;
import hello.hellospring.req.model.HugoUserSaveReqModel;
import hello.hellospring.res.model.ApiResultObjectDto;
import hello.hellospring.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

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
            resultCode = 550;
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
}
