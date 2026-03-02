import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Patio patio = new Patio();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean menu = true;
		patio.init();

		while (menu) {
			System.out.println();
			System.out.println("Menu de usuarios:\n1 - Atendimento\n2 - Gestao\nDigite outro numero para sair");
			System.out.print("Digite um numero: ");
			int usuario = sc.nextInt();

			switch (usuario) {

				case 1:// ACOES DO ATENDIMENTO:
					System.out.println(
							"\nMenu de opcoes:\n1 - Entrada\n2 - Saida\nDigite outro numero para sair");
					System.out.print("Digite um numero: ");
					int opcao = sc.nextInt();

					switch (opcao) {
						case 1:
							Atendimento.entrada();
							break;

						case 2:
							Atendimento.saida();
							break;

						default:
							break;
					}
					break;

				case 2:
					System.out.println("\nMenu de opcoes:"
							+ "\n1 - Cadastrar Vaga\n2 - Excluir Vaga\n3 - Listar as vagas\n4 - Mudar Taxa"
							+ "\n5 - Listar veiculos\n6 - Alterar dados de veiculo\n7 - Alterar dados de vaga"
							+ "\n8 - Alterar tamanhos do menu" + "\n9 - Operacoes com Clientes");
					System.out.print("Digite um numero: ");
					int opcao2 = sc.nextInt();

					switch (opcao2) {
						case 1:
							Gestao.cadastrarVaga();
							break;
						case 2:
							Gestao.excluirVaga();
							break;
						case 3:
							Gestao.listarVagas();
							break;
						case 4:
							Gestao.mudarTaxa();
							break;
						case 5:
							Gestao.listarVeiculos();
							break;
						case 6:
							Gestao.editarVeiculo();
							break;
						case 7:
							Gestao.editarVaga();
							break;
						case 8:
							Gestao.editarColuna();
							break;
						case 9:
							System.out.print(
									"\n1 - Criar cliente" + "\n2 - Listar Clientes" + "\n3 - Alterar Dados do cliente"
											+ "\n4 - Deletar Cliente");
							System.out.print("Digite um numero: ");
							int opcao3 = sc.nextInt();
							switch (opcao3) {
								case 1:
									Gestao.criarCliente();
									break;
								case 2:
									Gestao.listarClientes();
									break;
								case 3:
									Gestao.alterarCliente();
									break;
								case 4:
									Gestao.deletarCliente();
									break;
								default:
									break;
							}
							break;
						default:
							break;

					}
					break;
				case 3:
					menu = false;
					break;
				default:
					break;
			}
		}

		sc.close();
	}

	class Atendimento {
		static void entrada() {
			System.out.println("\nDigite o tipo de veiculo:\n1 - utilitario\n2 - automovel\n3 - moto");
			System.out.print("Digite um numero: ");
			int tipoVeiculo = sc.nextInt();

			Vaga vagaLivre = null;
			switch (tipoVeiculo) {
				case 1:
					vagaLivre = patio.procurarVaga(Tipo.UTILITARIO);
					break;
				case 2:
					vagaLivre = patio.procurarVaga(Tipo.AUTOMOVEL);
					break;
				case 3:
					vagaLivre = patio.procurarVaga(Tipo.MOTO);
			}
			if (vagaLivre != null) {

				sc.nextLine();
				System.out.print("\nDigite a placa do veiculo: ");
				String placa1 = sc.nextLine();
				if (patio.validaPlaca(placa1)) {
					if (patio.procuraVeiculo(placa1) == null) {
						System.out.println("Placa nao encontrada no sistema, deseja fazer o cadastro dela? S/N");
						if (sc.nextLine().equals("S")) {
							System.out.print("Digite o modelo do veiculo: ");
							String modelo = sc.nextLine();
							System.out.print("Digite a cor do veiculo: ");
							String cor = sc.nextLine();
							System.out.print("Digite a marca do veiculo: ");
							String marca = sc.nextLine();
							System.out.print("Digite o cpf do cliente: ");
							String cpf = sc.nextLine();
							Cliente c1 = null;
							Veiculo v1 = null;
							if (CPF.valida(cpf).equals(" ")) {
								System.out.println("Digite um cpf valido!");
							} else {
								if (patio.repeticaoCPF(CPF.valida(cpf)) == null) {
									System.out.print("Cliente nao encontrado no sistema, iniciando cadastro...");

									c1 = Gestao.criarCliente(cpf);
									v1 = Veiculo.getInstance(placa1, modelo, cor, marca, vagaLivre.getTipo(), c1);
									patio.entrada(v1, vagaLivre);
									patio.add(c1);
									patio.add(v1);

								} else {
									Cliente c = patio.repeticaoCPF(CPF.valida(cpf));
									v1 = Veiculo.getInstance(placa1, modelo, cor, marca, vagaLivre.getTipo(), c);
									c.addVeiculo(v1);
									patio.entrada(v1, vagaLivre);
									patio.add(c1);
									patio.add(v1);
								}
							}
						}
					} else {
						patio.entrada(patio.procuraVeiculo(placa1), vagaLivre);
						patio.add(patio.procuraVeiculo(placa1));
					}
				} else {
					System.out.println("Placa nao esta no modelo mercosul");
				}

			} else { // se retorna -1 é porque não tem vaga
				System.out.println("Sem vagas");
			}

		}

		static void saida() {
			sc.nextLine();
			System.out.print("\nDigite a placa do veiculo: ");
			String placa2 = sc.nextLine();

			double tarifa = patio.saida(placa2);
			System.out.println("Valor da tarifa: R$" + tarifa);

		}
	}

	class Gestao {
		static void cadastrarVaga() {

			System.out.println("Digite o tipo de vaga que deseja:\n1 - utilitario\n2 - automovel\n3 - moto");
			System.out.print("Digite um numero: ");
			int tipo = sc.nextInt();
			patio.cadastrarVaga(tipo);
		}

		static void excluirVaga() {

			System.out.println("\nDigite o numero da vaga: ");
			int numVaga = sc.nextInt();
			patio.excluirVagas(numVaga);
		}

		static void listarVagas() {
			Vaga[] listVagas = patio.listarVagas();
			System.out.println("");
			System.out.printf("%-9s %-12s %-6s\n", "NumVaga", "Tipo", "Estado");
			for (int i = 0; i < listVagas.length; i++) {
				System.out.printf("%-9d %-12s %-6s\n", listVagas[i].getNumero(), listVagas[i].getTipo(),
						listVagas[i].getEstado());
			}
		}

		static void mudarTaxa() {

			System.out.println(
					"Digite o tipo de taxa:\n1 - Taxa tarifa Hora\n2 - Taxa Moto\n3 - Taxa Automovel\n4 - Taxa Utilitario");
			System.out.print("Digite um numero: ");
			int opcao = sc.nextInt();
			System.out.println("Valor: ");
			double novoValor = sc.nextInt();
			double valorAnterior = patio.alterarTaxa(opcao, novoValor);
			if (valorAnterior == 0) {
				System.out.println("Insira um numero de 1 a 4!");
			} else
				System.out.println("Taxa anterior:" + valorAnterior + "\tTaxa atual:" + novoValor);

		}

		static boolean listarVeiculos() {

			System.out.println(
					"Digite o tipo de listagem que deseja:\n1 - Todos os veiculos do dia\n2 - Veiculos estacionados no momento\n3 - Intervalo de tempo");
			System.out.print("Digite um numero: ");
			int opcao = sc.nextInt();

			int[] tamanhoColunas = patio.getTamanhoColunas();

			switch (opcao) {

				// VEICULOS NO DIA
				case 1:
					ArrayList<Estacionamento> registro = patio.listarVeiculosDia();

					System.out.printf("%-" + tamanhoColunas[0] + "s %-" + tamanhoColunas[1]
							+ "s %-" + tamanhoColunas[2] + "s %-" + tamanhoColunas[3]
							+ "s %-" + tamanhoColunas[4] + "s %-" + tamanhoColunas[5]
							+ "s %-" + tamanhoColunas[6] + "s\n",
							"Data Ent", "Hora Ent", "Data Saida", "Hora Saida", "Placa", "Modelo", "NmCliente");

					for (int i = 0; i < registro.size(); i++) {

						if (registro.get(i).getSaida() == null) {
							System.out.printf("%-" + tamanhoColunas[0] + "s %-" + tamanhoColunas[1]
									+ "s %-" + tamanhoColunas[2] + "s %-" + tamanhoColunas[3]
									+ "s %-" + tamanhoColunas[4] + "s %-" + tamanhoColunas[5]
									+ "s %-" + tamanhoColunas[6] + "s\n",
									registro.get(i).getEntrada().getData(),
									registro.get(i).getEntrada().getHora(),
									"-----", "-----", // valores da saida
									registro.get(i).getVeiculo().getPlaca(),
									registro.get(i).getVeiculo().getModelo(),
									registro.get(i).getVeiculo().getCliente().getNome());
						} else {
							System.out.printf("%-" + tamanhoColunas[0] + "s %-" + tamanhoColunas[1]
									+ "s %-" + tamanhoColunas[2] + "s %-" + tamanhoColunas[3]
									+ "s %-" + tamanhoColunas[4] + "s %-" + tamanhoColunas[5]
									+ "s %-" + tamanhoColunas[6] + "s\n",
									registro.get(i).getEntrada().getData(),
									registro.get(i).getEntrada().getHora(), registro.get(i).getSaida().getData(),
									registro.get(i).getSaida().getHora(),
									registro.get(i).getVeiculo().getPlaca(),
									registro.get(i).getVeiculo().getModelo(),
									registro.get(i).getVeiculo().getCliente().getNome());
						}
					}
					break;

				// VEICULOS NO MOMENTO
				case 2:
					registro = patio.listarVeiculosMomento();

					System.out.printf("%-" + tamanhoColunas[0] + "s %-" + tamanhoColunas[1]
							+ "s %-" + tamanhoColunas[2] + "s %-" + tamanhoColunas[3]
							+ "s %-" + tamanhoColunas[4] + "s\n",
							"Data Ent", "Hora Ent", "Placa", "Modelo", "NmCliente");

					for (int i = 0; i < registro.size(); i++) {
						System.out.printf("%-" + tamanhoColunas[0] + "s %-" + tamanhoColunas[1]
								+ "s %-" + tamanhoColunas[2] + "s %-" + tamanhoColunas[3]
								+ "s %-" + tamanhoColunas[4] + "s\n",
								registro.get(i).getEntrada().getData(),
								registro.get(i).getEntrada().getHora(),
								registro.get(i).getVeiculo().getPlaca(),
								registro.get(i).getVeiculo().getModelo(),
								registro.get(i).getVeiculo().getCliente().getNome());
					}
					break;

				// INTERVALO DE TEMPO
				case 3:
					System.out.println(
							"Digite o DIA do INICIO do intervalo no formato: (AAAAMMDD -  ano, mes e dia concatenados");
					System.out.print("Digite um numero: ");
					long diaInicio = sc.nextLong();

					System.out.println(
							"Digite a HORA do INICIO do intervalo no formato: (HHMM - hora e minutos concatenados)");
					System.out.print("Digite um numero: ");
					long horaInicio = sc.nextLong();

					System.out.println(
							"Digite o DIA do FIM do intervalo no formato: (AAAAMMDD -  ano, mes e dia concatenados)");
					System.out.print("Digite um numero: ");
					long diaFim = sc.nextLong();

					System.out.println(
							"Digite a HORA do FIM do intervalo no formato: (HHMM - hora e minutos concatenados)");
					System.out.print("Digite um numero: ");
					long horaFim = sc.nextLong();

					registro = patio.listarVeiculosIntervalo(diaInicio, horaInicio, diaFim, horaFim);

					System.out.printf("%-" + tamanhoColunas[0] + "s %-" + tamanhoColunas[1]
							+ "s %-" + tamanhoColunas[2] + "s %-" + tamanhoColunas[3]
							+ "s %-" + tamanhoColunas[4] + "s %-" + tamanhoColunas[5]
							+ "s %-" + tamanhoColunas[6] + "s\n",
							"Data Ent", "Hora Ent", "Data Saida", "Hora Saida", "Placa", "Modelo", "NmCliente");

					for (int i = 0; i < registro.size(); i++) {

						if (registro.get(i).getSaida() == null) {
							System.out.printf("%-" + tamanhoColunas[0] + "s %-" + tamanhoColunas[1]
									+ "s %-" + tamanhoColunas[2] + "s %-" + tamanhoColunas[3]
									+ "s %-" + tamanhoColunas[4] + "s %-" + tamanhoColunas[5]
									+ "s %-" + tamanhoColunas[6] + "s\n",
									registro.get(i).getEntrada().getData(),
									registro.get(i).getEntrada().getHora(),
									"-----", "-----", // valores da saida
									registro.get(i).getVeiculo().getPlaca(),
									registro.get(i).getVeiculo().getModelo(),
									registro.get(i).getVeiculo().getCliente().getNome());
						} else {
							System.out.printf("%-" + tamanhoColunas[0] + "s %-" + tamanhoColunas[1]
									+ "s %-" + tamanhoColunas[2] + "s %-" + tamanhoColunas[3]
									+ "s %-" + tamanhoColunas[4] + "s %-" + tamanhoColunas[5]
									+ "s %-" + tamanhoColunas[6] + "s\n",
									registro.get(i).getEntrada().getData(),
									registro.get(i).getEntrada().getHora(), registro.get(i).getSaida().getData(),
									registro.get(i).getSaida().getHora(),
									registro.get(i).getVeiculo().getPlaca(),
									registro.get(i).getVeiculo().getModelo(),
									registro.get(i).getVeiculo().getCliente().getNome());
						}
					}

			}
			return true;
		}

		static void editarVeiculo() {

			sc.nextLine();
			System.out.println("Digite a placa do veiculo: ");

			String placa = sc.nextLine();
			if (patio.validaPlaca(placa)) {
				if (patio.procuraVeiculo(placa) != null) {
					System.out.println("1 - Modelo\n2 - Marca\n3 - Cor");
					switch (sc.nextInt()) {
						case 1:
							sc.nextLine();
							System.out.println("Digite o novo modelo:");
							String modelo = sc.nextLine();
							patio.editarVeiculo(placa, 1, modelo);
							break;
						case 2:
							sc.nextLine();
							System.out.println("Digite a nova marca: ");
							String marca = sc.nextLine();
							patio.editarVeiculo(placa, 2, marca);
							break;
						case 3:
							sc.nextLine();
							System.out.println("Digite a nova cor: ");
							String cor = sc.nextLine();
							System.out.println(cor);
							patio.editarVeiculo(placa, 3, cor);
							break;
					}
					System.out.println("Valor alterado com sucesso");
				} else
					System.out.println("Veiculo não encontrado no sistema");
			} else
				System.out.println("Placa invalida");
		}

		static void editarVaga() {

			System.out.println("Digite o indice da vaga que quer mudar: ");
			Vaga v1;
			int indice = sc.nextInt();
			if (patio.alterarVaga(indice) != null) {
				System.out.println("1 - Tipo\n2 - Estado");
				switch (sc.nextInt()) {
					case 1:
						System.out.println("Digite o tipo da vaga:\n1 - automovel\n2 - utilitario \n3 - moto");
						switch (sc.nextInt()) {
							case 1:
								v1 = patio.alterarVaga(indice);
								v1.setEstado(patio.getVagas().get(indice).getEstado());
								v1.setTipo(Tipo.AUTOMOVEL);
								break;
							case 2:
								v1 = patio.alterarVaga(indice);
								v1.setEstado(patio.getVagas().get(indice).getEstado());
								v1.setTipo(Tipo.UTILITARIO);
								break;
							case 3:
								v1 = patio.alterarVaga(indice);
								v1.setEstado(patio.getVagas().get(indice).getEstado());
								v1.setTipo(Tipo.MOTO);
								break;
							default:
								break;
						}
					case 2:
						sc.nextLine();
						System.out.println(
								"Digite o estado em que deseja colocar a vaga:\n1 - ocupado\n2 - livre\n3 - indisponivel");
						sc.nextLine();
						switch (sc.nextInt()) {
							case 1:
								v1 = patio.alterarVaga(indice);
								v1.setEstado(EstadoVaga.OCUPADO);
								v1.setTipo(patio.getVagas().get(indice).getTipo());
								break;
							case 2:
								v1 = patio.alterarVaga(indice);
								v1.setEstado(EstadoVaga.LIVRE);
								v1.setTipo(patio.getVagas().get(indice).getTipo());
								break;
							case 3:
								v1 = patio.alterarVaga(indice);
								v1.setEstado(EstadoVaga.INDISPONIVEL);
								v1.setTipo(patio.getVagas().get(indice).getTipo());
								break;
						}

					default:
						break;
				}
			} else {
				System.out.println("Uma vaga com esse indice nao existe");
			}
		}

		static void editarColuna() {

			int[] temp = new int[7];
			for (int i = 0; i < 7; i++) {
				System.out.println("Digite o valor da coluna " + i);
				temp[i] = sc.nextInt();
			}
			patio.alterarColunasMenu(temp);
		}

		static void criarCliente() {
			System.out.print("O cliente possui um carro? Se sim qual a placa do veiculo, se não possui digite N:");
			sc.nextLine();
			String placa = sc.nextLine();
			Veiculo v1 = null;
			if (!placa.equals("N")) {
				v1 = patio.procuraVeiculo(placa);
				if (v1 == null) {
					System.out.print("Digite uma placa valida");
				}
			}
			System.out.print("Digite o nome do cliente: ");
			String nome = sc.nextLine();
			System.out.print("Digite o CPF do cliente: ");
			String cpf = sc.nextLine();

			if (!placa.equals("N")) {
				patio.add(Cliente.getInstance(nome, cpf, v1));
			}
			patio.add(Cliente.getInstance(nome, cpf));

		}

		static Cliente criarCliente(String cpf) {

			System.out.println("Digite o nome do cliente: ");
			String nome = sc.nextLine();
			return Cliente.getInstance(nome, cpf);

		}

		static void listarClientes() {
			List<Cliente> temp = patio.listarClientes();
			for (int i = 0; i < temp.size(); i++) {
				System.out.println(temp.get(i).getNome() + " " + temp.get(i).getCpf());
			}

		}

		static void alterarCliente() {

			System.out.print("Digite o cpf do cliente: ");
			sc.nextLine();
			String cpf = sc.nextLine();
			if (patio.repeticaoCPF(CPF.valida(cpf)) != null) {
				Cliente c = patio.repeticaoCPF(CPF.valida(cpf)); // Copia total do cliente sem referencias soltas
				System.out.print("\nDigite o novo nome do cliente: ");
				String nome = sc.nextLine();
				System.out.print("\nDigite o novo cpf do cliente: ");
				cpf = sc.nextLine();

				patio.editarRegistro(c, Cliente.getInstance(nome, cpf));

			} else {
				System.out.print("\nDigite um cpf valido!");
			}
		}

		static void deletarCliente() {

			System.out.print("Digite o cpf do cliente: ");
			sc.nextLine();
			String cpf = sc.nextLine();
			if (patio.repeticaoCPF(CPF.valida(cpf)) != null) {
				Cliente c = patio.repeticaoCPF(CPF.valida(cpf)); // Copia total do cliente sem referencias soltas

				patio.editarRegistro(c, Cliente.getInstance("N/A", "000.000.000-00"));

			} else {
				System.out.print("\nDigite um cpf válido!");
			}
		}
	}

}

