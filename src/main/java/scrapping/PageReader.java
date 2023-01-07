package scrapping;

import lombok.SneakyThrows;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Scanner;

public class PageReader implements Reader {
    private String url;
    private final DBConnection dbconnection;
    {
        try {
            dbconnection = DBConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public PageReader(String url) {
        this.url = url;
    }
    @SneakyThrows
    public String read() {
        String data = dbconnection.exists(this.url);
        if (data != null) {
            return data;
        }
        URL link = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) link.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        data = new Scanner(connection.getInputStream()).useDelimiter("\n").next();
        dbconnection.save(url, data);
        return data;
    }
}