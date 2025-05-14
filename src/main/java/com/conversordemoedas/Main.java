package main.java.com.conversordemoedas;

import main.java.com.conversordemoedas.model.ApiResponse;
import main.java.com.conversordemoedas.model.ConversionRecord;
import main.java.com.conversordemoedas.service.CurrencyConverterService;
import main.java.com.conversordemoedas.service.ExchangeRateApiService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    private static final String LOG_FILE_NAME = "conversion_log.txt";

    private static void logConversion(ConversionRecord record) {
        try (FileWriter fw = new FileWriter(LOG_FILE_NAME, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(record.toString());

        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo de log (\"" + LOG_FILE_NAME + "\"): " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateApiService apiService = new ExchangeRateApiService();
        CurrencyConverterService converterService = null;
        Map<String, Double> taxasDisponiveis = null;

        List<ConversionRecord> historicoConversoes = new ArrayList<>();

        System.out.println("Bem-vindo ao Conversor de Moedas!");

        ApiResponse apiResponse = apiService.buscarTaxasDeCambioObjeto("USD");

        if (apiResponse != null && "success".equals(apiResponse.getResult())) {
            taxasDisponiveis = apiResponse.getConversion_rates();
            converterService = new CurrencyConverterService(taxasDisponiveis, apiResponse.getBase_code());

            List<String> moedasPopulares = Arrays.asList(
                    "USD", "EUR", "BRL", "JPY", "GBP",
                    "AUD", "CAD", "CHF", "CNY", "ARS", "CLP", "COP", "BOB"
            );

            System.out.println("\nA conversão está disponível nas seguintes opções (códigos de 3 letras):");
            for (String codigo : moedasPopulares) {
                if (taxasDisponiveis.containsKey(codigo)) {
                    System.out.print(codigo + " ");
                }
            }
            System.out.println("\n(Você pode tentar outros códigos de moeda válidos também)");

        } else {
            System.out.println("Não foi possível carregar as taxas de câmbio. O programa será encerrado.");
            scanner.close();
            return;
        }

        while (true) {
            System.out.println("\n*************************************************");
            System.out.print("Digite o código da moeda de ORIGEM (ex: USD, BRL) ou digite 'HISTORICO' ou 'SAIR': ");
            String userInput = scanner.nextLine().toUpperCase().trim();

            if (userInput.equalsIgnoreCase("SAIR")) {
                break;
            }

            if (userInput.equalsIgnoreCase("HISTORICO")) {
                if (historicoConversoes.isEmpty()) {
                    System.out.println("\n--- Histórico de Conversões ---");
                    System.out.println("Nenhuma conversão realizada ainda.");
                    System.out.println("-----------------------------");
                } else {
                    System.out.println("\n--- Histórico de Conversões ---");
                    for (int i = 0; i < historicoConversoes.size(); i++) {
                        System.out.println((i + 1) + ". " + historicoConversoes.get(i));
                    }
                    System.out.println("-----------------------------");
                }
                continue;
            }

            String moedaOrigem = userInput;

            if (!taxasDisponiveis.containsKey(moedaOrigem)) {
                System.out.println("Comando ou código da moeda de origem inválido. Tente novamente.");
                continue;
            }

            System.out.print("Digite o código da moeda de DESTINO (ex: EUR, ARS): ");
            String moedaDestino = scanner.nextLine().toUpperCase().trim();

            if (!taxasDisponiveis.containsKey(moedaDestino)) {
                System.out.println("Código da moeda de destino inválido ou não disponível. Tente novamente.");
                continue;
            }

            double valorParaConverter = 0;
            boolean valorValido = false;
            while (!valorValido) {
                System.out.print("Digite o VALOR a ser convertido de " + moedaOrigem + " para " + moedaDestino + ": ");
                try {
                    String linhaValor = scanner.nextLine();
                    valorParaConverter = Double.parseDouble(linhaValor.replace(",", "."));
                    if (valorParaConverter < 0) {
                        System.out.println("O valor não pode ser negativo. Tente novamente.");
                    } else {
                        valorValido = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Por favor, digite um número (ex: 100 ou 23.45).");
                }
            }

            double valorConvertido = converterService.converterMoeda(valorParaConverter, moedaOrigem, moedaDestino);

            if (valorConvertido != -1.0) {
                System.out.printf("RESULTADO: %.2f %s equivalem a %.2f %s%n",
                        valorParaConverter, moedaOrigem, valorConvertido, moedaDestino);

                ConversionRecord registro = new ConversionRecord(moedaOrigem, moedaDestino, valorParaConverter, valorConvertido);
                historicoConversoes.add(registro);
                logConversion(registro);

            } else {
                System.out.println("Não foi possível realizar a conversão. Verifique as moedas inseridas.");
            }
            System.out.println("*************************************************");
        }

        System.out.println("\nObrigado por usar o Conversor de Moedas! Saindo...");
        scanner.close();
    }
}