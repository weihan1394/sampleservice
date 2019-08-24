package com.govtech.sampleservice.repo.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:test.${integration_test_db}.properties"})
public class TestHibernateConf {

  public static String DATABASE_URL = null;
  public static String HOST = null;

  @Autowired private Environment env;

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan(new String[] {"com.govtech.sampleservice.repo.entities"});
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
  }

  @Bean
  public Flyway flyway() {
    String envVar = env.getProperty("integration_test_db");
    String quickTest = env.getProperty("quick_test");
    Boolean quickTestStatus = quickTest != null ? Boolean.valueOf(quickTest) : false;

    if (envVar.equals("gitlab") || quickTestStatus == false) {
      Flyway flyway = Flyway.configure().dataSource(dataSource()).load();
      flyway.clean();
      flyway.migrate();
      return flyway;
    }
    return null;
  }

  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
    dataSource.setUrl(env.getProperty("db.url"));
    dataSource.setUsername(env.getProperty("mysql.user"));
    dataSource.setPassword(env.getProperty("mysql.password"));

    return dataSource;
  }

  @Bean
  public PlatformTransactionManager hibernateTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }

  private final Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("db.hbm2ddl.auto"));
    hibernateProperties.setProperty("hibernate.jdbc.batch_size", "20");
    String envVar = env.getProperty("integration_test_db");
    String quickTest = env.getProperty("quick_test");
    Boolean quickTestStatus = quickTest != null ? Boolean.valueOf(quickTest) : false;

    if (envVar.equals("gitlab") || quickTestStatus == false) {
      hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "none");
    } else {
      hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("db.hbm2ddl.auto"));
    }
    hibernateProperties.setProperty("hibernate.dialect", env.getProperty("db.dialect"));
    hibernateProperties.setProperty("hibernate.show_sql", "false");
    hibernateProperties.setProperty("hibernate.format_sql", "true");

    return hibernateProperties;
  }
}
