package hellojpa;

import jdk.nashorn.internal.runtime.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try{
//            //등록 C
////            Member member = new Member();
////
////            member.setId(2L);
////            member.setName("HelloJPA2");
//
//            //조회 R
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//
//            // 전체 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(5)
//                    .getResultList();
//
//            //수정 U
//            findMember.setName("Hello~");
////            findMember.setId(2L);
//
//            //삭제 D
////            em.remove(findMember);
////            em.persist(member);
//
//            // 비영속
////            Member member = new Member();
////            member.setId(101L);
////            member.setName("HelloJPA1");
//
//            //영속
//            System.out.println("-------before---------");
////            em.persist(member); // 1차 캐시 저장
////            em.detach(member); //영속성 끊기
//            System.out.println("-------after---------");
//
//            //저장된 1차 캐시에서 가져옴
//            Member findMember2 = em.find(Member.class, 101L);
//            Member findMember3 = em.find(Member.class, 101L);
//
//            System.out.println("findMember2.getId() = " + findMember2.getId());
//            System.out.println("findMember2.getName() = " + findMember2.getName());
//            System.out.println("findMember3.getId() = " + findMember3.getId());
//            System.out.println("findMember3.getName( = " + findMember3.getName());

            //영속
            Member member = new Member(150L, "A");
            Member member2 = new Member(151L, "B");

            em.persist(member);
            em.persist(member2);
            System.out.println("=======================");
            
            //DB에 저장(영속성 컨택스트에 있는 내용 쿼리 전송)
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
