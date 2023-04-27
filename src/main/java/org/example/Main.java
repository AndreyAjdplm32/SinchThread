package org.example;

import java.util.*;


public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    public static final int ways = 1000;
    public static final int firstOne = 1;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < ways; i++) {
            new Thread (() -> {
                int count = (int) generateRoute("RLRFR", 100).chars().filter(element -> element ==
                        'R').count();

                synchronized (sizeToFreq) {
                    if (sizeToFreq.containsKey(count)) {
                        sizeToFreq.put(count,sizeToFreq.get(count)+1);
                    } else {
                        sizeToFreq.put(count, firstOne);
                    }
                    sizeToFreq.notify();
                }
            }).start();
        }
        Map.Entry <Integer,Integer> max = sizeToFreq.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println("Cамое часто кол-во повторений: " + max.getKey() + ", встречалось: " + max.getValue() + " кол-во раз.") ;
        System.out.println("Другие размеры: ");
        sizeToFreq.
                entrySet().
                stream().
                sorted(Map.Entry.comparingByValue()).
                forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " кол-во раз"));

    }


        public static String generateRoute (String letters,int length){
            Random random = new Random();
            StringBuilder route = new StringBuilder();
            for (int i = 0; i < length; i++) {
                route.append(letters.charAt(random.nextInt(letters.length())));
            }
            return route.toString();
        }
    }
