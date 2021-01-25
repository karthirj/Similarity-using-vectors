public class SimilarityMeasure {
	public double correlation(double[] a, double[] b) {
		double sx = 0.0;
		double sy = 0.0;
		double sxx = 0.0;
		double syy = 0.0;
		double sxy = 0.0;

		int n = a.length;

		for (int i = 0; i < n; ++i) {
			double x = a[i];
			double y = b[i];

			sx += x;
			sy += y;
			sxx += x * x;
			syy += y * y;
			sxy += x * y;
		}

		double cov = sxy / n - sx * sy / n / n;
		double sigmax = Math.sqrt(sxx / n - sx * sx / n / n);
		double sigmay = Math.sqrt(syy / n - sy * sy / n / n);

		return cov / sigmax / sigmay;
	}
	
	public double cosineSimilarity(double[] a, double[] b) {
		double dotProduct = 0.0;
	    double normA = 0.0;
	    double normB = 0.0;
	    for (int i = 0; i < a.length; i++) {
	        dotProduct += a[i] * b[i];
	        normA += Math.pow(a[i], 2);
	        normB += Math.pow(b[i], 2);
	    }   
	    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}
	
	public double euclideanDist(double[] a, double[] b) {
        double diff_square_sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            diff_square_sum += (a[i] - b[i]) * (a[i] - b[i]);
        }
        return Math.sqrt(diff_square_sum);
    }

	public double jaccardSimilarity(double[] a, double[] b) {
		double dotProduct = 0.0;
	    double normA = 0.0;
	    double normB = 0.0;
	    for (int i = 0; i < a.length; i++) {
	        dotProduct += a[i] * b[i];
	        normA += Math.pow(a[i], 2);
	        normB += Math.pow(b[i], 2);
	    }
		
	    return dotProduct / (Math.pow(normA, 1.0) + Math.pow(normB, 1.0) - dotProduct);
	}
}
