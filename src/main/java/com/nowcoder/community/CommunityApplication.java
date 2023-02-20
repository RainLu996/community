package com.nowcoder.community;

import com.nowcoder.community.util.CommunityConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommunityApplication implements CommunityConstant {
/*
	@PostConstruct
	public void init(){
		// 解决netty启动冲突问题
		// Redis和ES底层都依赖了netty，而ES底层会对这种netty冲突报异常
		// 详情请参见： Netty4Utils.setAvailableProcessors()
		System.setProperty("es.set.netty.runtime.available.processors", "false");
	}*/

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

	//声明队列（队列创建完毕后，请求注释掉所有@Bean注解）
	@Bean
	public Queue comment(){
		Queue comment = new Queue(TOPIC_COMMENT);
		//设置队列属性

		return comment;
	}

	@Bean
	public Queue like(){
		Queue like = new Queue(TOPIC_LIKE);
		//设置队列属性

		return like;
	}

	@Bean
	public Queue follow(){
		Queue follow = new Queue(TOPIC_FOLLOW);
		//设置队列属性

		return follow;
	}

	@Bean
	public Queue publish(){
		Queue publish = new Queue(TOPIC_PUBLISH);
		//设置队列属性

		return publish;
	}

	@Bean
	public Queue delete(){
		Queue delete = new Queue(TOPIC_DELETE);
		//设置队列属性

		return delete;
	}

	@Bean
	public Queue share(){
		Queue share = new Queue(TOPIC_SHARE);
		//设置队列属性

		return share;
	}
}

