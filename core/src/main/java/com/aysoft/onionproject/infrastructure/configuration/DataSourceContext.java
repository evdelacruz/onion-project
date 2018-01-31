package com.aysoft.onionproject.infrastructure.configuration;

import com.aysoft.onionproject.infrastructure.seedwork.i18n.MessageContex;
import com.aysoft.onionproject.infrastructure.seedwork.util.FileUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages={"com.aysoft.onionproject.application.module.*.impl",
                             "com.aysoft.onionproject.domain.module.*.impl"})
@EnableJpaRepositories("com.aysoft.onionproject.domain.module.*.datasource")
public class DataSourceContext {

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl(String.format("jdbc:postgresql://%s/%s", host, dbname));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(maxPoolSize);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setMaxLifetime(maxLifeTime);
        dataSource.setPoolName(String.format("%s-dbcp", appname));
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
        vendor.setGenerateDdl(false);
        vendor.setShowSql(false);
        vendor.setDatabase(Database.POSTGRESQL);
        return vendor;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean manager = new LocalContainerEntityManagerFactoryBean();
        manager.setDataSource(dataSource());
        manager.setPackagesToScan("com.aysoft.onionproject.domain.module.*.datasource.domain");
        manager.setMappingResources(
                FileUtils.findFilesPaths(
                        "classpath*:com/aysoft/onionproject/domain/module/*/datasource/query/*.xml",
                        "Queries",
                        ".xml",
                        9,
                        "/"));
        manager.setJpaVendorAdapter(vendorAdapter());
        return manager;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        manager.setJpaDialect(new HibernateJpaDialect());
        return manager;
    }

    //<editor-fold desc="i18n">
    @Bean
    public MessageSource messageSource() {
        return MessageContex.SOURCE;
    }
    //</editor-fold>

    //<editor-fold desc="Dependency injection">
    private @Value("${application.name}") String appname;
    private @Value("${application.db.host}") String host;
    private @Value("${application.db.name}") String dbname;
    private @Value("${application.db.username}") String username;
    private @Value("${application.db.password}") String password;
    private @Value("${application.db.max-pool-size}") int maxPoolSize;
    private @Value("${application.db.connection-timeout}") int connectionTimeout;
    private @Value("${application.db.max-life-time}") int maxLifeTime;
    //</editor-fold>
}
