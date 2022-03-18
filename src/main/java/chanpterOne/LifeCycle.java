package chanpterOne;

import entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// jpa 생명주기
public class LifeCycle {
    public static void main(String[] args) {
        run3();
    }

    // flush 이후 쿼리가 나가는 것을 확인할 수 있다.
    public static void run1() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Member member = new Member();

        member.setId(3L);
        member.setName("name");

        entityManager.persist(member);

        System.out.println("DB 에 값이 저장되나?");

        entityManager.flush();

        transaction.commit();
    }

    // 영속성 컨텍스트에서 엔티티 분리, 준영속 상태로 영속성 관리를 받지 않음
    public static void run2() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Member member = new Member();
        member.setId(6L);
        member.setName("name");

        System.out.println("저장");
        entityManager.persist(member);
        entityManager.flush();

        System.out.println("업데이트1");
        member.setName("change");
        entityManager.flush();

        System.out.println("업데이트2");
        entityManager.detach(member);
        member.setName("newChange");
        entityManager.flush();

        transaction.commit();
    }

    // 영속성 컨텍스트에서 값 삭제
    public static void run3() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Member member = new Member();
        member.setId(7L);
        member.setName("name");

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();


        Member findMember = entityManager.find(Member.class, member.getId());
        entityManager.remove(findMember);

        transaction.commit();
    }
}
