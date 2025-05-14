package main.java.com.conversordemoedas.service;

import java.util.Map;

public class CurrencyConverterService {
    private Map<String, Double> taxasDeCambio;
    private String moedaBaseDoMapa;

    public CurrencyConverterService(Map<String, Double> taxasDeCambio, String moedaBaseDoMapa) {
        if (taxasDeCambio == null || taxasDeCambio.isEmpty()) {
            throw new IllegalArgumentException("O mapa de taxas de câmbio não pode ser nulo ou vazio.");
        }
        if (moedaBaseDoMapa == null || moedaBaseDoMapa.trim().isEmpty()) {
            throw new IllegalArgumentException("A moeda base do mapa de taxas não pode ser nula ou vazia.");
        }

        this.taxasDeCambio = taxasDeCambio;
        this.moedaBaseDoMapa = moedaBaseDoMapa;
    }

    public double converterMoeda(double valorOpcional, String moedaOrigem, String moedaDestino) {
        if (!taxasDeCambio.containsKey(moedaOrigem)) {
            System.err.println("Erro: Moeda de origem '" + moedaOrigem + "' não encontrada nas taxas de câmbio fornecidas.");
            return -1.0;
        }
        if (!taxasDeCambio.containsKey(moedaDestino)) {
            System.err.println("Erro: Moeda de destino '" + moedaDestino + "' não encontrada nas taxas de câmbio fornecidas.");
            return -1.0;
        }

        double taxaOrigemRelativaABase = taxasDeCambio.get(moedaOrigem);
        double taxaDestinoRelativaABase = taxasDeCambio.get(moedaDestino);

        double valorNaMoedaBase = valorOpcional / taxaOrigemRelativaABase;

        double valorConvertido = valorNaMoedaBase * taxaDestinoRelativaABase;

        return valorConvertido;
    }
}
