package com.spring.spring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.spring.domain.Categoria;
import com.spring.spring.domain.Cidade;
import com.spring.spring.domain.Cliente;
import com.spring.spring.domain.Endereco;
import com.spring.spring.domain.Estado;
import com.spring.spring.domain.ItemPedido;
import com.spring.spring.domain.Pagamento;
import com.spring.spring.domain.PagamentoComBoleto;
import com.spring.spring.domain.PagamentoComCartao;
import com.spring.spring.domain.Pedido;
import com.spring.spring.domain.Produto;
import com.spring.spring.domain.enums.EstadoPagamento;
import com.spring.spring.domain.enums.Perfil;
import com.spring.spring.domain.enums.TipoCliente;
import com.spring.spring.repositories.CategoriaRepository;
import com.spring.spring.repositories.CidadeRepository;
import com.spring.spring.repositories.ClienteRepository;
import com.spring.spring.repositories.EnderecoRepository;
import com.spring.spring.repositories.EstadoRepository;
import com.spring.spring.repositories.ItemPedidoRepository;
import com.spring.spring.repositories.PagamentoRepository;
import com.spring.spring.repositories.PedidoRepository;
import com.spring.spring.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagametoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void instantiateTestDataBase() throws ParseException {
		
		

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Ferramentas");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Escolar");
		Categoria cat6 = new Categoria(null, "Medicina");

		Produto prod1 = new Produto(null, "computador", 2000.00);
		Produto prod2 = new Produto(null, "impressora", 800.00);
		Produto prod3 = new Produto(null, "mause", 10.00);

		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));

		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));

		// fim

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

		// Estado e cidade Inserir dados na iniciação do sistema

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Rio de Janeiro");

		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Rio de Janeiro", est3);
		Cidade cid4 = new Cidade(null, "Belford Roxo", est3);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2));
		est3.getCidades().addAll(Arrays.asList(cid3, cid4));

		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3, cid4));
		// fim

		// Cliente e Endereco Inserir dados na iniciação do sistema


		Cliente cli1 = new Cliente(null, "rasec23rj", "rasec23rj@gmail.com", "36378912377", TipoCliente.PessoaFisica, bCryptPasswordEncoder.encode("123"));
		Cliente cli2 = new Cliente(null, "rasec34rj", "rasec34rj@gmail.com", "363782dd377", TipoCliente.PessoaFisica, bCryptPasswordEncoder.encode("123"));
		cli2.addPerfil(Perfil.ADMIN);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "98838393"));
		cli2.getTelefones().addAll(Arrays.asList("27361323", "98838391"));

		Endereco end1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "38220834", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "400", "Sala 800", "Centro", "3877012", cli1, cid2);
		Endereco end3 = new Endereco(null, "Avenida Matos", "401", "Sala 870", "Centro", "3877012", cli2, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		cli2.getEnderecos().addAll(Arrays.asList(end3));


		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2,end3));

		// fim

		// Pedido e Pagamento, Pagamento com boleto, Pagamento com cartão Inserir dados
		// na iniciação do sistema

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM");

		Pedido ped1 = new Pedido(null, sdf.parse("30/01/2019 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/1/2019 11:32"), cli1, end2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PEDENTE, ped2, sdf.parse("30/02/2019 23:59"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagametoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		// fim

		// Item Pedido Inserir dados na iniciação do sistema
		
		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, prod2, 0.00, 1, 4000.00);
		ItemPedido ip3 = new ItemPedido(ped2, prod3, 100.00, 2, 1100.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip2));
		prod3.getItens().addAll(Arrays.asList(ip3));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
