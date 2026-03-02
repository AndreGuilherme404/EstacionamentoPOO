class Data {

	private int dia;
	private int mes;
	private int ano;
	private int minutos;
	private int horas;
	private long fullData; // ano, mes e dia concatenados
	private long fullHora; // hora e minutos concatenados
	private String data; // data no formato DD/MM/AAAA
	private String hora;// hora no formato HH:MM

	private Data(int dia, int mes, int ano, int minutos, int horas, long fullData, long fullHora, String data,
			String hora) {

		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.minutos = minutos;
		this.horas = horas;
		this.fullData = fullData;
		this.fullHora = fullHora;
		this.data = data;
		this.hora = hora;
	}

	public static Data getInstance(int dia, int mes, int ano, int minutos, int horas, long fullData, long fullHora,
			String data, String hora) {
		return new Data(dia, mes, ano, minutos, horas, fullData, fullHora, data, hora);
	}

	public int getDia() {
		return dia;
	}

	public int getHoras() {
		return horas;
	}

	public long getFullData() {
		return fullData;
	}

	public long getFullHora() {
		return fullHora;
	}

	public String getData() {
		return data;
	}

	public String getHora() {
		return hora;
	}
}
