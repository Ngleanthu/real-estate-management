package com.javaweb.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    private static final long serialVersionUID = -4988455421375043688L;
    @Column(name = "username", nullable = false, unique = true)
    private String userName;
    @Column(name = "fullname", nullable = false)
    private String fullName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "status", nullable = false)
    private Integer status;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email", unique = true)
    private String email;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() { return phone; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "staff" , fetch = FetchType.LAZY)
    private List<AssignmentBuildingEntity> buildings = new ArrayList<>();

    public List<AssignmentBuildingEntity> getBuildings() {
        return buildings;
    }
    public void setBuildings(List<AssignmentBuildingEntity> buildings) {
        this.buildings = buildings;
    }

}

