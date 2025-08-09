package com.bancoacme.servicolistas.infrastructure.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogTempoExecucaoAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogTempoExecucaoAspect.class);
    private static final String CORRELATION_ID_KEY = "correlationId";

    @Around("@annotation(com.bancoacme.servicolistas.infrastructure.log.LogTempoExecucao)")
    public Object logTempo(ProceedingJoinPoint joinPoint) throws Throwable {
        long inicio = System.currentTimeMillis();
        Object resultado = joinPoint.proceed();
        long fim = System.currentTimeMillis();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LogTempoExecucao annotation = methodSignature.getMethod().getAnnotation(LogTempoExecucao.class);

        String nomeLog;
        if (annotation != null && !annotation.value().isEmpty()) {
            nomeLog = annotation.value();
        } else {
            nomeLog = methodSignature.getDeclaringType().getSimpleName() + "." + methodSignature.getName();
        }

        logger.info("Tempo de execução[{}] {} ms", nomeLog, (fim - inicio));
        return resultado;
    }
}
