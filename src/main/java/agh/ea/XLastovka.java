package agh.ea;

import java.util.HashSet;
import java.util.PriorityQueue;

public class XLastovka {

	public ISequence generate(int size, long durationSeconds, int maxPrioritySize) {
		//System.out.println("Start XLastovka Algorithm!");

		long durationNanoSecs = (long) (durationSeconds * 1E9);
		long start = System.nanoTime();

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

		while (System.nanoTime() < (start + durationNanoSecs)) {

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
						System.out.println(bestMerit + " " + bestEnergy);
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
