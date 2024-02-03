package org.futuros;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HttpFuturos {
    public static String getHttp(String url) throws ExecutionException, InterruptedException {

        // Instanciamos un nuevo cliente HTTP pasándole una dirección y el comando GET para hacer
        // una petición al servidor para descargarla.
        HttpClient client = HttpClient.newHttpClient();

        // Petición a uno de los artículos más largos de la wikipedia para provocar una espera perceptible.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET() // GET is default
                .build();

        // Enviamos la petición
        Future<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // Devolvemos el cuerpo de la petición en forma de string
        return futureResponse.get().body();
    }
}
