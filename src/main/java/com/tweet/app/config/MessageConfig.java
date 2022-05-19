package com.tweet.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * メッセージ設定クラス
 *
 */
@Configuration
@PropertySource(value = "classpath:messages/messages.properties")
public class MessageConfig {

	/**
	 * プロパティリソースホルダー
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}