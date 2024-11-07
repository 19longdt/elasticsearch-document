package sds.easywrite.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A user.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "ew_user")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class User extends AbstractAuditingEntity<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Size(max = 100)
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "status")
    private Boolean status;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
