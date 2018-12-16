package agh.ea;

public class MeritEvaluator implements IEvaluator {

	private EnergyEvaluator energyEval = new EnergyEvaluator();

	@Override
	public float evaluate(ISequence sequence) {
		return (float) (Math.pow(sequence.getLength(), 2) / (2 * energyEval.evaluate(sequence)));
	}

}
