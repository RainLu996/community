package com.nowcoder.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author rainlu
 * @version 1.0.0
 * @Description Spring线程池 配置
 */
@Configuration
@EnableScheduling
@EnableAsync
public class ThreadPoolConfig {
}
