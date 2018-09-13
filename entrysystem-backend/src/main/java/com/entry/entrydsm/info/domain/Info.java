package com.entry.entrydsm.info.domain;

import com.entry.entrydsm.common.domain.BaseTimeEntity;
import com.entry.entrydsm.info.dto.InfoDTO;
import com.entry.entrydsm.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Getter
public class Info extends BaseTimeEntity {
    @Id
    @Column(length = 32)
    @JsonIgnore
    private String userId;

    @Column(length = 20, nullable = false)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(length = 15, nullable = false)
    private String myTel;

    @Column(length = 20, nullable = false)
    private String parentName;

    @Column(length = 15, nullable = false)
    private String parentTel;

    @Column(length = 100, nullable = false)
    private String addressBase;

    @Column(length = 50, nullable = false)
    private String addressDetail;

    @Column(length = 5, nullable = false)
    private String zipCode;

    @Column(length = 50)
    private String imgPath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(userId, info.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public Info(User user) {
        this.userId = user.getId();
        this.addressBase = "";
        this.myTel = "";
        this.addressDetail = "";
        this.name = "";
        this.parentName = "";
        this.parentTel = "";
        this.zipCode = "";
        this.birth = LocalDate.of(2002, 1, 1);
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
        this.imgPath = infoDTO.getImgPath();
    }
}
