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

    @NotBlank
    @Column(length = 20, nullable = false)
    private String name;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false)
    private LocalDate birth;

    @Pattern(regexp = PHONE_NUMBER_PATTERN)
    @Column(length = 15, nullable = false)
    private String myTel;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String parentName;

    @Pattern(regexp = PHONE_NUMBER_PATTERN)
    @Column(length = 15, nullable = false)
    private String parentTel;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String addressBase;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String addressDetail;

    @NotNull
    @Pattern(regexp = ZIP_CODE_PATTERN)
    @Column(length = 5, nullable = false)
    private String zipCode;

    @Setter
    @NotBlank
    @Column(length = 50)
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
