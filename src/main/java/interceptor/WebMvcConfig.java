package interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	HandlerInterceptor controllerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// インターセプト登録
		registry.addInterceptor(controllerInterceptor);
	}
}