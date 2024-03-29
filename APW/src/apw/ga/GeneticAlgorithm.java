/*
 *  Copyright (c) 2009, Wrocław University of Technology
 *  All rights reserved.
 *  Redistribution  and use in source  and binary  forms,  with or
 *  without modification,  are permitted provided that the follow-
 *  ing conditions are met:
 * 
 *   • Redistributions of source code  must retain the above copy-
 *     right  notice, this list  of conditions and  the  following
 *     disclaimer.
 *   • Redistributions  in binary  form must  reproduce the  above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the  documentation and/or other mate provided
 *     with the distribution.
 *   • Neither  the name of the  Wrocław University of  Technology
 *     nor the names of its contributors may be used to endorse or
 *     promote products derived from this  software without speci-
 *     fic prior  written permission.
 * 
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRI-
 *  BUTORS "AS IS" AND ANY  EXPRESS OR IMPLIED WARRANTIES, INCLUD-
 *  ING, BUT NOT  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTA-
 *  BILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 *  NO EVENT SHALL THE COPYRIGHT HOLDER OR  CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT,  INCIDENTAL, SPECIAL,  EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCURE-
 *  MENT OF SUBSTITUTE  GOODS OR SERVICES;  LOSS OF USE,  DATA, OR
 *  PROFITS; OR BUSINESS  INTERRUPTION) HOWEVER  CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *  TORT (INCLUDING  NEGLIGENCE  OR  OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSI-
 *  BILITY OF SUCH DAMAGE.
 */
package apw.ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Random;

/**
 * <p>Simple Genetic Algorithm implementation. The GA handles
 * binary coded numbers (both real and integer) and a fitness
 * function of any numer of arguments. </p>
 *
 * <p>This class cannot be directly instantiated - use
 * {@link GeneticAlgorithm#defineGenotype() } method to obtain
 * factory builder. Then call <code>endDefinition()</code> to
 * get an instance of GeneticAlgorithm.</p>
 *
 * <p>Example use:
 * <pre>
 * Double [] solution = 
 * GeneticAlgorithm.
 *   defineGenotype().          // Begin genotype definition
 *     numeric(2, true, -1, 1). // define 2 numeric genes Gray
 *     numeric(2, true, -1, 1). // coded on 2 bits in range (-1,1)
 *   endDefinition().           // genotype definition end
 *   // Genotype defined, proceed to
 *   // GA simulation parameter definition
 *   fitnessFunction(ff).       // use defined fitness function
 *   fittestCallback(dc).       // collect intermediate values
 *   crossoverProb(0.9d).       // crossover probability
 *   mutationProb(0.1d).        // mutation probability
 *   populationSize(10).        // population size
 *   // Simulation defined, now evolve
 *               evolve(40);    // evolve 40 generations
 * </pre>
 * </p>
 *
 * <p>The factory builder pattern was used to help defining all GA
 * parameters in a simple, concise and self explanatory way.
 * Additionally this design ensured that GeneticAlgorithm instance
 * is always properly defined. The factory will prevent instantiation
 * and throw exceptions if GA parameters are not properly defined.</p>
 *
 * @author Greg Matoga <greg dot matoga at gmail dot com>
 */
public final class GeneticAlgorithm {

    int populationSize;
    double crossoverProb, mutationProb;
    /** genome length */
    int bits;
    Chromosome[] p;
    Chromosome[] t;
    boolean[] Gray;
    int[] pos, len;
    Double[] min, max;

    /**
     * Enocdes a number from numeric [min, max]
     *
     * @param c chromosome
     * @param d number to encode
     * @param i geneInteger number
     */
    public void encode(Chromosome c, double d, int i) {
        // normalize to numeric [0, 1]
        d -= min[i];
        d /= max[i];

        // Max no of bits
        int m = 1 << len[i];

        // now the value goes in numeric [0, max no of bits]
        int n = (int) (m * d);

        encode(c.b, n, Gray[i], pos[i], len[i]);
    }

