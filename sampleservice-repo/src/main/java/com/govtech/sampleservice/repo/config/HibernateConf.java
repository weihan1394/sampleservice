package com.govtech.sampleservice.repo.config;

import com.govtech.sampleservice.repo.properties.DbProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Slf4j
public class HibernateConf extends HikariConfig {

  @Bean
  public LocalSessionFactoryBean sessionFactory(DbProperties dbProperties) {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource(dbProperties));
    sessionFactory.setPackagesToScan("com.govtech.sampleservice.repo.entities");
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
  }

  @Bean
  public Flyway flyway(DbProperties dbProperties) {
    Flyway flyway = Flyway.configure().dataSource(dataSource(dbProperties)).load();
    flyway.migrate();
    return flyway;
  }

  @Bean
  public DataSource dataSource(DbProperties dbProperties) {
    HikariConfig config = new HikariConfig();
    String url =
        String.format(
            "jdbc:mysql://%s:%s/%s",
            dbProperties.getHost(), dbProperties.getPort(), dbProperties.getSchema());
    config.setJdbcUrl(url);
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setUsername(dbProperties.getUser());
    config.setPassword(dbProperties.getPassword());
    config.setMaximumPoolSize(10);
    config.addDataSourceProperty("cachePrepStmts", true);
    config.addDataSourceProperty("prepStmtCacheSize", 256);
    config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
    config.addDataSourceProperty("useServerPrepStmts", true);
    config.addDataSourceProperty("rewriteBatchedStatements", true);
    config.addDataSourceProperty("useLocalSessionState", true);
    //    config.setMaxLifetime(10000);
    HikariDataSource dataSource = new HikariDataSource(config);

    return dataSource;
  }

  @Bean
  @Qualifier("hibernateTransactionManager")
  public PlatformTransactionManager hibernateTransactionManager(DbProperties dbProperties) {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory(dbProperties).getObject());

    return transactionManager;
  }

  private final Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "none");
    hibernateProperties.setProperty("hibernate.jdbc.batch_size", "20");
    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    hibernateProperties.setProperty("hibernate.show_sql", "false");
    hibernateProperties.setProperty("hibernate.format_sql", "false");
    hibernateProperties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
    //        hibernateProperties.setProperty("hibernate.generate_statistics", "true");

    return hibernateProperties;
  }
}
