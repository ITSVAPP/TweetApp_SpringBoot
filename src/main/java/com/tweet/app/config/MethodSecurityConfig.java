package com.tweet.app.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * メソッドセキュリティ設定クラス
 * 
 * メソッドに権限設定のアノテーションを付与するためのクラス
 *
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {

}
