package com.nowcoder.community.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author rainlu
 * @version 1.0.0
 * @Description 验证码插件Kaptcha的配置
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public Producer kaptchaProducer(){
        Properties properties=new Properties();
        properties.setProperty("kaptcha.image.width", "100");   // 验证码宽度
        properties.setProperty("kaptcha.image.height", "40");   // 验证码高度
        properties.setProperty("kaptcha.textproducer.font.size", "32");   // 验证码字体大小
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");  // 验证码字体颜色
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYAZ"); // 验证码字库
        properties.setProperty("kaptcha.textproducer.char.length", "4"); // 验证码字符串长度
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");// 验证码干扰类

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
