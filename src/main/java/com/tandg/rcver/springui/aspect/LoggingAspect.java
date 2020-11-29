package com.tandg.rcver.springui.aspect;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tandg.rcver.springui.model.Contest;
import com.tandg.rcver.springui.presentationobject.BallotPO;

@Aspect
@Component
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* com.tandg.rcver.springui.controller.*.electionLanding*(..))")
	public void getNameAdvice(){
		System.out.println("Executing Advice on controller get");
	}
	
	@Before("execution(* com.tandg.rcver.springui.service.*.get*())")
	public void getAllAdvice(){
		System.out.println("Service method get called");
	}
	
	
}
