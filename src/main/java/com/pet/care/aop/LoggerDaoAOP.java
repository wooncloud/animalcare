package com.pet.care.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDaoAOP {

	public void before(JoinPoint j) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");

		Object[] objs = j.getArgs();
		if (objs != null) {
			log.info("[ * DAO AOP Log * ] : {} =>", j.getSignature().getName());
			for (int i = 0; i < objs.length; i++) {
				log.warn("\t" + i + "번째 Args : " + String.valueOf(objs[i]));
			}

			log.info("<= [ * DAO AOP Log * ] : {}", j.getSignature().getName());
		}
	}

	public void afterReturning(JoinPoint j) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		log.info("[ * DAO AOP Log * ] 종료 : {}", j.getSignature().getName());
	}

	public void afterThrowing(JoinPoint j) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		log.warn("[ * DAO AOP Log * ] 에러발생 : {}", j.getSignature().getName());
	}

}
