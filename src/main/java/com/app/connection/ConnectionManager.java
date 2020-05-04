package com.app.connection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ConnectionManager {

    public Optional<Document> getByUrl(String url){
        try{
            Document document = Jsoup.connect(url).get();
            return Optional.of(document);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        return Optional.empty();
    }

}
