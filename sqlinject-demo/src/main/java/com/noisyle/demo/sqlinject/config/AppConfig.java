package com.noisyle.demo.sqlinject.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = { "classpath:/spring-context.properties" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan({ "com.noisyle.demo.sqlinject.repository" })
@MapperScan("com.noisyle.demo.sqlinject.persistence")
public class AppConfig {

	public static Environment env;

	@Autowired
	private void setEnv(Environment env) {
		AppConfig.env = env;
	}

	@Bean
	public DataSource dataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
//		dataSource.setUrl(env.getProperty("jdbc.url=jdbc:h2:mem:test"));
//		dataSource.setUsername(env.getProperty("jdbc.username"));
//		dataSource.setPassword(env.getProperty("jdbc.password"));
//		return dataSource;
		
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("db/init/create-db.sql")
			.addScript("db/init/insert-data.sql")
			.build();
		return db;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setTypeAliasesPackage("com.noisyle.demo.sqlinject.entity");
		return sqlSessionFactory;
	}
	
}
