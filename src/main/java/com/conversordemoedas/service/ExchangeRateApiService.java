package main.java.com.conversordemoedas.service;

import main.java.com.conversordemoedas.util.ConfigLoader;
import com.google.gson.Gson;
import main.java.com.conversordemoedas.model.ApiResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateApiService {

    private HttpClient httpClient;
    private Gson gson;
    private ConfigLoader configLoader;
    private String apiKey;

    public ExchangeRateApiService()  {
        this.httpClient = HttpClient.newHttpClient() ;
        this.gson = new Gson();
        this.configLoader = new ConfigLoader();
        this.apiKey = configLoader.getApiKey();
    }

    public ApiResponse buscarTaxasDeCambioObjeto(String moedaBase) {
        if (apiKey == null) {
            System.err.println("API Key não está disponível. Não é possível buscar taxas.");
            return null;
        }

        String apiUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + moedaBase;
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();
        try {
            HttpResponse<String> respostaHttp = httpClient.send(requisicao, HttpResponse.BodyHandlers.ofString() );
            int statusCode = respostaHttp.statusCode();
            if (statusCode == 200) {
                String corpoDaRespostaJson = respostaHttp.body();
                return gson.fromJson(corpoDaRespostaJson, ApiResponse.class);
            } else {
                System.err.println("Erro na requisição à API! Status: " + statusCode + ", Corpo: " + respostaHttp.body());
                if (statusCode == 401) {
                    System.err.println("Verifique se sua API Key ('" + apiKey + "') é válida e está corretamente configurada em config.properties.");
                }
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro de comunicação ao buscar taxas de câmbio: " + e.getMessage());
            e.printStackTrace();
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            return null;
        }
    }
}
