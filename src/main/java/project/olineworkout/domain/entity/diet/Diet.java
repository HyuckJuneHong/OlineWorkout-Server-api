package project.olineworkout.domain.entity.diet;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.olineworkout.domain.entity.user.User;
import project.olineworkout.domain.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="tbl_diet")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Diet extends BaseEntity {

    private String dietType; // BREAKFAST, LUNCH, DINNER

    private Long total_calorie; //하루에 먹어야 하는 칼로리량
    private Long total_fat;
    private Long total_protein;
    private Long total_carbs;


    @JoinColumn(name = "uno")
    @ManyToOne
    private User user;


}
