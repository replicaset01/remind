package com.returns.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
//    public TxAdvisor(TransactionManager transactionManager) {
//        this.transactionManager = transactionManager;
//    }
//
//    @Bean
//    public TransactionInterceptor txAdvice() {
//
//        NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();
//
//        RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();
//        txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//
//        RuleBasedTransactionAttribute txFindAttribute = new RuleBasedTransactionAttribute();
//        txFindAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        txFindAttribute.setReadOnly(true);
//
//        Map<String, TransactionAttribute> txMethods = new HashMap<>();
//        txMethods.put("find*", txFindAttribute);
//        txMethods.put("*", txAttribute);
//
//        txAttributeSource.setNameMap(txMethods);
//
//        return new TransactionInterceptor(transactionManager, txAttributeSource);
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