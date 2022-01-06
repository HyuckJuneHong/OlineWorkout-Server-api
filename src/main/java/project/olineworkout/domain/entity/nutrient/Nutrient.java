package project.olineworkout.domain.entity.nutrient;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="tbl_Nutrient")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Nutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nno;

    private String foodName;
    private Long protein;  //100g당
    private Long fat;
    private Long carbs;
}
