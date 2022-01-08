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

    //유저들이 직접 음식의 영양성분을 등록해 사용하기 위한 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nno;

    private String foodName;
    private Long protein;  //100g당
    private Long fat;
    private Long carbs;

      /*TODO
        영양성분 업데이트 함수
     */
}
