package edu.wgu.activitytracker.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
