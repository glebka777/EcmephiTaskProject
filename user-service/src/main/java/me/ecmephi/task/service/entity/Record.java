package me.ecmephi.task.service.entity;

import lombok.*;

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
@RequiredArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    @Column(name = "description", nullable = false)
    @NonNull
    private String description;

    @Column(name = "activity_type", nullable = false)
    @NonNull
    private Activity activity;

    @Column(name = "date", nullable = false)
    @NonNull
    private Date date;

}