    /**
     * Encode number <code>n</code> at location <code>i</code>
     *
     * @param c chromosome
     * @param n number to be encoded
     * @param i geneInteger index
     */
    public void encode(Chromosome c, int n, int i) {
        encode(c.b, n, Gray[i], pos[i], len[i]);
    }

    /**
     * Decode geneInteger with index <code>i</code>
     *
     * @param n number to be encoded
     * @param i geneInteger index
     */
    public int decodeInt(Chromosome c, int i) {
        return decode(c.b, Gray[i], pos[i], len[i]);
    }

    /**
     * Decode geneInteger with index <code>i</code>
     *
     * @param b a bitset - chromosome
     * @param n number to be encoded
     * @param i geneInteger index
     */
    public double decodeDouble(Chromosome c, int i) {
        int n = decode(c.b, Gray[i], pos[i], len[i]);
        double d = (double) n / (double) (1 << len[i]);
        return min[i] + d * max[i];
    }

    /**
     * more appropriately distributed bit version of encode (theres no sign bit,
     * what helps preserve mutation it's real properties).
     *
     * @param b a bitset - chromosome
     * @param n an integer number to encode
     * @param Gray use Gray coding
     * @param signed coding allows negative values
     * @param start adress of coding string in bitset
     * @param len bit number used to code
     */
    void encode(BitSet b,
            int n,
            boolean Gray,
            int start, int N) {

        // are there enough bits to represent num?
        if (1 << N <= n) // 2^b <= num?
            exc("Not enough bits to store the number " + n + " (max=" + (1 << N) + ").");

        // Gray'ize
        if (Gray) n = btg(n);

        // actual encoding - LSB first
        for (int j = 0; j < N; j++) {
            b.set(start + j, n % 2 != 0);
            n /= 2;
        }
    }

    /**
     * Decode a number from BitSet.
     *
     * @param b a bitset - chromosome
     * @param Gray use Gray coding
     * @param signed coding allows negative values
     * @param start adress of coding string in bitset
     * @param len bit number used to code
     * @return decoded number
     */
    int decode(
            BitSet b,
            boolean Gray,
            int start, int N) {
        // decode starting with LSB
        int n = 0, k = 1;
        for (int j = start; j < start + N; j++) {
            if (b.get(j)) n += k;
            k <<= 1;
        }

        // decode Gray code
        if (Gray) n = gtb(n);

        return n;
    }

    // Utilities
    /**
     * IllegalArgumentException thrower
     * @param s message
     */
    private static final void exc(String s) {
        throw new IllegalArgumentException(s);
    }

    /**
     * Convert Gray code to straight representations.
     *
     * @parameter n Gray coded number
     * @result normal (straight) representation
     */
    static int gtb(int n) {
        for (int i = (n >> 1); i != 0; i >>= 1)
            n ^= i;
        return n;
    }

    /**
     * Convert binary string to Gray code.
     *
     * @parameter n number to encode
     * @result Gray encoded representation
     */
    static int btg(int n) {
        return n ^ (n >> 1);
    }

    private GeneticAlgorithm() {
    }

    public static final GenotypeBuilder defineGenotype() {
        return new GenotypeBuilder();
    }

    public Iterator<Chromosome> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Chromosome[] makeRandomPopulation() {
        Chromosome[] cr = new Chromosome[populationSize];
        BitSet bs;
        Random r = new Random();
        for (int i = 0; i < populationSize; i++) {
            bs = new BitSet(bits);
            for (int j = 0; j < bits; j++)
                bs.set(j, r.nextBoolean());
            cr[i] = new Chromosome(bs);
        }
        return cr;
    }

    public static class GenotypeBuilder {

        public static final int DEFAULT_POPULATION_SIZE = 20;
        public static final double DEFAULT_MUTATION_PROB = 0.01d;
        public static final double DEFAULT_CROSSOVER_PROB = 0.9d;

