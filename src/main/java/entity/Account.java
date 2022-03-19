package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "ACCOUNT_NAME_UK", columnNames = {"name"})})
public class Account {
    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Enumerated(EnumType.ORDINAL)
//    @Enumerated(EnumType.STRING)
    private Type type; // kakao, google

//    @Temporal(TemporalType.DATE)
//    @Temporal(TemporalType.TIME)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private String onlyJavaField;


//    @Column(insertable = false, updatable = false)
//    private String test;


    public Account() {
    }

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Account setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Account setAge(Integer age) {
        this.age = age;
        return this;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public Account setRoleType(RoleType roleType) {
        this.roleType = roleType;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Account setType(Type type) {
        this.type = type;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Account setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Account setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Account setDescription(String description) {
        this.description = description;
        return this;
    }
}
