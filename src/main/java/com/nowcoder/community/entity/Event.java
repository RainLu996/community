package com.nowcoder.community.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rainlu
 * @version 1.0.0
 * @Description 封装事件实体类，便于在消息队列传递消息
 *       触发事件的情况：
 *              - 评论
 *              - 点赞
 *              - 关注
 */
public class Event {

    private String topic;     // 事件的类型
    private int userId;       // 触发事件的人物id

    private int entityType;   // 实体类型
    private int entityId;     // 实体id
    private int entityUserId; // 事件的目标人物id

    /*
    把其他额外的数据，存入map中，具有扩展性
    目的：为了后期动态扩展，有些字段没有办法做出预判
     */
    private Map<String, Object> data = new HashMap<>();

    public String getTopic() {
        return topic;
    }

    /*
    此处这样设计，是为了更灵活的设置属性，避免使用多个构造函数。即：【链式编程】
    这样设计很灵活和方便
     */
    public Event setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Event setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public Event setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public Event setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityUserId() {
        return entityUserId;
    }

    public Event setEntityUserId(int entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Event setData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
