package main.java.com.conversordemoedas.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversionRecord {
    private LocalDateTime timestamp;
    private String moedaOrigem;
    private String moedaDestino;
    private double valorOriginal;
    private double valorConvertido;

    public ConversionRecord(String moedaOrigem, String moedaDestino, double valorOriginal, double valorConvertido) {
        this.timestamp = LocalDateTime.now();
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valorOriginal = valorOriginal;
        this.valorConvertido = valorConvertido;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("[%s] %.2f %s -> %.2f %s",
                timestamp.format(formatter),
                valorOriginal,
                moedaOrigem,
                valorConvertido,
                moedaDestino);
    }
}
