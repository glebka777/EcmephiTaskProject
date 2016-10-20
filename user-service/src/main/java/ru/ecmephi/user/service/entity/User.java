package ru.ecmephi.user.service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(
                name = "User.getAllUsers()",
                query = "select u from User u"
        ),
        @NamedQuery(
                name = "User.getByUsername()",
                query = "select u from User u where u.username = :name"
        )
})
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "access_level", nullable = false)
    private ru.ecmephi.user.service.entity.AccessLevel accessLevel = ru.ecmephi.user.service.entity.AccessLevel.USER;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE},
            mappedBy = "user")
    @OrderBy("date")
    private Set<Record> log;

}
