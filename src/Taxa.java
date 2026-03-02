public class Taxa {

	private Tipo tipoVeiculo;
	private double valor;

	Taxa(double valor, Tipo tipoVeiculo) {
		this.valor = valor;
		this.tipoVeiculo = tipoVeiculo;
	}

	public Tipo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(Tipo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
