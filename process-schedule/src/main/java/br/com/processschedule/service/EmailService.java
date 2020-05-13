package br.com.processschedule.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.processschedule.entity.Pedido;
import br.com.processschedule.model.EmailModel;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EmailService {
					
	@Value("${mail.api-url}")
	private String URL_API;
	
	public void process(Pedido pedido) {
		try {
			RestTemplate restTemplate= new RestTemplate();
			EmailModel model = new EmailModel();
			model.setFrom("teste@teste.com");
			model.setTo("teste@teste.com");
			model.setSubject(String.format("Seu pedido [%s] foi processado com sucesso", pedido.getId()));
			model.setFrom(String.format("Segue detalhes do seu pedido: %s ", pedido.toString()));
			HttpEntity<EmailModel> request = new HttpEntity<EmailModel>(model);		
			
			ResponseEntity<Object> response = restTemplate.exchange(URL_API,HttpMethod.POST, request, Object.class);
			
			System.out.println("Email enviado com sucesso. TICKET: " + response.getBody());		
		} catch (HttpClientErrorException e) {
			System.out.println("Deu erro no envio do e-mail: " + e.getMessage());
		}		
	}

}
