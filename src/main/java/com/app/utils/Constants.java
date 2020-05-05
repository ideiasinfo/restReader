package com.app.utils;

import java.nio.file.Paths;
import java.util.Map;

public class Constants {

    public static final String TXT_EXTENSION = ".txt";

    public static final String EMPTY = "";
    public static final String SPACE = " ";

    public static final String URL_PATH = "http://www.scielo.br/scielo.php?script=";
    public static final String BASE_URL = "https://www.scielo.br/scielo.php?script=sci_issuetoc&pid=1413-8123";

    public static final String RESOURCES_PATH = Paths.get(".").normalize().toAbsolutePath() + "/src/main/resources/";

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static final String LINK_ATTR = "href";
    public static final String LINK_CCS_DEF = "a";

    public static final String ABSTRACT_KEY = "sci_abstract";
    public static final String ARTICLE_KEY = "sci_arttext";

    public static final Map<String,String> FRONT_MAP = Map.of("span",SPACE,"p",SPACE,"a", LINE_SEPARATOR, "sup", SPACE);
    public static final Map<String,String> BODY_MAP = Map.of("span",SPACE,"p",SPACE,"a", SPACE, "em", SPACE);
    public static final Map<String,String> BACK_MAP = Map.of("span",SPACE,"p",SPACE,"em", SPACE);

    public static final Map<String,String> OLD_BODY_MAP = Map.of("span",SPACE,"p",SPACE,"a", SPACE, "em", SPACE, "font", SPACE, "b", SPACE);

}
