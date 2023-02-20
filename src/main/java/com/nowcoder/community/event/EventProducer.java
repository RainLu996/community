package com.nowcoder.community.event;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.community.entity.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rainlu
 * @version 1.0.0
 * @Description 事件生产者
 */

@Component
public class EventProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 处理事件
    public void fireEvent(Event event){
        // 将事件发送到指定主题（交换机）：把内容转换为json对象，消费者收到json后，可以将json转换成Event
        /* 没有使用到交换机，直接将消息发送到队列 */
        rabbitTemplate.convertAndSend(event.getTopic(), JSONObject.toJSONString(event));
    }
}
