public class CPF {

    static String valida(String entrada) {
        // Remove qualquer caractere que não seja número
        entrada = entrada.replaceAll("\\D", "");

        if (entrada.length() != 9 && entrada.length() != 11) {
            return " ";
        }

        // Pegamos apenas os 9 primeiros dígitos
        String cpfBase = entrada.substring(0, 9);

        int[] cpf = new int[11];
        for (int i = 0; i < 9; i++) {
            cpf[i] = cpfBase.charAt(i) - '0';
        }

        // Calcular 1º dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += cpf[i] * (10 - i);
        }
        int resto = soma % 11;
        cpf[9] = (resto < 2) ? 0 : 11 - resto;

        // Calcular 2º dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += cpf[i] * (11 - i);
        }
        resto = soma % 11;
        cpf[10] = (resto < 2) ? 0 : 11 - resto;

        // Retornar CPF formatado
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d",
                cpf[0], cpf[1], cpf[2],
                cpf[3], cpf[4], cpf[5],
                cpf[6], cpf[7], cpf[8],
                cpf[9], cpf[10]);
    }
}
