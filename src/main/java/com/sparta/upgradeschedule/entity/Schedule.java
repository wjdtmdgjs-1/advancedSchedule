package com.sparta.upgradeschedule.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "writer_id")
    private Long writerId;

    @Column(name = "schedule_title")
    private String scheduleTitle;

    @Column(name = "schedule_contents")
    private String scheduleContents;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,name = "write_date")
    private LocalDateTime writeDate;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    public Schedule(
            Long id,
            Long writerId,
            String scheduleTitle,
            String scheduleContents,
            LocalDateTime writeDate,
            LocalDateTime updateDate) {
        this.id = id;
        this.writerId = writerId;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }

    @OneToMany(mappedBy = "schedule",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> commentList = new ArrayList<>();

    //comment 갯수 표현하기위함.
    @Column(name = "comment_count")
    private int commentCount = countComment(commentList);

    @OneToMany(mappedBy = "schedule")
    private List<Pic> picList = new ArrayList<>();

    public Schedule(Long writerId, String scheduleTitle, String scheduleContents) {
        this.writerId=writerId;
        this.scheduleTitle=scheduleTitle;
        this.scheduleContents=scheduleContents;

    }

    public void update(String scheduleTitle, String scheduleContents) {
        this.scheduleTitle=scheduleTitle;
        this.scheduleContents =scheduleContents;
    }

    //comment의 갯수를 세어주는 메서드
    public int countComment(List<Comment> list){
        int a =0;
        for(Comment c : list){
            a++;
        }
        return a;
    }
}
