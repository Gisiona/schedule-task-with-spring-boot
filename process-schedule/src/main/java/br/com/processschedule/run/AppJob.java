package br.com.processschedule.run;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.processschedule.processor.EmailProcessor;

@EnableScheduling
@Component
public class AppJob {
	
	private final long SEGUNDO = 1000; 
    private final long MINUTO = SEGUNDO * 60; 
    private final long HORA = MINUTO * 60;
    
    @Autowired
    private EmailProcessor processor;
    
	@Scheduled(initialDelay = SEGUNDO, fixedDelay = MINUTO)
	public void run() throws InterruptedException {
		try {
			System.out.println("==========================================================================================");
			System.out.println("INICIO do processamento" + LocalDateTime.now());
			System.out.println("==========================================================================================");
			
			processor.executar();
			System.out.println("FIM do processamento: " + LocalDateTime.now());
			System.out.println("==========================================================================================");
		} catch (Exception e) {
			System.out.println("DEU ERRO NO PROCESSAMENTO: ERRO = " + e + " DATA = " + LocalDateTime.now());
		}
		
	}
	
}
