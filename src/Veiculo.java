public class Veiculo {

	private String placa;
	private String modelo;
	private String cor;
	private String marca;
	private Tipo tipoVeiculo;
	private Cliente cliente;

	private Veiculo(String placa, String modelo, String cor, String marca, Tipo tipoVeiculo, Cliente cliente) {
		this.placa = placa;
		this.modelo = modelo;
		this.cor = cor;
		this.marca = marca;
		this.tipoVeiculo = tipoVeiculo;
		this.cliente = cliente;
	}

	Veiculo(Veiculo veiculo) {
		this.placa = veiculo.getPlaca();
		this.modelo = veiculo.getModelo();
		this.cor = veiculo.getCor();
		this.marca = veiculo.getMarca();
		this.tipoVeiculo = veiculo.getTipo();
		this.cliente = veiculo.getCliente();
	}

	public static Veiculo getInstance(String placa, String modelo, String cor, String marca, Tipo tipoVeiculo,
			Cliente cliente) {
		return new Veiculo(placa, modelo, cor, marca, tipoVeiculo, cliente);
	}

	public Cliente getCliente() {
		return Cliente.getInstance(this.cliente.getNome(), this.cliente.getCpf());
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Tipo getTipo() {
		return tipoVeiculo;
	}

	public void setTipo(Tipo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

}
