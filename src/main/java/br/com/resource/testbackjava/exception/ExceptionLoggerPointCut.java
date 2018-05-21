package br.com.resource.testbackjava.exception;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@Aspect
public class ExceptionLoggerPointCut {
	
		private static final String VIEW_HOME = "index.html";
		
		private static final String SISTEMA_INDISPONIVEL = "Sistema Indisponivel";

		private Logger logger = LoggerFactory.getLogger(ExceptionLoggerPointCut.class);

		@Around("execution(* br.com.resource.testbackjava.controller.*.*(..) )")
		public Object catchErrors(ProceedingJoinPoint joinPoint) {
			try {
				Object proceed = joinPoint.proceed();
				return proceed;
			} catch (Throwable e) { 
				logger.error(e.getMessage(), (Exception) e);

				String msgErro = SISTEMA_INDISPONIVEL;

				if (e instanceof MensagemTratadaException) {
					msgErro = e.getMessage();
				} 
//				else  {
//					msgErro = e.getCause().getMessage();
//				} 				

				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, String> responseJson = new HashMap<>();
				responseJson.put("message", msgErro);
		        JsonNode jsonNode = objectMapper.valueToTree(responseJson);
				return new ResponseEntity<>(jsonNode, HttpStatus.BAD_REQUEST);
			}
		}

	

		
}
