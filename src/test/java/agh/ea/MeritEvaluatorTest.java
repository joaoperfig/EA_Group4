package agh.ea;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MeritEvaluatorTest {

	@Test
	void test() {
		ISequence sequence = new BinarySequence(new int[] { -1, -1, -1, 1, -1 });
		float expectedMerit = 6.25f;

		IEvaluator evaluator = new MeritEvaluator();
		assertEquals(expectedMerit, evaluator.evaluate(sequence));
	}

}
