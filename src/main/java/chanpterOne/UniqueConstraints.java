package chanpterOne;

import entity.Account;
import entity.RoleType;
import entity.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

// 각종 매핑
public class UniqueConstraints {


    public static void main(String[] args) {
//        run1();
        run2();


//        System.out.println(Type.KAKAO.ordinal());
//        System.out.println(Type.GOOGLE.ordinal());
    }

    // unique 제약 조건
    public static void run1() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();


        Account account1 = new Account();
        account1.setId(30L);
        account1.setUsername("민수");

        Account account2 = new Account();
        account2.setId(31L);
        account2.setUsername("민수");

        entityManager.persist(account1);
        entityManager.persist(account2);

        transaction.commit();
    }

    // 각종 매핑
    public static void run2() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Account account = new Account();

        account.setId(32L);
        account.setUsername("name");
        account.setAge(20);
        account.setRoleType(RoleType.ADMIN);
        account.setCreatedDate(new Date());
        account.setType(Type.KAKAO);

        entityManager.persist(account);

        transaction.commit();
    }

}
