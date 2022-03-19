package entity;

import javax.persistence.*;

@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(name = "MEMBER_NAME_UK", columnNames = {"name"})})
public class Member {
    @Id
    private Long id;
    private String name;

    private Type type; // kakao, google

    public Type getType() {
        return type;
    }

    public Member setType(Type type) {
        this.type = type;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Member setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
