package agh.ea;

import java.util.HashSet;
import java.util.PriorityQueue;

public class XLastovka {
	
	public ISequence generate (int size, float targetMerit) {
		ISequenceGenerator generator = null;//TODO MARKO CARALHO
		MeritEvaluator evaluator = new MeritEvaluator();
		PriorityQueue<SequenceValuePair> pq = new PriorityQueue<SequenceValuePair>();
		HashSet<ISequence> closePivots = new HashSet<ISequence>();
		ISequence coord = generator.generate(size);
		SequenceValuePair coordpair;
		float value = evaluator.evaluate(coord);
		if(value >= targetMerit) {
			return coord;
		}
		pq.add(new SequenceValuePair(coord, value));
		while (value < targetMerit) {
			coordpair = pq.remove();
			coord = coordpair.getSequence();
			for(int i=0; i<=(size+1)/2; i++) {
				ISequence sFlipped = coord.flip(i); 
				if(closePivots.contains(sFlipped)) {
					continue;
				} else {
					value = evaluator.evaluate(sFlipped);
				}
				if (value >= targetMerit) {
					break;
				} else {
					pq.add(new SequenceValuePair(sFlipped, value));
				}
			}
			closePivots.add(coord);
		}
		return coord;
		
	}

}
