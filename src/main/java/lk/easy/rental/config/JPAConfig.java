package lk.easy.rental.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/2/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Configuration
@EnableJpaRepositories(basePackages = "lk.easy.rental.repo")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JPAConfig {

    @Autowired
    Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds, JpaVendorAdapter va){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaVendorAdapter(va);
        bean.setDataSource(ds);
        bean.setPackagesToScan(environment.getRequiredProperty("entity.package.name"));
        return bean;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setUrl(environment.getRequiredProperty("my.app.url"));
        dataSource.setUsername(environment.getRequiredProperty("my.app.username"));
        dataSource.setPassword(environment.getRequiredProperty("my.app.password"));
        dataSource.setDriverClassName(environment.getRequiredProperty("my.app.driverclassname"));
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter vendor= new HibernateJpaVendorAdapter();
        vendor.setDatabasePlatform(environment.getRequiredProperty("my.app.dialect"));
        vendor.setDatabase(Database.MYSQL);
        vendor.setShowSql(true);
        vendor.setGenerateDdl(true);
        return vendor;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
