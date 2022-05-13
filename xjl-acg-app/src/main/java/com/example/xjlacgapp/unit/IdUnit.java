package com.example.xjlacgapp.unit;

import java.util.Random;

public class IdUnit {
    public static String getId() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer(10);

        for (int j = 1; j <= 10; j++) {

            int i = r.nextInt(10);

            if (j == 1 || (j >= 8 && j <= 10)) {
                while (i == 0) {
                    i = r.nextInt(10);
                }
            }

            sb.append(i);
        }
        String id =sb.toString();
        return id;
    }
}
