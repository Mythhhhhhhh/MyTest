package cn.myth.design_pattern.creational_pattern.prototype.question;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 选择题
 */
@Data
@AllArgsConstructor
public class ChoiceQuestion {

    private String name;                 // 题目
    private Map<String, String> option;  // 选项；A、B、C、D
    private String key;

}
