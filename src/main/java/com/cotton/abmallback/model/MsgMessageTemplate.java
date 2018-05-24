package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import javax.persistence.*;

@Table(name = "msg_message_template")
public class MsgMessageTemplate extends BaseModel {
    /**
     * 消息分类:分享奖励,晋级奖励,高管薪酬
     */
    private String type;

    /**
     * 消息模板内容
     */
    private String content;

    /**
     * 获取消息分类:分享奖励,晋级奖励,高管薪酬
     *
     * @return type - 消息分类:分享奖励,晋级奖励,高管薪酬
     */
    public String getType() {
        return type;
    }

    /**
     * 设置消息分类:分享奖励,晋级奖励,高管薪酬
     *
     * @param type 消息分类:分享奖励,晋级奖励,高管薪酬
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取消息模板内容
     *
     * @return content - 消息模板内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置消息模板内容
     *
     * @param content 消息模板内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}