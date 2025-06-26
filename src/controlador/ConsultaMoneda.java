
package controlador;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import modelo.Moneda;

/**
 *
 * @author leytonflores
 */
public class ConsultaMoneda {

    private static String API_KEY = "987c88bda32269d0c9ecae03";

    public Moneda convertir(String monedaBase, String monedaDestino, double cantidad) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + monedaBase + "/" + monedaDestino + "/" + cantidad + "/");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);

        } catch (Exception e) {
            throw new RuntimeException("Error al convertir la moneda");
        }
    }
}
