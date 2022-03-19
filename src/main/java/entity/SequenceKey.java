package entity;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "SEQUENCE_KEY_GENERATOR", // jpa 내부적으로 generator 로 부여한 식별자 생성기 이름
        sequenceName = "SEQUENCE_SEQ", // 이름을 지정하지 않으면 기본 HIBERNATE_SEQUENCE
        initialValue = 1, allocationSize = 5
)
public class SequenceKey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_KEY_GENERATOR")
    private Long id;
}
