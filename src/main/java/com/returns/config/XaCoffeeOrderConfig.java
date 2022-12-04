//package com.returns.config;
//
//import com.atomikos.jdbc.AtomikosDataSourceBean;
//import com.mysql.cj.jdbc.MysqlXADataSource;
//import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///* 커피 주문을 위한 데이터베이스 설정 */
//
///**
// * i JPA Repository 활성화
// * i DB를 사용하기 위한 패키지와 EntityManagerFactory 빈에대한 참조 설정
// * i basePackages = 기존 레포 사용, 경로 지정
// * i entityManager... = bean 생성 메소드명 지정
// */
//@EnableJpaRepositories(
//        basePackages = {
//                "com.returns.member",
//                "com.returns.stamp",
//                "com.returns.order",
//                "com.returns.coffee"},
//        entityManagerFactoryRef = "coffeeOrderEntityManager")
//
//@Configuration
//public class XaCoffeeOrderConfig {
//
//    /**
//     * i DB 접속 정보 생성
//     */
//    @Primary
//    @Bean
//    public DataSource dataSourceCoffeeOrder() {
//
//        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
//        mysqlXADataSource.setURL("jdbc:mysql://192.168.56.100:3306/coffee_order" +
//                "?allowPublicKeyRetrieval=true" +
//                "&characterEncoding=UTF-8");
//        mysqlXADataSource.setUser("guest");
//        mysqlXADataSource.setPassword("guest");
//
//        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
//        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
//        atomikosDataSourceBean.setUniqueResourceName("xaCoffeeOrder");
//
//        return atomikosDataSourceBean;
//    }
//
//    /**
//     * i JPA의 ENtityManager를 얻기 위해 LocalContainerEntityManagerFactoryBean 사용
//     * i LocalContainer..에서 사용하는 어댑터 중 우리가 사용하는
//     * i HibernateJpaVendorAdapter를 설정해주고,
//     * i Hibernate에서 필요한 설정 정보를 Map으로 설정 해줌
//     * i EntityManager가 사용할 Entity클래스가 위치한 패키지 경로 지정
//     */
//    @Primary
//    @Bean
//    public LocalContainerEntityManagerFactoryBean coffeeOrderEntityManager() {
//
//        LocalContainerEntityManagerFactoryBean emFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.MYSQL);
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "create");
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
//
//        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
//        properties.put("javax.persistence.transactionType", "JTA");
//
//        emFactoryBean.setDataSource(dataSourceCoffeeOrder());
//        emFactoryBean.setPackagesToScan(new String[]{
//                "com.returns.member",
//                "com.returns.stamp",
//                "com.returns.order",
//                "com.returns.coffee" });
//        emFactoryBean.setJpaVendorAdapter(vendorAdapter);
//        emFactoryBean.setPersistenceUnitName("coffeeOrderPersistenceUnit");
//        emFactoryBean.setJpaPropertyMap(properties);
//
//        return emFactoryBean;
//    }
//}
