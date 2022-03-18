import entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Run {
    public static void main(String[] args) {
//        Run.run1();
//        Run.run2();
//        Run.run3();
//        Run.jpql1();
        Run.jpql2();
    }

    // jpa 는 트랜잭션 없이 동작하지 않는다.
    public static void run1() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
        entityManager.persist(member);

        entityManager.close();
        entityManagerFactory.close();
    }

    // jpa 는 트랜잭션이 있어야 동작한다.
    public static void run2() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
        entityManager.persist(member);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    // 정석적인 코드
    public static void run3() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        try {
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            entityManager.persist(member);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }

    // jpql 예제 코드
    public static void jpql1() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Member> memberList = entityManager.createQuery("select m from Member as m", Member.class).getResultList();

        for (Member member : memberList) {
            System.out.println("name : " + member.getName());
        }

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void jpql2() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Member> memberList = entityManager.createQuery("select m from Member as m", Member.class)
                .setFirstResult(0)
                .setMaxResults(1)
                .getResultList();

        for (Member member : memberList) {
            System.out.println("name : " + member.getName());
        }

        entityManager.close();
        entityManagerFactory.close();
    }

}
