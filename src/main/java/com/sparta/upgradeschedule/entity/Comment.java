package com.sparta.upgradeschedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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

    @Column(name= "comment_writer_name")
    private String commentWriterName;

    @Column(name = "comment_contents")
    private String commentContents;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "comment_write_date")
    private LocalDateTime commentWriteDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "comment_update_date")
    private LocalDateTime commentUpdateDate;

    public Comment(Long id,
                   String commentWriterName,
                   String commentContents,
                   LocalDateTime commentWriteDate,
                   LocalDateTime commentUpdateDate) {
        this.id = id;
        this.commentWriterName = commentWriterName;
        this.commentContents = commentContents;
        this.commentWriteDate = commentWriteDate;
        this.commentUpdateDate = commentUpdateDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="schedule_id")
    private Schedule schedule;



    public Comment(String commentWriterName, String commentContents) {
        this.commentWriterName=commentWriterName;
        this.commentContents=commentContents;
    }

    public void update(String commentContents) {
        this.commentContents=commentContents;
    }
}
