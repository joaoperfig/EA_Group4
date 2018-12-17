package agh.ea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	private static final int[] SEQUENCE_LENGTHS = { 51, 67, 77, 83, 95 };
	
	private static final int ALGORITHM_CYCLES = 5;

	private static final long DURATION_SECONDS = 600;

	private static final int MAX_QUEUE_SIZE = 10000;

	public static void main(String[] args) {
		XLastovka xl = new XLastovka();
		
		
		for (int sequenceLength: SEQUENCE_LENGTHS) {
			for (int cycle = 1; cycle <= ALGORITHM_CYCLES; cycle++) {
				String fileDirectory = "./output/length" + sequenceLength; 
				String fileName = "/cycle" + cycle + ".txt";
				Path path = Paths.get(fileDirectory);
				if (Files.notExists(path)) {
					try {
						Files.createDirectories(path);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.exit(-1);
					}
				}
				
				try (PrintStream fileOut = new PrintStream(fileDirectory + fileName)) {
					System.setOut(fileOut);
					System.out.println("L=" + sequenceLength + ", " + "T=" + DURATION_SECONDS + "s, Q=" + MAX_QUEUE_SIZE);
					ISequence bestSequence = xl.generate(sequenceLength, DURATION_SECONDS, MAX_QUEUE_SIZE);
					System.out.println(bestSequence);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		}
	}
}
