package cn.myth.design_pattern.creational_pattern.prototype;

import cn.myth.design_pattern.creational_pattern.prototype.question.AnswerQuestion;
import cn.myth.design_pattern.creational_pattern.prototype.question.ChoiceQuestion;
import cn.myth.design_pattern.creational_pattern.prototype.util.Topic;
import cn.myth.design_pattern.creational_pattern.prototype.util.TopicRandomUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Data
public class QuestionBank implements Cloneable {

    private String candidate; // 考生
    private String number;    // 考号

    private ArrayList<ChoiceQuestion> choiceQuestionList = new ArrayList<ChoiceQuestion>();
    private ArrayList<AnswerQuestion> answerQuestionList = new ArrayList<AnswerQuestion>();

    public QuestionBank append(ChoiceQuestion choiceQuestion) {
        choiceQuestionList.add(choiceQuestion);
        return this;
    }

    public QuestionBank append(AnswerQuestion answerQuestion) {
        answerQuestionList.add(answerQuestion);
        return this;
    }

    /*
     * 原型模式主要解决的问题就是创建大量重复的类，而我们模拟的场景就需要给不同的用户都创建相同的试卷，但这些试卷的题目不便于每次都从库中获取，
     * 甚至有时候需要从远程的RPC中获取。这样都是非常耗时的，而且随着创建对象的增多将严重影响效率。
     * 在原型模式中所需要的非常重要的手段就是克隆，在需要用到克隆的类中都需要实现 implements Cloneable 接口
     */

    @SuppressWarnings("unchecked")
    @Override
    public QuestionBank clone() {
        try {
            QuestionBank questionBank = (QuestionBank) super.clone();
            questionBank.choiceQuestionList = (ArrayList<ChoiceQuestion>) choiceQuestionList.clone();
            questionBank.answerQuestionList = (ArrayList<AnswerQuestion>) answerQuestionList.clone();

            // 题目乱序
            Collections.shuffle(questionBank.choiceQuestionList);
            Collections.shuffle(questionBank.answerQuestionList);
            // 答案乱序
            ArrayList<ChoiceQuestion> choiceQuestionList = questionBank.choiceQuestionList;
            for (ChoiceQuestion question : choiceQuestionList) {
                Topic random = TopicRandomUtil.random(question.getOption(), question.getKey());
                question.setOption(random.getOption());
                question.setKey(random.getKey());
            }
            return questionBank;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {

        StringBuilder detail = new StringBuilder("考生：" + candidate + "\r\n" +
                "考号：" + number + "\r\n" +
                "--------------------------------------------\r\n" +
                "一、选择题" + "\r\n\n");

        for (int idx = 0; idx < choiceQuestionList.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(choiceQuestionList.get(idx).getName()).append("\r\n");
            Map<String, String> option = choiceQuestionList.get(idx).getOption();
            for (String key : option.keySet()) {
                detail.append(key).append("：").append(option.get(key)).append("\r\n");;
            }
            detail.append("答案：").append(choiceQuestionList.get(idx).getKey()).append("\r\n\n");
        }

        detail.append("二、问答题" + "\r\n\n");

        for (int idx = 0; idx < answerQuestionList.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(answerQuestionList.get(idx).getName()).append("\r\n");
            detail.append("答案：").append(answerQuestionList.get(idx).getKey()).append("\r\n\n");
        }

        return detail.toString();
    }
}
