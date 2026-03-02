
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Patio {

	private List<Veiculo> veiculos = new ArrayList<>();
	private List<Cliente> clientes = new ArrayList<>();
	private Calendar calendario = Calendar.getInstance();
	private List<Estacionamento> registro = new ArrayList<Estacionamento>();
	private List<Vaga> vagas = new ArrayList<>();
	private int[] tamanhoColunas = new int[7];

	private double taxaHora = 10;
	private List<Taxa> taxa = new ArrayList<>();

	Patio() {
		Taxa temp = new Taxa(0.5, Tipo.MOTO);
		taxa.add(temp);
		temp = new Taxa(0.7, Tipo.AUTOMOVEL);
		taxa.add(temp);
		temp = new Taxa(1, Tipo.UTILITARIO);
		taxa.add(temp);
	}

	public void init() {
		Cliente c1 = Cliente.getInstance("Joao", "378.796.450-01");
		add(c1);
		Veiculo v1 = Veiculo.getInstance("BRA2E19", "Doblo", "Cinza", "Mercedes", Tipo.UTILITARIO, c1);
		add(v1, c1);
		Vaga vg1 = Vaga.getInstance(Tipo.UTILITARIO, EstadoVaga.LIVRE);
		add(vg1);
		entrada(v1, vg1, 2025, 05, 19, 2, 30);

		Cliente c2 = Cliente.getInstance("Carlos", "592.847.360-00");
		add(c2);
		Veiculo v2 = Veiculo.getInstance("PHL4F06", "Parati", "Cinza", "Toyota", Tipo.UTILITARIO, c2);
		add(v2, c2);
		Vaga vg2 = Vaga.getInstance(Tipo.UTILITARIO, EstadoVaga.LIVRE);
		add(vg2);
		entrada(v2, vg2, 2025, 05, 19, 6, 30);

		Cliente c3 = Cliente.getInstance("Gabriel", "013.426.597-40");
		add(c3);
		Veiculo v3 = Veiculo.getInstance("DDA5F23", "Celta", "Cinza", "Chevron", Tipo.AUTOMOVEL, c3);
		add(v3, c3);
		Vaga vg3 = Vaga.getInstance(Tipo.UTILITARIO, EstadoVaga.LIVRE);
		add(vg3);
		entrada(v3, vg3, 2025, 05, 19, 4, 30);

		saida(v1.getPlaca());

		int[] temp = { 10, 10, 10, 10, 10, 10, 10 };
		alterarColunasMenu(temp);
	}

	public boolean validaPlaca(String placa) { // verifica se é uma placa válida
		return placa.matches("^[a-zA-Z]{3}[0-9][A-Za-z0-9][0-9]{2}$");
	}

	// retorna o cliente com tal cpf, se retornar null a pessoa nao foi cadastrada
	// ainda
	public Cliente repeticaoCPF(String cpf) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				return new Cliente(clientes.get(i));
			}
		}
		return null;
	}

	private boolean verificaRepeticao(String placa) { // verifica se o veículo já foi cadastrado

		for (int i = 0; i < veiculos.size(); i++) {
			if (veiculos.get(i) != null) {
				if (veiculos.get(i).getPlaca().equals(placa)) {
					return false;
				}
			}
		}
		return true;
	}

	Vaga procurarVaga(Tipo tipoVaga) {

		for (int i = 0; i < vagas.size(); i++) {
			if (vagas.get(i).getEstado() == EstadoVaga.LIVRE && vagas.get(i).getTipo() == tipoVaga) {
				return new Vaga(vagas.get(i));
			}
		}
		return null;
	}

	public Vaga procurarVaga(Veiculo veiculo) {

		for (int i = 0; i < vagas.size(); i++) {
			if (vagas.get(i).getEstado() == EstadoVaga.LIVRE && vagas.get(i).getTipo() == veiculo.getTipo()) {
				return new Vaga(vagas.get(i)); // retorno uma copia da vaga
			}
		}
		return null;
	}

	// para podermos inserir manualnte a data de entrada
	public void entrada(Veiculo veiculo, Vaga vaga, int ano, int mes, int dia, int hora, int minuto) {
		vaga.setEstado(EstadoVaga.OCUPADO);
		registro.add(Estacionamento.getInstance(inserirData(ano, mes, dia, hora, minuto), null, veiculo, vaga));

	}

	public void entrada(Veiculo veiculo, Vaga vaga) {

		registro.add(Estacionamento.getInstance(inserirData(calendario.get(Calendar.YEAR),
				calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH),
				calendario.get(Calendar.HOUR), calendario.get(Calendar.MINUTE)), null, veiculo, vaga));
	}

	Veiculo procuraVeiculo(String placa) { // procura o veiculo no vetor pela placa e o retorna
		for (int i = 0; i < veiculos.size(); i++) {
			if (veiculos.get(i).getPlaca().equals(placa)) {
				return veiculos.get(i);
			}
		}
		return null;
	}

	double saida(String placa) {
		double custo = 0;
		for (int i = registro.size() - 1; i >= 0; i--) {
			if (registro.get(i).getVeiculo().getPlaca().equals(placa) && registro.get(i).getSaida() == null) {
				vagas.get(registro.get(i).getVaga().getNumero()).setEstado(EstadoVaga.LIVRE);
				editarRegistro(procuraVeiculo(placa), registro.get(i).getEntrada(),
						inserirData(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH + 1),
								calendario.get(Calendar.DAY_OF_MONTH), calendario.get(Calendar.HOUR),
								calendario.get(Calendar.MINUTE)),
						procurarVagaPlaca(placa));
				switch (registro.get(i).getVeiculo().getTipo()) {
					case MOTO:
						custo = taxa.get(0).getValor()
								* (registro.get(i).getSaida().getHoras() - registro.get(i).getEntrada().getHoras())
								+ (registro.get(i).getSaida().getDia() - registro.get(i).getEntrada().getDia()) * 24;
						break;
					case AUTOMOVEL:
						custo = taxa.get(1).getValor()
								* (registro.get(i).getSaida().getHoras() - registro.get(i).getEntrada().getHoras())
								+ (registro.get(i).getSaida().getDia() - registro.get(i).getEntrada().getDia()) * 24;
						break;
					case UTILITARIO:
						custo = taxa.get(2).getValor()
								* (registro.get(i).getSaida().getHoras() - registro.get(i).getEntrada().getHoras())
								+ (registro.get(i).getSaida().getDia() - registro.get(i).getEntrada().getDia()) * 24;
						break;
				}
			}
		}
		return custo;
	}

	Data inserirData(int ano, int mes, int dia, int hora, int minuto) {
		return Data.getInstance(dia, mes, ano, minuto, hora, (ano * 10000) + (mes * 100) + dia, (hora * 100) + minuto,
				dia + "/" + mes + "/" + ano, hora + ":" + minuto);
	}

	void lancarRegistro(Veiculo veiculo, Data entrada, Data saida, Vaga vaga) {
		registro.add(Estacionamento.getInstance(entrada, saida, veiculo, vaga));
	}

	void cadastrarVeiculo(String placa, String modelo, String cor, String marca, Tipo tipoVeiculo) {

		if (validaPlaca(placa) && verificaRepeticao(placa)) {
			veiculos.add(Veiculo.getInstance(placa, modelo, cor, marca, tipoVeiculo, null)); // cliente?

		}
	}

	void cadastrarVaga(int tipo) {

		if (vagas.size() < 26) {
			switch (tipo) {

				case 1:
					vagas.add(Vaga.getInstance(Tipo.UTILITARIO, EstadoVaga.LIVRE));
					break;
				case 2:
					vagas.add(Vaga.getInstance(Tipo.AUTOMOVEL, EstadoVaga.LIVRE));
					break;
				case 3:
					vagas.add(Vaga.getInstance(Tipo.MOTO, EstadoVaga.LIVRE));
					break;
			}

		} else

		{
		}
	}

	void excluirVagas(int numero) {
		if (numero > 0) {
			vagas.set(numero, null);
		}
	}

	Vaga[] listarVagas() {
		Vaga[] listVaga = new Vaga[vagas.size()];
		for (int i = 0; i < vagas.size(); i++) {
			listVaga[i] = new Vaga(vagas.get(i));
		}
		return listVaga;
	}

	double alterarTaxa(int opcao, double valor) {

		double tmp = 0;
		switch (opcao) {
			case 1:
				tmp = taxaHora;
				taxaHora = valor;
			case 2:
				tmp = taxa.get(0).getValor();
				taxa.get(0).setValor(valor);
				break;
			case 3:
				tmp = taxa.get(1).getValor();
				taxa.get(1).setValor(valor);
				break;
			case 4:
				tmp = taxa.get(2).getValor();
				taxa.get(2).setValor(valor);
				break;
			default:
				break;
		}
		return tmp;
	}

	ArrayList<Estacionamento> listarVeiculosDia() {

		ArrayList<Estacionamento> lista = new ArrayList<>();
		int dtAtual = getDataAtual();
		for (int i = 0; i < registro.size(); i++) {
			if (registro.get(i).getEntrada().getFullData() <= dtAtual
					&& (registro.get(i).getSaida() == null || registro.get(i).getSaida().getFullData() >= dtAtual)) {

				lista.add(registro.get(i));
			}
		}
		return lista;
	}

	ArrayList<Estacionamento> listarVeiculosMomento() {

		ArrayList<Estacionamento> lista = new ArrayList<>();
		int dataAtual = getDataAtual();
		int horaAtual = getHoraAtual();

		for (int i = 0; i < registro.size(); i++) {
			if (registro.get(i).getSaida() == null) {
				if (registro.get(i).getEntrada().getFullData() <= dataAtual
						&& registro.get(i).getEntrada().getFullHora() <= horaAtual) {

					lista.add(registro.get(i));
				}
			}
		}
		return lista;

	}

	ArrayList<Estacionamento> listarVeiculosIntervalo(long inicioDia, long inicioHora, long fimDia, long fimHora) {
		ArrayList<Estacionamento> lista = new ArrayList<>();

		for (int i = 0; i < registro.size(); i++) {
			if ((registro.get(i).getEntrada().getFullData() >= inicioDia
					&& registro.get(i).getEntrada().getFullHora() >= inicioHora)
					&& (registro.get(i).getEntrada().getFullData() <= fimDia
							&& registro.get(i).getEntrada().getFullHora() <= fimHora)
					&& ((registro.get(i).getSaida() == null // )
							|| (registro.get(i).getSaida().getFullData() <= fimDia
									&& registro.get(i).getSaida().getFullHora() <= fimHora)))) {
				lista.add(registro.get(i));
			}

		}
		return lista;
	}

	// para inserir a data do computador
	int getDataAtual() {
		int ano = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH) + 1;
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		return (ano * 10000) + (mes * 100) + dia;
	}

	// para inserir a hora do computador
	int getHoraAtual() {
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		int minuto = calendario.get(Calendar.MINUTE);
		return (hora * 100) + minuto;
	}

	void editarVeiculo(String placa, int dado, String valor) {
		for (int i = 0; i < veiculos.size(); i++) {
			if (veiculos.get(i) != null && veiculos.get(i).getPlaca().equals(placa)) {
				switch (dado) {
					case 1:
						veiculos.get(i).setModelo(valor);
						break;
					case 2:
						veiculos.get(i).setMarca(valor);
						break;
					case 3:
						veiculos.get(i).setCor(valor);
					default:
						break;
				}
			}
		}
	}

	Vaga procurarVagaPlaca(String placa) { // Procura em qual vaga o veiculo está estacionado por sua placa
		for (int i = registro.size() - 1; i > 0; i--) {
			if (registro.get(i).getVeiculo().getPlaca().equals(placa)) {
				return registro.get(i).getVaga();
			}
		}
		return null;
	}

	Vaga alterarVaga(int indice) {

		if (indice < vagas.size()) {
			return vagas.get(indice);
		}
		return null;
	}

	Vaga numeroVaga(int indice) {
		for (int i = 0; i < vagas.size(); i++) {
			if (vagas.get(i).getNumero() == indice) {
				return vagas.get(i);
			}
		}
		return null;
	}

	void alterarColunasMenu(int[] a) {
		tamanhoColunas = a;
	}

	List<Vaga> getVagas() {
		return vagas;
	}

	void cadastrarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	List<Cliente> listarClientes() {
		List<Cliente> temp = new ArrayList<>();
		for (int i = 0; i < clientes.size(); i++) {
			temp.add(new Cliente(clientes.get(i)));
		}
		return temp;
	}

	void add(Vaga vaga) {
		vagas.add(vaga);
	}

	void add(Veiculo veiculo, Cliente cliente) {
		cliente.addVeiculo(veiculo);
		veiculos.add(veiculo);
	}

	void add(Veiculo veiculo) {
		veiculos.add(veiculo);
	}

	void add(Cliente cliente) {
		clientes.add(cliente);
	}

	void editarRegistro(Veiculo veiculo, Data entrada, Data saida, Vaga vaga) {
		int indice = procurarRegistro(veiculo);
		registro.set(indice, Estacionamento.getInstance(entrada, saida, veiculo, vaga));
	}

	int procurarRegistro(Veiculo veiculo) {
		for (int i = 0; i < registro.size(); i++) {
			if (registro.get(i).getVeiculo().getPlaca().equals(veiculo.getPlaca())) {
				return i;
			}
		}
		return -1;
	}

	int[] getTamanhoColunas() {
		return tamanhoColunas;
	}

	void setTamanhoColunas(int[] tamanhoColunas) {
		this.tamanhoColunas = tamanhoColunas;
	}

	void editarRegistro(Cliente anterior, Cliente atualizado) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(anterior.getCpf())) {
				clientes.set(i, atualizado);
			}
		}
		for (int i = 0; i < veiculos.size(); i++) {
			if (veiculos.get(i).getCliente().getCpf().equals(anterior.getCpf())) {
				veiculos.get(i).setCliente(atualizado);
			}
		}
	}

}
