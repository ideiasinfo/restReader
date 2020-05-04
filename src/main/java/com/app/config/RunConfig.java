package com.app.config;

import com.app.connection.ConnectionManager;
import com.app.file.FileWriting;
import com.app.processor.MonthlyDocumentProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

import static com.app.utils.Constants.BASE_URL;

@Configuration
public class RunConfig {

    @Autowired
    private MonthlyDocumentProcessor monthlyDocumentProcessor;

    @Autowired
    private ConnectionManager connectionManager;

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public CommandLineRunner run(){
        return args -> {

            int mes = 4;
            connectionManager
                    .getByUrl(BASE_URL+"201500"+mesToString(mes)+"&lng=pt&nrm=iso")
                    .ifPresent(document -> monthlyDocumentProcessor.process(document,"2015/"+mes));

            /*
            //2020
            FileWriting.createFolder("2020");
            IntStream.range(1, 4).forEach(mes -> {
                connectionManager
                        .getByUrl(BASE_URL+"202000"+mesToString(mes)+"&lng=pt&nrm=iso")
                        .ifPresent(document -> monthlyDocumentProcessor.process(document,"2020/"+mes));
            });

            IntStream.range(2012, 2019).forEach(ano ->{
                FileWriting.createFolder(ano+"");
                IntStream.range(1, 12).forEach(mes -> {
                    connectionManager
                            .getByUrl(BASE_URL+"201900"+mesToString(mes)+"&lng=pt&nrm=iso")
                            .ifPresent(document -> monthlyDocumentProcessor.process(document,ano+"/"+mes));
                });
            });*/

        };
    }

    private String mesToString(int mes){
        if(mes<10) return "0"+mes;
        else return ""+mes;
    }

}
