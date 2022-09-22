package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.mybatis.dao.HugoUserInfoDao;
import hello.hellospring.mybatis.model.HugoUserInfoModel;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
}
