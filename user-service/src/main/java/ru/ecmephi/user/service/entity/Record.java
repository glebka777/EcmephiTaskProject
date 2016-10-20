package ru.ecmephi.user.service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_audit")
@NamedQueries({
        @NamedQuery(
                name = "Log.getAllEntriesOfOneType()",
                query = "select l from Record l where l.activity = :activity"
        )
})
@Getter
@Setter
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "activity_type", nullable = false)
    private Activity activity;

    @Column(name = "date", nullable = false)
    private Date date;

}
