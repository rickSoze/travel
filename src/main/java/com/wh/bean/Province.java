package com.wh.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Province {
    private Integer id;
    private String name;
    private String tags;
    private Integer placecounts;
}
