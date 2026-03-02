public class Estacionamento {

	private Data entrada;
	private Data saida;
	private Veiculo veiculo;
	private Vaga vaga;

	private Estacionamento(Data entrada, Data saida, Veiculo veiculo, Vaga vaga) {
		this.entrada = entrada;
		this.saida = saida;
		this.veiculo = veiculo;
		this.vaga = vaga;
	}

	public static Estacionamento getInstance(Data entrada, Data saida, Veiculo veiculo, Vaga vaga) {
		return new Estacionamento(entrada, saida, veiculo, vaga);
	}

	public Data getEntrada() {
		return entrada;
	}

	public Data getSaida() {
		return saida;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

}
