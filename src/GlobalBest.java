/**
 * The function of the global best of the fitness function
 */
public class GlobalBest {
    /**
     * @value position - the note that has the biggest number of particle around
     */
    private int position;

    /**
     * The constructor that generates the value in interval from lowest to highest note
     * Used to generate the first best note
     *
     * @param reference - the array with possible notes that can be generated as the next note
     */
    public GlobalBest(int[] reference) {
        do {
            position = (int) (Math.random() * (reference[reference.length - 1] - reference[0] + 1) + reference[0]);
        } while (!inReference(reference, position));
    }

    /**
     * @param reference - the array with possible notes that can be generated as the next note
     * @param position  - checks if the position in the interval between lowest and highest note
     * @return true if the position in the interval, false otherwise
     */
    private boolean inReference(int[] reference, int position) {
        for (int i = 0; i < reference.length; i++) {
            if (reference[i] == position) {
                return true;
            }
        }
        return false;
    }

    public int countGlobal(Particles[] p, int[] reference) {
        int[] a = new int[reference.length];
        for (int i = 0; i < p.length; i++) {
            double min = Double.POSITIVE_INFINITY;
            int note = 0;
            for (int j = 0; j < reference.length; j++) {
                if (Math.abs(p[i].getPosition() - reference[j]) < min) {
                    min = Math.abs(p[i].getPosition() - reference[j]);
                    note = j;
                }
            }
            a[note]++;
        }
        int max = 0;
        int note = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                note = i;
            }
        }
        position = reference[note];
        return reference[note];
    }

    public int getPosition() {
        return position;
    }
}
