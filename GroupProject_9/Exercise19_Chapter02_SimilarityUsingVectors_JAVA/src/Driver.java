import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
import java.math.RoundingMode;

public class Driver {
	private static SimilarityMeasure runClass = new SimilarityMeasure();
	private static DecimalFormat df = new DecimalFormat("#.#####");
	
	public static void main(String[] args) throws IOException {
		PrintStream fileOut = new PrintStream("./SimilarityAndDistancesOutput.txt");
		
		double startTime = 0;
		Scanner keyboard = new Scanner(System.in);
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		System.out.println("------MENU------");
		System.out.println("CHOOSE FROM THE FOLLOWING (ENTER '1' OR '2'):");
		System.out.println("1. CHAPTER 2 EXERCISE 19");
		System.out.println("2. COMPARISON OF ANIMALS");
		
		System.out.print("\nUSER INPUT: ");
		int input = keyboard.nextInt();
		System.setOut(fileOut);
		if(input == 1) {
			startTime = System.currentTimeMillis();
			runVectorProblem();
		} else if (input == 2) {
			startTime = System.currentTimeMillis();
			runAnimalComparison();
		} else {
			System.out.println("Bad input!");
		}
		
		keyboard.close();
		double endTime   = System.currentTimeMillis();
		double totalTime = (endTime - startTime) / 1000.0;
		System.out.println("\n\nTotal Runtime: " + df.format(totalTime) + " second(s)");
	}
	
