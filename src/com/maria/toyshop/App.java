package com.maria.toyshop;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
		var totalizator = new ToyTotalizator("1 1 Car", "2 20 Bear", "3 10 Motorcycle");
		totalizator.put("4 10 Bus");
		totalizator.put("5 5 Constructor");
		totalizator.writeToFile();
	}

}
