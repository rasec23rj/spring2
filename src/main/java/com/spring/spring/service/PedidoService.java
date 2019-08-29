
package com.spring.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.spring.domain.ItemPedido;
import com.spring.spring.domain.PagamentoComBoleto;
import com.spring.spring.domain.Pedido;
import com.spring.spring.domain.enums.EstadoPagamento;
import com.spring.spring.repositories.ItemPedidoRepository;
import com.spring.spring.repositories.PagamentoRepository;
import com.spring.spring.repositories.PedidoRepository;
import com.spring.spring.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pgRe;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	/*@Autowired
	private EmailService emailService;
	*/

	public Pedido buscar(Integer id) {

		Optional<Pedido> obj = repo.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + ", Tipo: " + Pedido.class.getName());
		}
		return obj.orElse(null);
	}

	public List<Pedido> index() {

		List<Pedido> obj = repo.findAll();
		return obj;
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(ClienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setPedido(obj);
		obj.getPagamento().setEstado(EstadoPagamento.PEDENTE);
		
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
			
		}
		
		obj =  repo.save(obj);
		pgRe.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.busca(ip.getProduto().getId()).getPreco());
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		/*
		 * PARA ENVIAR VIA DEV 
		 * emailService.sendOrderConfirmationHtmlEmail(obj);*/
		return obj;
	}

}

