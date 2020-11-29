package com.tandg.rcver.springui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.tandg.rcver.springui.model.Contest;
import com.tandg.rcver.springui.service.RankedChoiceVoteService;

@Configuration
@ComponentScan(basePackages = "com.tandg.rcver.springui")
@EnableAspectJAutoProxy
public class RcverSpringUiConfig {
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	private RankedChoiceVoteService rankedChoiceVoteService;
	
	@Bean
	public Contest getContest() {
		Collection<String> candidates = new ArrayList<String>();
		candidates.add("test");
		System.out.println("test");
		candidates.add("test2");
		System.out.println("test2");
		candidates.add("test3");
		System.out.println("test3");
		candidates.add("test4");
		System.out.println("test4");
		candidates.add("test5");
		System.out.println("test5");
		Collection<List<String>> rawBallots = new ArrayList<List<String>>();
		
		
        return this.rankedChoiceVoteService.createContest(candidates, rawBallots);
	}
	
	@Bean
    public ViewResolver thymeleafResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(0);
        return viewResolver;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver () {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;

    }
    
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

}
