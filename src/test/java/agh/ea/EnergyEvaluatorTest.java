package agh.ea;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EnergyEvaluatorTest {

	@Test
	void test() {
		ISequence sequence = new BinarySequence(new int[]{1, -1, 1, -1});
		int expectedEnergy = 14;
		
		IEvaluator evaluator = new EnergyEvaluator();
		assertEquals(expectedEnergy, evaluator.evaluate(sequence));
	}

}
