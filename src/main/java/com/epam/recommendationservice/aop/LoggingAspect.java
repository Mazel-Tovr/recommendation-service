package com.epam.recommendationservice.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
@Aspect
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution (public * com.epam.recommendationservice.controllers.*.*(..))")
    public void controllersApis(){};

    @Around("controllersApis()")
    public void logAllRequests(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();
        pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder httpContent = new StringBuilder();
        httpContent.append("\"Header\":{ \n");
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            httpContent.append(String.format("\"%s\" :  \"%s\"",element,request.getHeader(element))).append(headerNames.hasMoreElements()? ",\n":"");
        }
        httpContent.append("}\n");
        //Some code to get body
        httpContent.append("\"Body\":{");
        Object[] args = pjp.getArgs();
        for (int i = 0; i <args.length; i++) {
            httpContent.append(String.format("\"args%d\": \"%s\"",i,args[i])).append((i+1)<args.length?",\n":"");
        }
        httpContent.append("}\n");
        logger.info(String.format(" %s \n \"Time spend on request\": \"%d\"", httpContent.toString(), elapsedTime));
    }
}
