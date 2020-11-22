package com.wh.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    private Integer id;
    private String name;
    private String picpath;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date hottime;
    private Double hotticket;
    private Double dimticket;
    private String placedes;
    private Integer provinceid;

}
