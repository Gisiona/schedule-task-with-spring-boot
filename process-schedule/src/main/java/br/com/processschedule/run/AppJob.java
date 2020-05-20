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
    
    // @Scheduled(cron = "* * 13 * * MON-FRI")
    // * = executa em todos os segundos
    // * = executa em todos os minutos
    // 13 = inicia a execução do processo as 13 horas
    // * = executa em todos os meses
    // * = executa em todos os anos
    // MON-FRI = executa em todos os dias de segunda a sexta feira
    
	// @Scheduled(initialDelay = SEGUNDO, fixedDelay = MINUTO)
    @Scheduled(cron = "* * 13 * * MON-FRI")
	public void run() throws InterruptedException {
		try {
			System.out.println("");
			System.out.println("==========================================================================================");
			System.out.println("INICIO do processamento" + LocalDateTime.now());
			System.out.println("==========================================================================================");
						
			processor.executar();
			
			System.out.println("==========================================================================================");
			System.out.println("FIM do processamento: " + LocalDateTime.now());
			System.out.println("==========================================================================================");
			System.out.println("");
		} catch (Exception e) {
			System.out.println("DEU ERRO NO PROCESSAMENTO: ERRO = " + e + " DATA = " + LocalDateTime.now());
		}
		
	}
	
}
