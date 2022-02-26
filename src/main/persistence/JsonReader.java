package persistence;

import model.Item;
import model.ShoppingCart;

import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads shopping cart from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads shopping cart from file and returns it;
    // throws IOException if there is an error that occurs reading data from file
    public ShoppingCart read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject((jsonData));
        return parseShoppingCart(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses shopping cart from JSON object and returns it
    private ShoppingCart parseShoppingCart(JSONObject jsonObject) {
        String name = jsonObject.getString("cartName");
        ShoppingCart sc = new ShoppingCart(name);
        addItems(sc, jsonObject);
        return sc;
    }

    // MODIFIES: sc
    // EFFECTS: parses items from JSON object and adds them to shopping cart
    private void addItems(ShoppingCart sc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(sc, nextItem);
        }
    }

    private void addItem(ShoppingCart sc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double price = jsonObject.getDouble("price");
        Item item = new Item(name, price);
        sc.addToCart(item);
    }

}
