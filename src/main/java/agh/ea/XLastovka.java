package agh.ea;

import java.util.HashSet;
import java.util.PriorityQueue;

public class XLastovka {
	
	public ISequence generate (int size, long durationNanoSecs, int maxPrioritySize) {
		System.out.println("Start XLastovka Algorithm!");
		
		long start = System.nanoTime();
		
		ISequenceGenerator generator = new BinarySequenceGenerator();
		MeritEvaluator evaluator = new MeritEvaluator();
		PriorityQueue<SequenceValuePair> priority = new PriorityQueue<SequenceValuePair>();
		HashSet<ISequence> visitedNeighbors = new HashSet<ISequence>();
		ISequence flipped;
		float fmerit;
		SequenceValuePair fseqpair;
		
		ISequence bestSequence = null;
		float bestMerit = 0;
		
		ISequence sequence = generator.generate(size);
		SequenceValuePair seqpair;
		float merit = evaluator.evaluate(sequence);
		seqpair = new SequenceValuePair(sequence, merit);
		priority.add(seqpair);
		
		while (System.nanoTime()<(start+durationNanoSecs)) {
			
			if(priority.size() >= maxPrioritySize)
				priority = removelasts(priority, maxPrioritySize);
			
			//System.out.println(priority.size());
			
			sequence = priority.remove().getSequence();
			visitedNeighbors.add(sequence);
			for (int i=0; i<(size+1)/2; i++) {
				flipped = sequence.flip(i);
				//System.out.println(flipped.toString());
				if (!visitedNeighbors.contains(flipped)) {
					fmerit = evaluator.evaluate(flipped);
					if (fmerit > bestMerit) {
						bestMerit = fmerit;
						bestSequence = flipped;
						System.out.println(bestMerit);
						//System.out.println(bestSequence.toString());
					}
					fseqpair = new SequenceValuePair(flipped, fmerit);
					priority.add(fseqpair);
				}
			}
		}
		
		return bestSequence;
	}
	
	private static PriorityQueue<SequenceValuePair> removelasts(PriorityQueue<SequenceValuePair> pq, int size)
	{
		int extras = pq.size()-size;
		
	    PriorityQueue<SequenceValuePair> pqnew = new PriorityQueue<SequenceValuePair>();

	    while(pq.size() > extras)
	    {
	        pqnew.add(pq.poll());
	    }

	    pq.clear();
	    return pqnew;
	}

}
