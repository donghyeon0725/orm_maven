package chanpterOne;

import entity.Member;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Objects;

// 영속성 컨텍스트 이점
public class Persist {
    public static void main(String[] args) {
//        run1();
//        run2();
//        run3();
//        run4();
//        run5();
//        run6();
        run7();
    }

    // 1차 캐시에서 조회
    public static void run1() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Member member = new Member();
        member.setId(7L);
        member.setName("name");

        entityManager.persist(member);

        Member findMember = entityManager.find(Member.class, member.getId());
        System.out.println("멤버 찾음");

        entityManager.flush();
        entityManager.clear();
        System.out.println("영속성 컨텍스트 비우고 다시 찾음");

        entityManager.find(Member.class, findMember.getId());
    }

    // 1차 캐시에서 조회 2
    public static void run2() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Member member = new Member();
        member.setId(10L);
        member.setName("name");
        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        Member findMember1 = entityManager.find(Member.class, 10L);
        Member findMember2 = entityManager.find(Member.class, 10L);

        transaction.commit();
    }

    static class Data {
        private String name;

        public Data(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return Objects.equals(name, data.name);
        }
    }

    // 동일성 vs 동등성
    public static void run3() {
        int int1 = 7;
        int int2 = 7;

        System.out.println("기초형 동등성 비교");
        System.out.println(int1 == int2);

        Data data1 = new Data("data");
        Data data2 = new Data("data");

        System.out.println(data1 == data2);
        System.out.println(data1.equals(data2));

        String string1 = "hello";
        String string2 = "hello";

        System.out.println("참조형 동등성");
        System.out.println(string1 == string2);
        System.out.println(string1.equals(string2));

        String string3 = new String("hello");

        System.out.println("동등한 변수");
        System.out.println(string3 == string1);
        System.out.println(string3.equals(string1));
    }

    // 동일성 vs 동등성
    public static void run4() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Member member = new Member();
        member.setId(11L);
        member.setName("name");

        entityManager.persist(member);

        Member findMember1 = entityManager.find(Member.class, 11L);
        Member findMember2 = entityManager.find(Member.class, 11L);

        System.out.println(findMember1 == findMember2);

        transaction.commit();
    }

    // 쓰기 지연
    public static void run5() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Member member1 = new Member();
        member1.setId(21L);
        member1.setName("member1");

        Member member2 = new Member();
        member2.setId(22L);
        member2.setName("member2");

        Member member3 = new Member();
        member3.setId(23L);
        member3.setName("member3");

        Member member4 = new Member();
        member4.setId(24L);
        member4.setName("member4");

        entityManager.persist(member1);
        entityManager.persist(member2);
        entityManager.persist(member3);
        entityManager.persist(member4);

        System.out.println("쿼리 나가나?");
        transaction.commit();
    }

    // dirty checking
    public static void run6() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Member member = new Member();
        member.setId(23L);
        member.setName("name");

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();
        System.out.println("플러시");

        Member findMember = entityManager.find(Member.class, member.getId());

        findMember.setName("change");

        transaction.commit();
    }

    // jpql
    public static void run7() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Member member = new Member();
        member.setId(25L);
        member.setName("name");

        entityManager.persist(member);

        System.out.println("jpql 실행 전");
        TypedQuery<Member> query = entityManager.createQuery("select m from Member m", Member.class);
        query.getResultList();
        System.out.println("jpql 실행 후 아직 커밋하기 전 ");

        transaction.commit();
    }

}
