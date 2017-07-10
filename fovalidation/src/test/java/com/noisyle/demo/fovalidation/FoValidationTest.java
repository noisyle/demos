package com.noisyle.demo.fovalidation;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.fozone.validation.BasicValidateService;
import cc.fozone.validation.IValidateService;
import cc.fozone.validation.config.BasicValidateConfig;
import cc.fozone.validation.config.IValidateConfig;

public class FoValidationTest {

	@Test
	public void testFoValidation() {
		/**
		 * Validation.FO的配置资源
		 */
		// 验证器配置，系统默认配置
		String validatorsXML = "validationfo/validators.fo.xml";
		// 规则配置
		String rulesXML = "validationfo/rules.fo.xml";

		/**
		 * 实例化配置对象
		 */
		IValidateConfig config = new BasicValidateConfig(validatorsXML, rulesXML);
		/**
		 * 实例化验证服务层
		 */
		IValidateService validateService = new BasicValidateService(config);

		// 实例化用户
		User user = createUser();

		/**
		 * 执行验证
		 */
		Map<String, String> map = validateService.validate(user, "user.validate");
		// 输出结果
		if (map == null || map.size() == 0) {
			System.out.println("验证成功");
		} else {
			System.out.println("验证失败，结果如下");
			System.out.println(map);
		}
	}

	@Test
	public void testFoValidationWithSpring() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");

		// 获取验证服务
		IValidateService service = ctx.getBean(IValidateService.class);
		// 创建用户对象
		User user = createUser();

		// 执行验证
		Map<String, String> map = service.validate(user, "user.validate");
		// 输出结果
		if (map == null || map.size() == 0) {
			System.out.println("验证成功");
		} else {
			System.out.println("验证失败，结果如下");
			System.out.println(map);
		}
	}

	private User createUser() {
		User user = new User();
		return user;
	}
}