        public GenotypeBuilder() {
        }
        private int populationSize = DEFAULT_POPULATION_SIZE;
        private double mutationProb = DEFAULT_MUTATION_PROB;
        private double crossoverProb = DEFAULT_CROSSOVER_PROB;
        ArrayList<Boolean> grays = new ArrayList();
        ArrayList<Boolean> sign = new ArrayList();
        ArrayList<Integer> pos = new ArrayList();
        ArrayList<Integer> len = new ArrayList();
        ArrayList<Double> min = new ArrayList();
        ArrayList<Double> max = new ArrayList();
        // endDefinition helper values
        int no = 0;
        /** not what you think */
        int cum = 0;

        /**
         * Ends genotype definitions and returns properly defined and ready to
         * run {@link GeneticAlgorithm}.
         * @return configured GeneticAlgorithm
         */
        public GeneticAlgorithm endDefinition() {
            if (no < 1)
                throw new IllegalStateException("Genotype not defined.");
            GeneticAlgorithm c = new GeneticAlgorithm();
            c.Gray = new boolean[no];
            c.pos = new int[no];
            c.len = new int[no];
            c.max = new Double[no];
            c.min = new Double[no];
            c.crossoverProb = crossoverProb;
            c.mutationProb = mutationProb;

            // endDefinition population array
            c.populationSize = populationSize;
            c.p = c.makeRandomPopulation();
            // initialize chromosome information
            c.bits = cum;
            int i = 0;
            for (Boolean b : grays)
                c.Gray[i++] = b;
            i = 0;
            for (Integer p : pos)
                c.pos[i++] = p;
            i = 0;
            for (Integer p : len)
                c.len[i++] = p;
            i = 0;
            for (Double d : max)
                c.max[i++] = d;
            i = 0;
            for (Double d : min)
                c.min[i++] = d;
            return c;
        }

        /**
         * Adds gene to genotype definition. Decoded value will be of type
         * <code>Integer</code>. Parameters are used to define number
         * of bits used and wheter or not use Gray coding.
         * 
         * @param bits no of bits, determines gene length AND max value
         * @param Gray use Gray coding
         * @return GenotypeBuilder for chain calls
         */
        public GenotypeBuilder integer(int bits, boolean Gray) {
            if (bits < 1) exc("One bit is minimum");
            grays.add(Gray);
            pos.add(cum);
            len.add(bits);
            min.add(null);
            max.add(null);
            cum += bits;
            no++;
            return this;
        }

        /**
         * Adds gene to genotype definition. Decoded value will be of type
         * <code>Double</code>. Parameters are used to define range, number
         * of bits used and wheter or not use Gray coding.
         *
         * @param bits how many bits are used to store coded gene
         * @param Gray use Gray coding
         * @param minimum minimum possible coded value
         * @param maximum maximum possible coded value
         * @return GenotypeBuiler for chain calls
         */
        public GenotypeBuilder numeric(int bits, boolean Gray, double minimum, double maximum) {
            if (minimum > maximum) exc("Minimum greater than maximum.");
            if (bits < 1) exc("One bit is minimum");
            grays.add(Gray);
            pos.add(cum);
            len.add(bits);
            min.add(minimum);
            max.add(maximum);
            cum += bits;
            no++;
            return this;
        }
    }

    void trashTables() {
        System.out.println("Grays " + Arrays.toString(Gray));
        System.out.println("Lengs " + Arrays.toString(len));
        System.out.println("Posss " + Arrays.toString(pos));
    }
    FitnessFunction ff;

    public GeneticAlgorithm fitnessFunction(FitnessFunction ff) {
        this.ff = ff;
        return this;
    }

    public interface FittestCallback {

        public void fittest(double f, double m);
    };
    FittestCallback fc;

    public GeneticAlgorithm fittestCallback(FittestCallback fc) {
        this.fc = fc;
        return this;
    }

