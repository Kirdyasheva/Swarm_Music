/**
 * The class describes particle for PSO algorithm
 */
public class Particles {
    /**
     * @value position - the current position of the particle
     * @value velocity - the current velocity of the particle
     */
    double position;
    double velocity;

    /**
     * @link PSO#c1
     * @link PSO#c2
     * @link PSO#m
     */
    double c1;
    double c2;
    double m;

    /**
     * @value r1 - the random number from 0 to 1
     * @value r2 - the random number from 0 to 1
     */
    double r1;
    double r2;

    /**
     * @value myBest - the best distance to a note
     * @value myBestPosition - the best position depends on the best distance to a note
     */
    double myBest;
    double myBestPosition;

    /**
     * The constructor that run the initialize function
     *
     * @param start - the first note that can be generated
     * @param end   - the last note that can be generated
     * @see PSO#c1
     * @see PSO#c2
     * @see PSO#m
     */
    public Particles(double c1, double c2, double m, double start, double end) {
        this.c1 = c1;
        this.c2 = c2;
        this.m = m;
        initialize(start, end);
    }

    /**
     * Sets myBest as infinity.
     * Sets velocity and position as random value in the interval between start and end.
     *
     * @see  #Particles(double, double, double, double, double)
     */
    private void initialize(double start, double end) {
        myBest = Double.POSITIVE_INFINITY;
        velocity = -(Math.random() * (end - start)) + start;
        position = (Math.random() * (end - start)) + start;
    }

    /**
     * Sets new position as sum of the current position and velocity
     */
    public void setPosition() {
        position = position + velocity;
    }

    /**
     * Sets velocity depends on constants, best position of particles and global function.
     *
     * @param globalBest - the note that drew the biggest number of particles
     */
    public void setVelocity(double globalBest) { //firstly update velocity
        velocity = velocity * m + c1 * Math.random() * (myBestPosition - position) + c2 * Math.random() * (globalBest - position);
    }

    /**
     * Setter of best fitness function of the particle
     *
     * @param myBest - new best fitness function of the particle
     */
    public void setMyBest(double myBest) {
        this.myBest = myBest;
    }

    /**
     * Setter of best position of the particle
     *
     * @param bestPosition - new best position of the particle
     */
    public void setMyBestPosition(double bestPosition) {
        myBestPosition = bestPosition;
    }


    /**
     * Recounts the fitness function of the particle
     *
     * @param reference - the array of possible note that can be generated as the next note
     * @return new best fitness function of the particle
     */
    public double updateMyBest(int[] reference) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < reference.length; i++) {
            if (Math.abs(position - (double) reference[i]) < min) {
                min = Math.abs(position - (double) reference[i]);
            }
        }
        return min;
    }

    /**
     * Getter of the current position of the particle
     *
     * @return current position of the particle
     */
    public double getPosition() {
        return position;
    }

    /**
     * Getter of the best fitness function of the particle
     *
     * @return best fitness function of the particle
     */
    public double getMyBest() {
        return myBest;
    }
}
