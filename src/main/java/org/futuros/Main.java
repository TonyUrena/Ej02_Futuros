package org.futuros;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        // Ej01
        HttpFuturos.getHttp("https://en.wikipedia.org/wiki/2023_deaths_in_the_United_States");

        // Ej02
        ComprimeZip.comprimeRuta("src/main/resources/testZip", "testZip.zip");

        // Ej03
        List<String> listaWebs = Arrays.asList(
                "https://en.wikipedia.org/wiki/List_of_Glagolitic_manuscripts",
                "https://en.wikipedia.org/wiki/Tawag_ng_Tanghalan_(season_6)",
                "https://en.wikipedia.org/wiki/List_of_Hindi_songs_recorded_by_Asha_Bhosle",
                "https://en.wikipedia.org/wiki/Opinion_polling_for_the_next_United_Kingdom_general_election",
                "https://en.wikipedia.org/wiki/Municipal_history_of_Quebec",
                "https://en.wikipedia.org/wiki/Criminal_proceedings_in_the_January_6_United_States_Capitol_attack",
                "https://en.wikipedia.org/wiki/2023_deaths_in_the_United_States",
                "https://en.wikipedia.org/wiki/List_of_battles_by_geographic_location",
                "https://en.wikipedia.org/wiki/List_of_Everybody,_Sing!_episodes",
                "https://en.wikipedia.org/wiki/Sixth_Labour_Government_of_New_Zealand"
        );
        ComprimeZip.comprimeWebs(listaWebs, "webs10.zip");

    }
}