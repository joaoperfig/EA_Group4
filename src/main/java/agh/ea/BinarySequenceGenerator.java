package agh.ea;

public class BinarySequenceGenerator implements ISequenceGenerator {

	@Override
	public ISequence generate(int length) {
		BinarySequence sequence = new BinarySequence(length);
		return sequence;
	}
}
