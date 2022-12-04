package com.returns.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.transaction.TransactionManager;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class TxAdvisor {

//    private final TransactionManager transactionManager;
//
//    public TxAdvisor(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") TransactionManager transactionManager) {
//        this.transactionManager = transactionManager;
//    }
//
//    @Bean
//    public TransactionInterceptor txAdvice() {
//
//        //i 메소드 이름 패턴에 따른 구분 적용
//        NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();
//
//        //i 조회 메소드를 제외한 공통 트랜잭션 Attribute
//        RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();
//        txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//
//        //i 조회 메소드에 적용하기 위한 트랜잭션 Attribute
//        RuleBasedTransactionAttribute txFindAttribute = new RuleBasedTransactionAttribute();
//        txFindAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        txFindAttribute.setReadOnly(true);
//
//        //i 설정한 트랜잭션 Attribute를 Map에 추가, Map의 키를 '메소드 이름 패턴' 으로 지정해서 각각의 트랜잭션 Attribute 추가
//        Map<String, TransactionAttribute> txMethods = new HashMap<>();
//        txMethods.put("find*", txFindAttribute);
//        txMethods.put("*", txAttribute);
//
//        //i 트랜잭션 Attribute를 추가한 Map 객체를 Source로 넘겨줌
//        txAttributeSource.setNameMap(txMethods);
//
//        //i TransactionInterceptor의 생성자 파라미터로 TransactionManager, txAttributeSource 전달
//        return new TransactionInterceptor((org.springframework.transaction.TransactionManager) transactionManager, txAttributeSource);
//    }
//
//    @Bean
//    public Advisor txAd() {
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression("execution(* com.returns.coffee.service." + "CoffeeService.*(..))");
//
//        return new DefaultPointcutAdvisor(pointcut, txAdvice());
//    }
}