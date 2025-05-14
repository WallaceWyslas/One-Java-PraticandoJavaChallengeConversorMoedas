package main.java.com.conversordemoedas.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final String CONFIG_FILE = "config.properties";
    private Properties properties;

    public ConfigLoader() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("ERRO CRÍTICO: Não foi possível encontrar o arquivo '" + CONFIG_FILE + "' no classpath (src/main/main.main.resources).");
                System.err.println("Certifique-se de que o arquivo existe e contém sua 'api.key'.");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("ERRO CRÍTICO ao carregar o arquivo de configuração: " + CONFIG_FILE);
            ex.printStackTrace();
        }
    }

    public String getApiKey() {
        String apiKey = properties.getProperty("api.key");
        if (apiKey == null || apiKey.trim().isEmpty()) {
            System.err.println("AVISO: A chave 'api.key' não foi encontrada ou não está configurada corretamente no arquivo " + CONFIG_FILE);
            System.err.println("Por favor, adicione sua chave real ao arquivo para que o programa funcione.");
            return null;
        }
        return apiKey;
    }
}