	public static void runVectorProblem() {
		double[] aX = {1,1,1,1};
		double[] aY = {2,2,2,2};
		
		double[] bX = {0,1,0,1};
		double[] bY = {1,0,1,0};
		
		double[] cX = {0,-1,0,1};
		double[] cY = {1,0,-1,0};
		
		double[] dX = {1,1,0,1,0,1};
		double[] dY = {1,1,1,0,0,1};
		
		double[] eX = {2,-1,0,2,0,-3};
		double[] eY = {-1,1,-1,0,0,-1};
		
		System.out.println("------CHAPTER 2 EXERCISE 19------");
		
		System.out.println("\nPROBLEM 1: " + "x=(1,1,1,1), y=(2,2,2,2)");
		System.out.println("Correlation: " + df.format(runClass.correlation(aX, aY)));
		System.out.println("Cosine Similarity: " + df.format(runClass.cosineSimilarity(aX, aY)));
		System.out.println("Euclidean Distance: " + df.format(runClass.euclideanDist(aX, aY)));
		System.out.println("Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(aX, aY)));
		
		System.out.println("\nPROBLEM 2: " + "x=(0,1,0,1), y=(1,0,1,0)");
		System.out.println("Correlation: " + df.format(runClass.correlation(bX, bY)));
		System.out.println("Cosine Similarity: " + df.format(runClass.cosineSimilarity(bX, bY)));
		System.out.println("Euclidean Distance: " + df.format(runClass.euclideanDist(bX, bY)));
		System.out.println("Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(bX, bY)));
		
		System.out.println("\nPROBLEM 3: " + "x=(0,−1,0,1), y=(1,0,−1,0)");
		System.out.println("Correlation: " + df.format(runClass.correlation(cX, cY)));
		System.out.println("Cosine Similarity: " + df.format(runClass.cosineSimilarity(cX, cY)));
		System.out.println("Euclidean Distance: " + df.format(runClass.euclideanDist(cX, cY)));
		System.out.println("Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(cX, cY)));
		
		System.out.println("\nPROBLEM 4: " + "x=(1,1,0,1,0,1), y=(1,1,1,0,0,1)");
		System.out.println("Correlation: " + df.format(runClass.correlation(dX, dY)));
		System.out.println("Cosine Similarity: " + df.format(runClass.cosineSimilarity(dX, dY)));
		System.out.println("Euclidean Distance: " + df.format(runClass.euclideanDist(dX, dY)));
		System.out.println("Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(dX, dY)));
		
		System.out.println("\nPROBLEM 5: " + "x=(2,−1,0,2,0,−3), y=(−1,1,−1,0,0,−1)");
		System.out.println("Correlation: " + df.format(runClass.correlation(eX, eY)));
		System.out.println("Cosine Similarity: " + df.format(runClass.cosineSimilarity(eX, eY)));
		System.out.println("Euclidean Distance: " + df.format(runClass.euclideanDist(eX, eY)));
		System.out.println("Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(eX, eY)));
	}
	
	public static void runAnimalComparison() throws IOException {
		File animalsFile = new File("animalData.txt");
		Scanner inputFile = new Scanner(animalsFile);
		
		List<String> animalTypes = new ArrayList<String>();
		List<Double> catData = new ArrayList<Double>();
		List<Double> dogData = new ArrayList<Double>();
		List<Double> giraffeData = new ArrayList<Double>();
		List<Double> horseData = new ArrayList<Double>();
		
		double[] catArray = new double[72];
		double[] dogArray = new double[72];
		double[] giraffeArray = new double[72];
		double[] horseArray = new double[72];
		
		animalTypes.add("C"); // CAT
		animalTypes.add("D"); // DOG
		animalTypes.add("G"); // GIRAFFE
		animalTypes.add("H"); // HORSE
		
		while(inputFile.hasNextLine()) {
			String[] tokens = inputFile.nextLine().trim().split("\\s+");
			
			if(tokens[0].equals(animalTypes.get(0)) && catData.size() == 0) { // IS CAT DATA
				while(!tokens[0].equals(animalTypes.get(1)) && !tokens[0].equals(animalTypes.get(2)) && !tokens[0].equals(animalTypes.get(3)) && inputFile.hasNextLine()) {
					tokens = inputFile.nextLine().trim().split("\\s+");
					if(tokens[0].length() > 2) {
						catData.add(Double.parseDouble(tokens[0]));
					}
				}
			} else if (tokens[0].equals(animalTypes.get(1)) && dogData.size() == 0) { // IS DOG DATA
				while(!tokens[0].equals(animalTypes.get(0)) && !tokens[0].equals(animalTypes.get(2)) && !tokens[0].equals(animalTypes.get(3)) && inputFile.hasNextLine()) {
					tokens = inputFile.nextLine().trim().split("\\s+");
					if(tokens[0].length() >= 2) {
						dogData.add(Double.parseDouble(tokens[0]));
					}
				}
			} else if (tokens[0].equals(animalTypes.get(2)) && giraffeData.size() == 0) { // IS GIRAFFE DATA
				while(!tokens[0].equals(animalTypes.get(0)) && !tokens[0].equals(animalTypes.get(1)) && !tokens[0].equals(animalTypes.get(3)) && inputFile.hasNextLine()) {
					tokens = inputFile.nextLine().trim().split("\\s+");
					if(tokens[0].length() > 2) {
						giraffeData.add(Double.parseDouble(tokens[0]));
					}
				}
			} else if (tokens[0].equals(animalTypes.get(3)) && horseData.size() == 0) { // IS HORSE DATA
				while(!tokens[0].equals(animalTypes.get(0)) && !tokens[0].equals(animalTypes.get(1)) && !tokens[0].equals(animalTypes.get(2)) && inputFile.hasNextLine()) {
					tokens = inputFile.nextLine().trim().split("\\s+");
					if(tokens[0].length() > 2) {
						horseData.add(Double.parseDouble(tokens[0]));
					}
				}
			}
		}
		
		for(int i = 0; i < 72; i++){
			catArray[i] = catData.get(i);
			dogArray[i] = dogData.get(i);
			giraffeArray[i] = giraffeData.get(i);
			horseArray[i] = horseData.get(i);
		}
		
		System.out.println("------ANIMAL COMPARISONS------");
		// CAT
		System.out.println("\nCAT TO CAT: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(catArray, catArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(catArray, catArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(catArray, catArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(catArray, catArray)));
		
		System.out.println("CAT TO DOG: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(catArray, dogArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(catArray, dogArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(catArray, dogArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(catArray, dogArray)));
		
		System.out.println("CAT TO GIRAFFE: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(catArray, giraffeArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(catArray, giraffeArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(catArray, giraffeArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(catArray, giraffeArray)));
		
		System.out.println("CAT TO HORSE: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(catArray, horseArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(catArray, horseArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(catArray, horseArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(catArray, horseArray)));
		
		// DOG
		System.out.println("\nDOG TO CAT: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(dogArray, catArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(dogArray, catArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(dogArray, catArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(dogArray, catArray)));
		
		System.out.println("DOG TO DOG: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(dogArray, dogArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(dogArray, dogArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(dogArray, dogArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(dogArray, dogArray)));
		
		System.out.println("DOG TO GIRAFFE: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(dogArray, giraffeArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(dogArray, giraffeArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(dogArray, giraffeArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(dogArray, giraffeArray)));
		
		System.out.println("DOG TO HORSE: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(dogArray, horseArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(dogArray, horseArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(dogArray, horseArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(dogArray, horseArray)));
		
		// GIRAFFE
		System.out.println("\nGIRAFFE TO CAT: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(giraffeArray, catArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(giraffeArray, catArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(giraffeArray, catArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(giraffeArray, catArray)));
		
		System.out.println("GIRAFFE TO DOG: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(giraffeArray, dogArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(giraffeArray, dogArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(giraffeArray, dogArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(giraffeArray, dogArray)));
		
		System.out.println("GIRAFFE TO GIRAFFE: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(giraffeArray, giraffeArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(giraffeArray, giraffeArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(giraffeArray, giraffeArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(giraffeArray, giraffeArray)));
		
		System.out.println("GIRAFFE TO HORSE: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(giraffeArray, horseArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(giraffeArray, horseArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(giraffeArray, horseArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(giraffeArray, horseArray)));
		
		// HORSE
		System.out.println("\nHORSE TO CAT: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(horseArray, catArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(horseArray, catArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(horseArray, catArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(horseArray, catArray)));
		
		System.out.println("HORSE TO DOG: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(horseArray, dogArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(horseArray, dogArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(horseArray, dogArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(horseArray, dogArray)));
		
		System.out.println("HORSE TO GIRAFFE: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(horseArray, giraffeArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(horseArray, giraffeArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(horseArray, giraffeArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(horseArray, giraffeArray)));
		
		System.out.println("HORSE TO HORSE: ");
		System.out.println("\t-Correlation: " + df.format(runClass.correlation(horseArray, horseArray)));
		System.out.println("\t-Cosine Similarity: " + df.format(runClass.cosineSimilarity(horseArray, horseArray)));
		System.out.println("\t-Euclidean Distance: " + df.format(runClass.euclideanDist(horseArray, horseArray)));
		System.out.println("\t-Jaccard Coefficient: " + df.format(runClass.jaccardSimilarity(horseArray, horseArray)));
		
		inputFile.close();
	}
}
