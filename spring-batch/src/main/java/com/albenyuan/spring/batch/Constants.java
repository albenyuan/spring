package com.albenyuan.spring.batch;


import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final List<String> FILENAMES = new ArrayList<>();


    static {
        FILENAMES.add("/Users/macuser/Desktop/data/1575187276559.csv");
        FILENAMES.add("/Users/macuser/Desktop/data/1575187276559.txt");
        FILENAMES.add("/Users/macuser/Desktop/data/1575187276559.xlsx");
    }


    public static final String CONTEXT_KEY_FILES = "FILES";

    public static final String CONTEXT_KEY_FILE_INDEX = "FILE_INDEX";

    public static final String STANDARD_YES = "YES";

    public static final String STANDARD_NO = "NO";


    public static final String LOOP_FILE_FINISHED = "FINISHED";

    public static final String LOOP_FILE_LOOP = "LOOP";


}
