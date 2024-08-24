package com.sparta.upgradeschedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "comment_write_date")
    private LocalDateTime commentWriteDate;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "comment_update_date")
    private LocalDateTime commentUpdateDate;

    @Column(name= "comment_writer_name")
    private String commentWriterName;

    @Column(name = "comment_contents")
    private String commentContents;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;



}
