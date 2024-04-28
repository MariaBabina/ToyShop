package com.maria.toyshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

public class ToyTotalizator {
	
	public static final String FILE_NAME = "toy_results.txt";
	
	private int[] ids = new int[0];
    private int[] weights = new int[0];
    private String[] toyNames = new String[0];

    private int weightsSum = 0;

    private Random randomizer = new Random();

    public ToyTotalizator(String ...strs) {
        if(strs.length < 3) {
            throw new RuntimeException("Should be at least 3 strings!");
        }
        for(var str: strs) {
            put(str);
        }
    }

    /**
     * Кладет в коллекцию новую игрушку (в виде строки)
     * @param str
     */
    public void put(String str) {
        var arr = str.split(" ");

        var newId = Integer.valueOf(arr[0]);
        var newWeight = Integer.valueOf(arr[1]);
        var newToyName = arr[2];

        ids = Arrays.copyOf(ids, ids.length + 1);
        weights = Arrays.copyOf(weights, weights.length + 1);
        toyNames = Arrays.copyOf(toyNames, toyNames.length + 1);

        ids[ids.length-1] = newId;
        weights[weights.length-1] = newWeight;
        toyNames[toyNames.length-1] = newToyName;

        weightsSum += newWeight;
    }

    /**
     * Возвращает наиболее вероятную игрушку (ID игрушки)
     *
     * @return ID наиболее верятной игрушки
     */
    public int get() {
        var random = 1 + randomizer.nextInt(weightsSum);
        var i = 0;
        var counter = weights[i];
        while(random > counter) {
            i++;
            counter += weights[i];
        }
        return ids[i];
    }

    public void writeToFile() throws IOException {
        var sb = new StringBuilder();
        for(var i = 0; i < 10; i++) {
            sb.append(get() + "\n");
        }
        Path file = Paths.get(FILE_NAME);
        Files.write(file, sb.toString().getBytes());
    }
	
}
