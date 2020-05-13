package br.com.processschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.processschedule.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
