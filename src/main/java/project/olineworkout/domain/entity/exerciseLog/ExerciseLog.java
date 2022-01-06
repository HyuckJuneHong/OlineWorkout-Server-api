package project.olineworkout.domain.entity.exerciseLog;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.olineworkout.domain.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tbl_exercise_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ExerciseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eno;

    private String title;
    private String content;
    private String performYN;    // Y/N 운동 수행 여부
    private String total_sets;   // 총 운동 세트 수

    private LocalDate exerciseDate;  //운동한 날짜

    @JoinColumn(name = "uno")
    @ManyToOne
    private User user;
}
