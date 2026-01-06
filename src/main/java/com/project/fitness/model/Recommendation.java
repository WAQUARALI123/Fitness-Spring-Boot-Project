package com.project.fitness.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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



    @JdbcTypeCode(SqlTypes.JSON  )
    @Column(columnDefinition = "json")
    private List<String> improvements;

    @JdbcTypeCode(SqlTypes.JSON  )
    @Column(columnDefinition = "json")
    private List<String> suggestions;

    @JdbcTypeCode(SqlTypes.JSON  )
    @Column(columnDefinition = "json")
    private List<String> safety;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;

}
