package hello.hellospring.service;

import hello.hellospring.mybatis.dao.HugoUserInfoDao;
import hello.hellospring.mybatis.model.HugoUserInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private HugoUserInfoDao hugoUserInfoDao;

    /**
     * getHugoUserInfoList 가져오는 서비스
     * @return
     */
    public List<HugoUserInfoModel> findHugoUserList() {
        return hugoUserInfoDao.getHugoUserInfoList();
    }

    public Integer findCountHugoUserById(String id) {
        if (!"".equals(id)) {
            return hugoUserInfoDao.getCountHugoUserInfoById(id);
        }
        return 1;
    }

    @Transactional
    public void saveHugoUserInfo(HugoUserInfoModel hugoUserInfoModel) {
        if (!"".equals(hugoUserInfoModel.getId())) {
            hugoUserInfoDao.saveHugoUserInfo(hugoUserInfoModel);
        }
    }
}
