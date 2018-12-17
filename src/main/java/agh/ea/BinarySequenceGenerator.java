package agh.ea;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class BinarySequenceGenerator implements ISequenceGenerator {

	private SecureRandom random;
	private static final String RNG_ALGORITHM = "SHA1PRNG";

	public BinarySequenceGenerator() {
		try {
			random = SecureRandom.getInstance(RNG_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	@Override
	public ISequence generate(int length) {
		BinarySequence sequence = new BinarySequence(length);

		for (int index = 0; index < length; index++) {
			if (random.nextBoolean()) {
				sequence.set(index, 1);
			} else {
				sequence.set(index, -1);
			}
		}

		return sequence;
	}
}
