import scala.collection.JavaConversions._
import java.io

object Similarities {
	def main(args: Array[String]): Unit = {
			val scan: java.util.Scanner = new java.util.Scanner(System.in)
					println("Enter vector size (if input vector is x=(1,1,1,1), then size is 4):")
					val size: Int = java.lang.Integer.parseInt(scan.nextLine())
					println("Enter Input vector 1 (each value innew line):")


					val input1: Array[Int] = Array.ofDim[Int](size)
					for (i <- 0 until input1.length) {
						input1(i) = scan.nextLine().toInt
					}
					for (i <- 0 until input1.length) {
						System.out.print(input1(i) + "\n")
					}
					println("Enter Input vector 2:")

					val input2: Array[Int] = Array.ofDim[Int](size)
					for (i <- 0 until input2.length) {
						input2(i) = scan.nextLine().toInt
					}
					for (i <- 0 until input2.length) {
						System.out.print(input2(i) + "\n")
					}


					    var result1=0.0
							var result2=0.0
							var result3=0.0
							var result4=0.0


							result1 = cosineSimilarity(input1, input2)

							result2 = correlation(input1, input2)

							result3 = jaccard(input1, input2)

							result4 = euclideanDistance(input1, input2)

							println("Cosine Similarity: "+result1)
							println("Correlation Similarity: "+result2)
							println("Jaccard Similarity: "+result3)
							println("Euclidean Distance: "+result4)

	}

	def euclideanDistance(xs: Array[Int], ys: Array[Int]) = {
			math.sqrt((xs zip ys).map { case (x,y) => math.pow(y - x, 2) }.sum)
	}

	def jaccard[A](a: Array[Int], b: Array[Int]): Double = {
			require(a.size == b.size)
			dotProduct(a, b)/((magnitude(a)*magnitude(a)) + (magnitude(b)*magnitude(b)) - dotProduct(a, b))

	} 

	def correlation(xs: Array[Int], ys: Array[Int]): Double = {
			var sx: Double = 0.0
					var sy: Double = 0.0
					var sxx: Double = 0.0
					var syy: Double = 0.0
					var sxy: Double = 0.0
					val n: Int = xs.length
					for (i <- 0 until n) {
						    val x: Double = xs(i)
								val y: Double = ys(i)
								sx += x
								sy += y
								sxx += x * x
								syy += y * y
								sxy += x * y
					}
	val cov: Double = sxy / n - sx * sy / n / n
			val sigmax: Double = Math.sqrt(sxx / n - sx * sx / n / n)
			val sigmay: Double = Math.sqrt(syy / n - sy * sy / n / n)
			cov / sigmax / sigmay
	}


	def cosineSimilarity(x: Array[Int], y: Array[Int]): Double = {
			require(x.size == y.size)
			dotProduct(x, y)/(magnitude(x) * magnitude(y))
	}


	def dotProduct(x: Array[Int], y: Array[Int]): Int = {
			(for((a, b) <- x zip y) yield a * b) sum
	}


	def magnitude(x: Array[Int]): Double = {
			math.sqrt(x map(i => i*i) sum)
	}
}

