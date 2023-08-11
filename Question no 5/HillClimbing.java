// 5a)
// Implement hill climbing algorithm


import java.util.Random;

public class HillClimbing {

    // The function to be optimized (you can replace this with your own function)
    private static double objectiveFunction(double x) {
        return -x * x + 5 * x - 6;  // Example function: -x^2 + 5x - 6
    }

    // Hill Climbing algorithm
    private static double hillClimbing(double start, double stepSize, int maxIterations) {
        double currentSolution = start;
        double currentObjective = objectiveFunction(currentSolution);

        // Initialize a random number generator
        Random random = new Random();

        // Perform hill climbing iterations
        for (int i = 0; i < maxIterations; i++) {
            // Generate a random direction (up or down)
            boolean moveUp = random.nextBoolean();

            // Calculate the new solution
            double newSolution = currentSolution + (moveUp ? stepSize : -stepSize);

            // Calculate the objective function value at the new solution
            double newObjective = objectiveFunction(newSolution);

            // If the new objective is better, move to the new solution
            if (newObjective > currentObjective) {
                currentSolution = newSolution;
                currentObjective = newObjective;
            }
        }

        return currentSolution;
    }

    public static void main(String[] args) {
        double startSolution = 0.0; // Starting point for hill climbing
        double stepSize = 0.1;      // Size of each step
        int maxIterations = 100;    // Maximum number of iterations

        double peak = hillClimbing(startSolution, stepSize, maxIterations);
        double peakValue = objectiveFunction(peak);

        System.out.println("Found peak solution at x = " + peak);
        System.out.println("Objective value at peak: " + peakValue);
    }
}


