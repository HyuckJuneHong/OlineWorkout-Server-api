package project.olineworkout.domain.shared;


import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

    @ApiModelProperty(example = "경기도")
    @Column(name = "state")
    private String state;

    @ApiModelProperty(example = "구리시")
    @Column(name = "city")
    private String city;

    @ApiModelProperty(example = "인창동")
    @Column(name = "district")
    private String dong;

    @Builder
    public Address(String state, String city, String dong) {
        this.state = state;
        this.city = city;
        this.dong = dong;
    }
}
