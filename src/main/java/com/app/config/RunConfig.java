package com.app.config;

import com.app.connection.ConnectionManager;
import com.app.processor.MonthlyDocumentProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

import static com.app.file.FileWriting.createFolderInDisk;
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

            IntStream.range(1996, 1998).forEach(ano ->{
                createFolderInDisk(ano+"");
                connectionManager.getByUrl(BASE_URL+ano+"00"+mesToString(1)+"&lng=pt&nrm=iso")
                        .ifPresent(document -> monthlyDocumentProcessor.process(document,ano+"/"+1));
            });

            /*
            // 1998 - 2001
            IntStream.range(1998, 2002).forEach(ano ->{
                createFolderInDisk(ano+"");
                IntStream.range(1, 3).forEach(mes -> {
                    connectionManager.getByUrl(BASE_URL+ano+"00"+mesToString(mes)+"&lng=pt&nrm=iso")
                            .ifPresent(document -> monthlyDocumentProcessor.process(document,ano+"/"+mes));
                });
            });
            */

            /*
            // 2002 - 2004
            IntStream.range(2002, 2005).forEach(ano ->{
                createFolderInDisk(ano+"");
                IntStream.range(1, 5).forEach(mes -> {
                    connectionManager.getByUrl(BASE_URL+ano+"00"+mesToString(mes)+"&lng=pt&nrm=iso")
                            .ifPresent(document -> monthlyDocumentProcessor.process(document,ano+"/"+mes));
                });
            });
            */

            //https://www.scielo.br/scielo.php?script=sci_abstract&pid=S1413-81232002000400015&lng=pt&nrm=iso&tlng=pt
            //https://www.scielo.br/scielo.php?script=sci_abstract&pid=S1413-81232002000400015&lng=pt&nrm=iso&tlng=en
            //https://www.scielo.br/scielo.php?script=sci_arttext&pid=S1413-81232002000400015&lng=pt&nrm=iso&tlng=pt
            //https://www.scielo.br/scielo.php?script=sci_abstract&pid=S1413-81232003000300007&lng=pt&nrm=iso&tlng=pt
            //https://www.scielo.br/scielo.php?script=sci_abstract&pid=S1413-81232003000300007&lng=pt&nrm=iso&tlng=en
            //https://www.scielo.br/scielo.php?script=sci_arttext&pid=S1413-81232003000300007&lng=pt&nrm=iso&tlng=pt
            //https://www.scielo.br/scielo.php?script=sci_abstract&pid=S1413-81232004000200015&lng=pt&nrm=iso&tlng=pt
            //https://www.scielo.br/scielo.php?script=sci_abstract&pid=S1413-81232004000200015&lng=pt&nrm=iso&tlng=en
            //https://www.scielo.br/scielo.php?script=sci_arttext&pid=S1413-81232004000200015&lng=pt&nrm=iso&tlng=pt

            /*
            // 2005 - 2006
            IntStream.range(2005, 2007).forEach(ano ->{
                createFolderInDisk(ano+"");
                IntStream.range(1, 6).forEach(mes -> {
                    connectionManager.getByUrl(BASE_URL+ano+"00"+mesToString(mes)+"&lng=pt&nrm=iso")
                            .ifPresent(document -> monthlyDocumentProcessor.process(document,ano+"/"+mes));
                });
            });
            */

            /*
            //https://www.scielo.br/scielo.php?script=sci_abstract&pid=S1413-81232007000100020&lng=pt&nrm=iso&tlng=en
            //https://www.scielo.br/scielo.php?script=sci_abstract&pid=S1413-81232007000400020&lng=pt&nrm=iso&tlng=en

            createFolderInDisk("2007");
            IntStream.range(1, 8).forEach(mes -> {
                connectionManager.getByUrl(BASE_URL+"200700"+mesToString(mes)+"&lng=pt&nrm=iso")
                        .ifPresent(document -> monthlyDocumentProcessor.process(document,"2007/"+mes));
            });
            */

            /*
            // 2008
            createFolderInDisk("2008");
            IntStream.range(1, 8).forEach(mes -> {
                connectionManager.getByUrl(BASE_URL+"200800"+mesToString(mes)+"&lng=pt&nrm=iso")
                        .ifPresent(document -> monthlyDocumentProcessor.process(document,"2008/"+mes));
            });
            connectionManager.getByUrl(BASE_URL+"200800"+mesToString(9)+"&lng=pt&nrm=iso")
                    .ifPresent(document -> monthlyDocumentProcessor.process(document,"2008/"+9));
            */


            /*
            // 2009
            createFolderInDisk("2009");
            IntStream.range(1, 7).forEach(mes -> {
                connectionManager.getByUrl(BASE_URL+"200900"+mesToString(mes)+"&lng=pt&nrm=iso")
                        .ifPresent(document -> monthlyDocumentProcessor.process(document,"2009/"+mes));
            });
            connectionManager.getByUrl(BASE_URL+"200900"+mesToString(8)+"&lng=pt&nrm=iso")
                    .ifPresent(document -> monthlyDocumentProcessor.process(document,"2009/"+8));
            */

            /*
            // 2010
            createFolderInDisk("2010");
            IntStream.range(1, 10).forEach(mes -> {
                connectionManager.getByUrl(BASE_URL+"201000"+mesToString(mes)+"&lng=pt&nrm=iso")
                        .ifPresent(document -> monthlyDocumentProcessor.process(document,"2010/"+mes));
            });
            */

            /*s
            // 2011
            createFolderInDisk("2011");
            IntStream.range(1, 14).forEach(mes -> {
                connectionManager.getByUrl(BASE_URL+"201100"+mesToString(mes)+"&lng=pt&nrm=iso")
                        .ifPresent(document -> monthlyDocumentProcessor.process(document,"2011/"+mes));
            });
            */

            /*
            // 2012 - 2016
            IntStream.range(2012, 2017).forEach(ano ->{
                createFolderInDisk(ano+"");
                IntStream.range(1, 13).forEach(mes -> {
                    connectionManager.getByUrl(BASE_URL+ano+"00"+mesToString(mes)+"&lng=pt&nrm=iso")
                            .ifPresent(document -> monthlyDocumentProcessor.process(document,ano+"/"+mes));
                });
            });
            */

            /*
            // 2017
            createFolderInDisk("2017");
            IntStream.range(1, 3).forEach(mes -> {
                connectionManager.getByUrl(BASE_URL+"201700"+mesToString(mes)+"&lng=pt&nrm=iso")
                        .ifPresent(document -> monthlyDocumentProcessor.process(document,"2017/"+mes));
            });
            IntStream.range(3, 13).forEach(mes -> {
                connectionManager.getByUrl(BASE_URL+"20170"+mesV2ToString(mes)+"&lng=pt&nrm=iso")
                        .ifPresent(document -> monthlyDocumentProcessor.process(document,"2017/"+mes));
            });
            */

            /*
            // 2018 - 2019
            IntStream.range(2018, 2020).forEach(ano ->{
                createFolderInDisk(ano+"");
                IntStream.range(1, 13).forEach(mes -> {
                    connectionManager.getByUrl(BASE_URL+ano+"00"+mesToString(mes)+"&lng=pt&nrm=iso")
                            .ifPresent(document -> monthlyDocumentProcessor.process(document,ano+"/"+mes));
                });
            });
            */

            /*
            // 2020
            createFolderInDisk("2020");
            IntStream.range(1, 5).forEach(mes -> {
                connectionManager.getByUrl(BASE_URL+"202000"+mesToString(mes)+"&lng=pt&nrm=iso")
                        .ifPresent(document -> monthlyDocumentProcessor.process(document,"2020/"+mes));
            });
            */

        };
    }

    private String mesToString(int mes){
        if(mes<10) return "0"+mes;
        else return ""+mes;
    }

    private String mesV2ToString(int mes){
        if(mes<10) return "02"+mes;
        else return "2"+mes;
    }

}
