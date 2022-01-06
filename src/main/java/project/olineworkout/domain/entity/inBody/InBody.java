package project.olineworkout.domain.entity.inBody;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.olineworkout.domain.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tbl_inBody")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class InBody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private Long height;
    private Long weight;
    private Long fatMass;    //체지방량
    private Long muscleMass; //근육량
    private String basal_Metabolism;     //기초 대사량
    private String maintenance_Calories; //유지 칼로리

    private LocalDate checkDate;

    @JoinColumn(name = "uno")
    @ManyToOne
    private User user;
}