    public Object[] evolve(int generations) { /*
        1.   [Start] Generate random population of n chromosomes (suitable solutions for the problem)
        2. [Fitness] Evaluate the fitness f(x) of each chromosome x in the population
        3. [New population] Create a new population by repeating following steps until the new population is complete
        1.   [Selection] Select two parent chromosomes from a population according
        to their fitness (the better fitness, the bigger chance to be selected)
        2.   [Crossover] With a crossover probability cross over the parents to form a new offspring
        (children). If no crossover was performed, offspring is an exact copy of parents.
        3.   [Mutation] With a mutation probability mutate new offspring at each locus (position in chromosome).
        4.   [Accepting] Place new offspring in a new population
        4. [Replace] Use new generated population for a further run of algorithm
        5. [Test] If the end condition is satisfied, stop, and return the best solution in current population
        6. [Loop] Go to step 2 */
        // declare parent populations.

        Chromosome mother, father;
        Chromosome[] tc;
        t = new Chromosome[populationSize];
        Random r = new Random();
        Chromosome fittest = null;


        for (int i = 0; i < generations; i++) {
            int start = 0;
            // evaluate fitness foreach chromosome
            double maxF = Double.MIN_VALUE, sum = 0;
            fittest = null;
            for (Chromosome c : Arrays.asList(p)) {

                c.updateFitness();
                sum += c.f;
                if (c.f > maxF) {
                    maxF = c.f;
                    fittest = c;

                }
            }
            if (fc != null) fc.fittest(maxF, sum / p.length);
            if (fittest != null) {
                start = 1;
                t[0] = new Chromosome((BitSet) fittest.b.clone());
            }


            // with fitness info we can initiate selection mechanism
            DistrGenerator dg = new DistrGenerator(p);

            int k = start;
            BitSet c1, c2, tmp;
            while (k < t.length) {
                // next lines are equal to roulette based selection
                mother = select(dg);
                father = select(dg);

                c1 = mother.b;
                c2 = father.b;

                // ALARM: Abstract the code for crossover
                if (r.nextDouble() <= crossoverProb) {
                    // a really messy way to do cross over
                    c1 = new BitSet(bits);
                    c2 = new BitSet(bits);
                    int idx = (int) (r.nextDouble() * bits);
                    // nowai - algorithm copying bit by bit
                    // i'm certain this could be done using
                    // functions that operate on whole bitsets
                    for (int j = 0; j < p.length; j++) {
                        c1.set(j, mother.b.get(j));
                        c2.set(j, father.b.get(j));
                        if (j == idx) {
                            tmp = c1;
                            c1 = c2;
                            c2 = tmp;
                        }
                    }
                }

                // ALARM: Redundant object creation, could be implemented
                // with object reuse
                t[k++] = new Chromosome(c1);
                if (k < t.length) t[k++] = new Chromosome(c2);
            }

            // wow this one is pretty concise!
            for (Chromosome c : Arrays.asList(t).
                    subList(1, t.length))
                for (int j = 0; j < bits; j++)
                    if (r.nextDouble() <= mutationProb)
                        c.b.flip(j);

            // Switch populations
            tc = p;
            p = t;
            t = tc;
        }
        return fittest.decode();
    }

    private Chromosome select(DistrGenerator dg) {
        return p[dg.nextInt()]; // WARN: expect errors here
    }

    class Chromosome implements Comparable<Chromosome> {

        public Chromosome(BitSet b) {
            this.b = b;
        }
        /** genotype */
        BitSet b;
        /** fitness */
        double f;

        public int compareTo(Chromosome o) {
            return Double.compare(f, o.f);
        }

        private Object[] decode() {
            Object[] args = new Object[pos.length];
            for (int i = 0; i < args.length; i++) {
                if (min[i] == null) {
					args[i] = decodeInt(this, i);
				} else {
					args[i] = decodeDouble(this, i);
				}
			}

            return args;
        }

        private void updateFitness() {
            Object[] args = decode();
            f = ff.evalFitness(args);
        }
    }
    // PARAMETERS

    public GeneticAlgorithm populationSize(int size) {
        if (size < 1) exc("Population must have at least one individual");
        populationSize = size;
        return this;
    }

    public GeneticAlgorithm mutationProb(double prob) {
        if (prob < 0 || prob > 1) exc("Probability outside of <0,1> range");
        mutationProb = prob;
        return this;
    }

    public GeneticAlgorithm crossoverProb(double prob) {
        if (prob < 0 || prob > 1) exc("Probability outside of <0,1> range");
        crossoverProb = prob;
        return this;
    }

    // Just an utility class
}
