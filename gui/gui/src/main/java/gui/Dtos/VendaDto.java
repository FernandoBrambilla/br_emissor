package gui.Dtos;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

public class VendaDto {
	
	DecimalFormat realFormato = new DecimalFormat("¤ #,###,##0.00");

	private Long id;

	private LocalDateTime dataVenda;

	private ClienteDto cliente;

	private Double desconto;
	
	private Double totalFinal;

	private MeioPagamentoDto meioPgto;
	
	private Double troco;

	private List<ItensVendaDto> itens;

	private UserDto usuario;

	private Boolean status;

	public Long getId() {
		return id;
	}

	public String getDataVenda() {
		return dataVenda.getDayOfMonth() + "/" + dataVenda.getMonthValue() + "/" + dataVenda.getYear() + "  "
				+ dataVenda.getHour() + ":" + dataVenda.getMinute();
	}

	public String getCliente() {
		return cliente.getName();
	}

	public String getItens() {
		String items = "";
		String quantidade = "";
		String iten = "";
		for (ItensVendaDto itensVendaDto : itens) {
			quantidade = String.valueOf(itensVendaDto.getQuantidade());
			iten = itensVendaDto.getProduto().getDescricao();
			items += quantidade + "  x  " + iten + "\n";
		}
		return items;

	}
	
	
	public String getSituacao() {
		if(status) {
			return new String("PAGO");
		}
		else {
			return new String("PENDENTE");
		}
	}


	public void setItens(List<ItensVendaDto> itens) {
		this.itens = itens;
	}

	public String getDesconto() {
		return realFormato.format(desconto);
	}
	
	public Double getDescontoDouble() {
		return desconto;
	}

	public String getValorTotal() {
		Double valor = 0D;
		for (ItensVendaDto itensVendaDto : itens) {
			valor += itensVendaDto.getQuantidade() * itensVendaDto.getProduto().getValorVendas();
			this.totalFinal = valor - desconto;
		}
		return realFormato.format(valor);
	}
	

	public String getTotalFinal() {
		return realFormato.format(totalFinal);
	}
	
	public Double getTotalFinalDouble() {
		return totalFinal;
	}

	public void setTotalFinal(Double totalFinal) {
		this.totalFinal = totalFinal;
	}

	public String getMeioPgto() {
		return meioPgto.getMeio();
	}

	public String getTroco() {
		return realFormato.format(troco);
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	public String getUsuario() {
		return usuario.getFullName();
	}
 
	public Boolean getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public void setMeioPgto(MeioPagamentoDto meioPgto) {
		this.meioPgto = meioPgto;
	}

	public void setUsuario(UserDto usuario) {
		this.usuario = usuario;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
