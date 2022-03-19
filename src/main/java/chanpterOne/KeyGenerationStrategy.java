package chanpterOne;

import entity.IdentityKey;
import entity.SequenceKey;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 키 생성 전략
public class KeyGenerationStrategy {

    public static void main(String[] args) {
//        run1();
//        run2();
        run3();
    }

    // identity 키 생성 전략
    public static void run1() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        IdentityKey identityKey = new IdentityKey();

        entityManager.persist(identityKey);

        System.out.println("커밋 전");

        transaction.commit();
    }

    // sequence 키 생성 전략
    public static void run2() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        SequenceKey sequenceKey = new SequenceKey();

        entityManager.persist(sequenceKey);

        transaction.commit();
    }

    // sequence 키 생성 전략 성능최적화
    public static void run3() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        SequenceKey sequenceKey1 = new SequenceKey();
        SequenceKey sequenceKey2 = new SequenceKey();
        SequenceKey sequenceKey3 = new SequenceKey();

        entityManager.persist(sequenceKey1);
        entityManager.persist(sequenceKey2);
        entityManager.persist(sequenceKey3);

        transaction.commit();
    }
}
