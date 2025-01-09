package cn.myth.design_pattern.creational_pattern.prototype.question;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 问答题
 */
@Data
@AllArgsConstructor
public class AnswerQuestion {
    private String name;  // 问题
    private String key;
}
