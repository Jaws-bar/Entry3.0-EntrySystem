package com.entry.entrydsm.info.dto;

import com.entry.entrydsm.common.validate.Ged;
import com.entry.entrydsm.common.validate.Graduate;
import com.entry.entrydsm.support.ValidationTest;
import com.entry.entrydsm.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class InfoDTOTest extends ValidationTest {

    private InfoDTO infoDto;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        infoDto = InfoDTO.builder()
                .name("")
                .addressBase("")
                .addressDetail("")
                .birth(LocalDate.now())
                .myTel("")
                .parentName("")
                .parentTel("")
                .schoolCode(null)
                .schoolTel("")
                .sex(null)
                .studentClass(3)
                .studentNumber(3)
                .zipCode("")
                .build();
    }

    @Test
    public void 유효() {
        assertConstraintViolations(infoDto, 0, Ged.class);
        assertConstraintViolations(infoDto, 0, Graduate.class);
    }

    @Test
    public void 이름이_NULL() {
        infoDto.setName(null);
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 이름이_20자_초과() {
        infoDto.setName(StringUtils.repeat("A", 21));
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 생년월일이_NULL() {
        infoDto.setBirth(null);
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 반이_NULL() {
        infoDto.setStudentClass(null);
        assertConstraintViolations(infoDto, 0, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 반이_0_미만() {
        infoDto.setStudentClass(-1);
        assertConstraintViolations(infoDto, 0, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 번호가_NULL() {
        infoDto.setStudentNumber(null);
        assertConstraintViolations(infoDto, 0, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 번호가_0_미만() {
        infoDto.setStudentNumber(-1);
        assertConstraintViolations(infoDto, 0, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 학교_번호가_NULL() {
        infoDto.setSchoolTel(null);
        assertConstraintViolations(infoDto, 0, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 학교_번호_형식_안맞음_공백() {
        infoDto.setSchoolTel(" ");
        assertConstraintViolations(infoDto, 0, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 학교_번호_형식_안맞음_문자() {
        infoDto.setSchoolTel("a");
        assertConstraintViolations(infoDto, 0, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 학교_번호_11자_아님() {
        infoDto.setSchoolTel(StringUtils.repeat("0", 6));
        assertConstraintViolations(infoDto, 0, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 보호자_성함이_NULL() {
        infoDto.setParentName(null);
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 보호자_성함이_20자_초과() {
        infoDto.setParentName(StringUtils.repeat("A", 21));
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 보호자_전화번호가_NULL() {
        infoDto.setParentTel(null);
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 보호자_전화번호_형식_안맞음_공백() {
        infoDto.setParentTel(" ");
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 보호자_전화번호_형식_안맞음_문자() {
        infoDto.setParentTel("a");
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 보호자_전화번호_11자_아님() {
        infoDto.setParentTel(StringUtils.repeat("0", 6));
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 전화번호가_NULL() {
        infoDto.setMyTel(null);
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 전화번호_형식_안맞음_공백() {
        infoDto.setMyTel(" ");
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 전화번호_형식_안맞음_문자() {
        infoDto.setMyTel("a");
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 전화번호_11자_아님() {
        infoDto.setMyTel(StringUtils.repeat("0", 6));
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 우편번호_NULL() {
        infoDto.setZipCode(null);
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 우편번호_형식_안맞음_문자() {
        infoDto.setZipCode("aaaaa");
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 우편번호_5자_아님() {
        infoDto.setZipCode(StringUtils.repeat("0", 3));
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 주소_NULL() {
        infoDto.setAddressBase(null);
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 주소_100자_초과() {
        infoDto.setAddressBase(StringUtils.repeat("s", 101));
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 상세주소_NULL() {
        infoDto.setAddressDetail(null);
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }

    @Test
    public void 상세주소_50자_초과() {
        infoDto.setAddressDetail(StringUtils.repeat("s", 51));
        assertConstraintViolations(infoDto, 1, Ged.class);
        assertConstraintViolations(infoDto, 1, Graduate.class);
    }
}