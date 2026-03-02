public class Vaga {
	
	private int numero;
	private Tipo tipoVaga;
	private EstadoVaga estadoVaga;
	private static int geraNumero;

	private Vaga(Tipo tipoVaga, EstadoVaga estadoVaga) {
		this.numero = geraNumero++;
		this.tipoVaga = tipoVaga;
		this.estadoVaga = estadoVaga;
	}

	Vaga(Vaga vaga) {
		this.numero = vaga.getNumero();
		this.tipoVaga = vaga.getTipo();
		this.estadoVaga = vaga.getEstado();
	}

	public static Vaga getInstance(Tipo tipoVaga, EstadoVaga estadoVaga) {
		return new Vaga(tipoVaga, estadoVaga);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Tipo getTipo() {
		return tipoVaga;
	}

	public void setTipo(Tipo tipo) {
		this.tipoVaga = tipo;
	}

	public EstadoVaga getEstado() {
		return estadoVaga;
	}

	public void setEstado(EstadoVaga estado) {
		this.estadoVaga = estado;
	}

}
