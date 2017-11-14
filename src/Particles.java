public class Particles {
    double position;
    double velocity;
    double c1;
    double c2;
    double m;
    double r1;
    double r2;
    double myBest;

    public Particles(double c1, double c2, double m, double start, double end) {
        this.c1 = c1;
        this.c2 = c2;
        this.m = m;
        initialize(start, end);
    }

    private void initialize(double start, double end) {
        myBest = Double.POSITIVE_INFINITY;
        velocity = 0;
        position = (Math.random() * (end - start)) + start;
    }

    public void setPosition() {
        position = position + velocity;
    }

    public void setVelocity(double globalBest) { //firstly update velocity
        velocity = velocity * m + c1 * Math.random() * (myBest - position) + c2 * Math.random() * (globalBest - position);
    }

    public void setMyBest(double myBest) {
        this.myBest = myBest;
    }

    public double updateMyBest(int[] reference) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < reference.length; i++) {
            if (Math.abs(position - reference[i]) < min) {
                min = Math.abs(position - reference[i]);
            }
        }
        return min;
    }

    public double getPosition() {
        return position;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getMyBest() {
        return myBest;
    }
}
