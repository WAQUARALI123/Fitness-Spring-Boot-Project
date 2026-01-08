package com.project.fitness.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;

    @Column(length = 2000)
    private String recommendation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id", nullable = false, foreignKey = @ForeignKey(name = "fk_recommendation"))
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false, foreignKey = @ForeignKey(name = "fk_activity"))
    private Activity activity;

    @Column(columnDefinition = "json")
    private List<String> improvements;

    @Column(columnDefinition = "json")
    private List<String> suggestions;

    @Column(columnDefinition = "json")
    private List<String> safety;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;

}
