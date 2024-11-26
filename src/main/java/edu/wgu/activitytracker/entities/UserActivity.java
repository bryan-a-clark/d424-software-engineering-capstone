package edu.wgu.activitytracker.entities;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Data
@NoArgsConstructor
public class UserActivity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="activity_id", nullable=false)
    private Activity activity;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    @Type(PostgreSQLIntervalType.class)
    @Column(insertable = false, updatable = false)
    private Duration duration;

    private double distance;

    @ManyToOne
    @JoinColumn(name="distance_unit_id", nullable=false)
    private DistanceUnit distanceUnit;

    private String note;

    @Builder
    public UserActivity(User user, Activity activity, LocalDateTime startDateTime, LocalDateTime endDateTime,
                        double distance, DistanceUnit distanceUnit, String note) {
        this.user = user;
        this.activity = activity;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.distance = distance;
        this.distanceUnit = distanceUnit;
        this.note = note;
    }
}
