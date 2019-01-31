package agh.ea;

import java.util.HashSet;
import java.util.PriorityQueue;

public class XLastovka {
	
	public static final long MAX_DURATION_MINUTES = 10;
	
	public static final long MAX_DURATION_NANOSECONDS = (long) (MAX_DURATION_MINUTES * 60 * 1E9);
	
	public static double secondsSince(long time) {
		return (System.nanoTime() - time) / 1E9;  
	}
	
	public ISequence generate(int size, long maxIterations, int maxPrioritySize) {
		long startTime = System.nanoTime();
		long endTime = startTime + MAX_DURATION_NANOSECONDS;
		
		ISequence bestSequence = null;
		float bestMerit = 0;
		float bestEnergy = Float.MAX_VALUE;
		EnergyEvaluator energyEvaluator = new EnergyEvaluator();
		MeritEvaluator meritEvaluator = new MeritEvaluator();
		
		while (System.nanoTime() < endTime) {
			ISequence currentSequence = generateSingle(size, maxIterations, maxPrioritySize);
			
			float currentMerit = meritEvaluator.evaluate(currentSequence);
			float currentEnergy = energyEvaluator.evaluate(currentSequence);
			
			if (currentMerit > bestMerit) {
				bestSequence = currentSequence;
				bestMerit = currentMerit;
				bestEnergy = currentEnergy;
			}
			
			System.out.println(bestMerit + " " + bestEnergy);
		}
		
		return bestSequence;
	}

	private ISequence generateSingle(int size, long maxIterations, int maxPrioritySize) {
		//System.out.println("Start XLastovka Algorithm!");

		ISequenceGenerator generator = new BinarySequenceGenerator();
		MeritEvaluator meritEvaluator = new MeritEvaluator();
		EnergyEvaluator energyEvaluator = new EnergyEvaluator();
		PriorityQueue<SequenceValuePair> priority = new PriorityQueue<SequenceValuePair>();
		HashSet<ISequence> visitedNeighbors = new HashSet<ISequence>();
		ISequence flipped;
		float flippedMerit;
		float flippedEnergy;
		SequenceValuePair flippedSeqPair;

		ISequence bestSequence = null;
		float bestMerit = 0;
		float bestEnergy = Float.MAX_VALUE;

		ISequence sequence = generator.generate(size);
		SequenceValuePair seqPair;
		float merit = meritEvaluator.evaluate(sequence);
		seqPair = new SequenceValuePair(sequence, merit);
		priority.add(seqPair);

		for (long iteration = 1; iteration <= maxIterations; iteration++) {

			if (priority.size() >= maxPrioritySize)
				priority = removeExcess(priority, maxPrioritySize);
			
			if (priority.isEmpty()) break;

			sequence = priority.remove().getSequence();
			visitedNeighbors.add(sequence);
			for (int i = 0; i < (size + 1) / 2; i++) {
				flipped = sequence.flip(i);
				// System.out.println(flipped.toString());
				if (!visitedNeighbors.contains(flipped)) {
					flippedMerit = meritEvaluator.evaluate(flipped);
					flippedEnergy = energyEvaluator.evaluate(flipped);
					
					if (flippedMerit > bestMerit) {
						bestMerit = flippedMerit;
						bestEnergy = flippedEnergy;
						bestSequence = flipped;
						// System.out.println(bestSequence.toString());
					}
					
					flippedSeqPair = new SequenceValuePair(flipped, flippedMerit);
					priority.add(flippedSeqPair);
				}
			}

		}

		return bestSequence;
	}

	private static PriorityQueue<SequenceValuePair> removeExcess(PriorityQueue<SequenceValuePair> pq, int size) {
		int extras = pq.size() - size;

		PriorityQueue<SequenceValuePair> pqnew = new PriorityQueue<SequenceValuePair>();

		while (pq.size() > extras) {
			pqnew.add(pq.poll());
		}

		pq.clear();
		return pqnew;
	}
}
