import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ShopService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    // Base URL for the external API
    private static final String SHOP_API_URL = "https://shopapi.cphbusinessapps.dk/shops";

    public ShopService() {
        this.httpClient = HttpClient.newHttpClient(); // Using Java's HttpClient
        this.objectMapper = new ObjectMapper(); // Jackson's ObjectMapper to parse JSON
        objectMapper.registerModule(new JavaTimeModule()); // Handle Java 8 time types like ZonedDateTime
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Dates in ISO format
    }

    // Fetch the shop data from the external URL

        // Create a request to the external URL
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SHOP_API_URL)) // Set the URI of the external API
                .build();

        // Send the request and get the response asynchronously
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // If the response is not successful, throw an exception
        if (response.statusCode() != 200) {
            throw new IOException("Failed to fetch data from the API. HTTP code: " + response.statusCode());
        }

        // Parse the JSON response into a ShopResponse object using ObjectMapper
        return objectMapper.readValue(response.body(), ShopResponse.class);

}
