package com.jh4dev.myapp.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"ssn"})
@Schema(description = "User Domain")
@Entity
@Table(name = "users")
public class User {

    @Schema(title = "사용자 ID", description = "사용자 ID는 자동 생성됩니다.")
    @Id
    @GeneratedValue
    private Integer id;

    @Schema(title = "사용자 이름", description = "사용자 이름을 입력하세요.")
    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
    private String name;

    @Schema(title = "사용자 등록일", description = "사용자 등록일을 입력하세요. 입력하지 않을 경우 현재 일자로 등록됩니다.")
    @Past(message = "등록일은 미래 날짜로 입력하실 수 없습니다.")
    private Date joinDate;

//    @JsonIgnore
    @Schema(title = "사용자 패스워드", description = "사용자 패스워드를 입력하세요.")
    private String password;

//    @JsonIgnore
    @Schema(title = "사용자 주민등록번호", description = "주민등록번호를 입력하세요.")
    private String ssn;
}
