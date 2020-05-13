package br.com.processschedule.processor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.processschedule.entity.Pedido;
import br.com.processschedule.repository.PedidoRepository;
import br.com.processschedule.service.EmailService;

@Component
public class EmailProcessor {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public void executar() throws InterruptedException {
		List<Pedido> pedidos = pedidoRepository.findAll();
		
		if(pedidos.size()<= 0 ) {
			System.out.println("Não foi encontrato nenhum registro para processar: Data= " + LocalDateTime.now());	
			return;
		}
				
		System.out.println(String.format("Total [%s] de registros encontrados para processar: Data= %s ", pedidos.size() , LocalDateTime.now()));
		
		for (Pedido pedido : pedidos) {
			System.out.println("==========================================================================================");
			Thread.sleep(2000);			
			System.out.println(String.format("Enviando e-mail para o pedido= [%s], Data= %s ", pedido.getId() , LocalDateTime.now()));			
			emailService.process(pedido);
			
			Thread.sleep(2000);			
			System.out.println(String.format("Excluido o registro processado do pedido= [%s], Data= %s ", pedido.getId() , LocalDateTime.now()));
			excluirPedidoProcessado(pedido);			
		}
		
		System.out.println("==========================================================================================");
	}
	
	private void excluirPedidoProcessado(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}
	
}
