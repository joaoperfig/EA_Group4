package agh.ea;

public class EnergyEvaluator implements IEvaluator {

	@Override
	public float evaluate(ISequence sequence) {
		int total = 0;
		for (int k = 1; k < sequence.getLength(); k++) {
			total += Math.pow(cks(k, sequence), 2);
		}
		return (float) total;
	}

	private int cks(int k, ISequence sequence) {
		int total = 0;
		for (int i = 0; i < sequence.getLength() - k; i++) {
			total += sequence.at(i) * sequence.at(i + k);
		}
		return total;
	}
}
