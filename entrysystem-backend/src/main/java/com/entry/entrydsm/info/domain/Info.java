package com.entry.entrydsm.info.domain;

import com.entry.entrydsm.common.domain.BaseTimeEntity;
import com.entry.entrydsm.info.dto.InfoDTO;
import com.entry.entrydsm.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Getter
public class Info extends BaseTimeEntity {

    private static final String PHONE_NUMBER_PATTERN = "^\\d{11}$";
    private static final String ZIP_CODE_PATTERN = "^\\d{5}$";

    @Id
    @Column(length = 32)
    @JsonIgnore
    private String userId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @NotBlank(message = "이름을 입력해주세요.")
    @Column(length = 20, nullable = false)
    private String name;

    @Column
    @NotNull(message = "성별을 입력해주세요.")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(length = 15, nullable = false)
    @Pattern(regexp = PHONE_NUMBER_PATTERN,
            message = "전화번호를 입력해주세요. (숫자 11자)")
    private String myTel;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "보호자 성함을 입력해주세요.")
    private String parentName;

    @Column(length = 15, nullable = false)
    @Pattern(regexp = PHONE_NUMBER_PATTERN,
            message = "보호자 전화번호를 입력해주세요. (숫자 11자)")
    private String parentTel;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "주소를 입력해주세요.")
    private String addressBase;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "상세 주소를 입력해주세요.")
    private String addressDetail;

    @Column(length = 5, nullable = false)
    @NotNull(message = "우편번호를 제대로 입력해주세요.")
    @Pattern(regexp = ZIP_CODE_PATTERN,
            message = "우편번호를 제대로 입력해주세요.")
    private String zipCode;

    @Setter
    @Column(length = 50)
    @NotBlank(message = "증명사진을 업로드해주세요.")
    private String imgPath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(user, info.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    public Info(User user) {
        this.user = user;
        this.addressBase = "";
        this.myTel = "";
        this.addressDetail = "";
        this.name = "";
        this.parentName = "";
        this.parentTel = "";
        this.zipCode = "";
        this.birth = LocalDate.of(2003, 1, 1);
    }

    public void update(InfoDTO infoDTO) {
        this.name = infoDTO.getName();
        this.sex = infoDTO.getSex();
        this.birth = infoDTO.getBirth();
        this.myTel = infoDTO.getMyTel();
        this.parentName = infoDTO.getParentName();
        this.parentTel = infoDTO.getParentTel();
        this.addressBase = infoDTO.getAddressBase();
        this.addressDetail = infoDTO.getAddressDetail();
        this.zipCode = infoDTO.getZipCode();
    }
}
