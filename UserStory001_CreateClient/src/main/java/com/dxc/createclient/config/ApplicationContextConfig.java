package com.dxc.createclient.config;


import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.dxc.createclient.dao.AccountDAO;
import com.dxc.createclient.dao.UserDAO;
import com.dxc.createclient.dao.implement.AccountDAOImpl;
import com.dxc.createclient.dao.implement.UserDAOImpl;



@Configuration
@ComponentScan("com.dxc.createclient")
@EnableTransactionManagement
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class ApplicationContextConfig {

	@Autowired
	private Environment evn;

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		// Load property in message/validator.properties
		rb.setBasenames(new String[] { "messages/validator"});
		return rb;
	}

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	//Upload Config
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		//Set max size here: .setMaxUploadSize();
		return commonsMultipartResolver;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//		
		//		//Look at ds-hibernate-cfg.properties
		//		dataSource.setDriverClassName(evn.getProperty("ds.database-driver"));
		//		dataSource.setUrl(evn.getProperty("ds.url"));
		//		dataSource.setUsername(evn.getProperty("ds.username"));
		//		dataSource.setPassword(evn.getProperty("ds.password"));
		//		
		//		System.out.println("##getDataSource: " + dataSource);
		//		return dataSource;

		//Apache Commons DBCP
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(evn.getProperty("ds.database-driver"));
		dataSource.setUrl(evn.getProperty("ds.url"));
		dataSource.setUsername(evn.getProperty("ds.username"));
		dataSource.setPassword(evn.getProperty("ds.password"));
		dataSource.setMinIdle(1);
		dataSource.setInitialSize(1);
		dataSource.setMaxIdle(10);
		dataSource.setMaxOpenPreparedStatements(100);
		System.out.println("##getDataSource: " + dataSource);
		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		Properties properties = new Properties();
		//Look at ds-hibernate-cfg.properties
		properties.put("hibernate.dialect", evn.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", evn.getProperty("hibernate.show_sql"));
		properties.put("current_session_context_class", evn.getProperty("current_session_context_class"));
		properties.put("hibernate.hbm2ddl.auto", evn.getProperty("hibernate.hbm2ddl.auto"));


		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		//Package include Entity class
		factoryBean.setPackagesToScan("com.dxc.createclient.entity");
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();


		SessionFactory sf = factoryBean.getObject();
		System.out.println("##getSessionFactory: " + sf);
		return sf;

	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	@Bean(name = "accountDAO")
	public AccountDAO getAccountDAO() {
		return new AccountDAOImpl();
	}

	@Bean(name = "userDAO")
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
}

