package cn.myth.design_pattern.creational_pattern.prototype.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Topic {

    private Map<String, String> option;  // 选项；A、B、C、D
    private String key;           // 答案；B

}
