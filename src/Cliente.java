import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private long id;
	private String nome;
	private String cpf;
	private List<Veiculo> veiculos;
	private static int geraId;

	private Cliente(String nome, String cpf) {
		veiculos = new ArrayList<Veiculo>();
		id = geraId++;
		this.nome = nome;
		this.cpf = cpf;
	}

	private Cliente(String nome, String cpf, Veiculo veiculo) {
		this(nome, cpf);
		this.veiculos.add(veiculo);
	}

	public Cliente(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.veiculos = cliente.getVeiculos();

	}

	public static Cliente getInstance(String nome, String cpf) {
		if (!CPF.valida(cpf).equals(" "))
			return new Cliente(nome, CPF.valida(cpf));
		return null;
	}

	public static Cliente getInstance(String nome, String cpf, Veiculo veiculo) {
		if (!CPF.valida(cpf).equals(" "))
			return new Cliente(nome, CPF.valida(cpf), veiculo);
		return null;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public List<Veiculo> getVeiculos() {
		ArrayList<Veiculo> temp = new ArrayList<>();
		for (int i = 0; i < veiculos.size(); i++) {
			temp.set(i, new Veiculo(veiculos.get(i)));
		}
		return temp;
	}

	public void addVeiculo(Veiculo veiculo) {
		veiculos.add(veiculo);
	}
}
