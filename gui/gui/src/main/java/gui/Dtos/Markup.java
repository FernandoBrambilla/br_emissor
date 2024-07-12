package gui.Dtos;

import java.math.BigDecimal;

public class Markup {
	
	private int id;
	private BigDecimal markup;
	private boolean utilizar;
	
	public Markup(Markup markup) {
		this.id = markup.getId();
		this.markup = markup.getMarkup();
		this.utilizar = markup.isUtilizar();
	}
	
	public Markup() {
		super();
	}

	public Markup(int id, BigDecimal markup, boolean utilizar) {
		this.id = id;
		this.markup = markup;
		this.utilizar = utilizar;
	}

	public int getId() {
		return id;
	}
	public BigDecimal getMarkup() {
		return markup;
	}
	public boolean isUtilizar() {
		return utilizar;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMarkup(BigDecimal markup) {
		this.markup = markup;
	}
	public void setUtilizar(boolean utilizar) {
		this.utilizar = utilizar;
	}
	
}
