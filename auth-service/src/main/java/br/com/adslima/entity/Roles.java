package br.com.adslima.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles",
    uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "role_name"}))
public class Roles implements Serializable {

  private static final long serialVersionUID = -3090658385114905175L;

  @Id
  @Column(name = "role_id", nullable = false, unique = true, columnDefinition = "smallint unsigned")
  private Integer roleId;

  @Column(name = "role_name", nullable = false, unique = true)
  private String roleName;

  @JsonIgnore
  @ManyToMany(cascade = {CascadeType.MERGE}, mappedBy = "roles")
  private Set<Users> users;

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Set<Users> getUsers() {
    return users;
  }

  public void setUsers(Set<Users> users) {
    this.users = users;
  }

}
