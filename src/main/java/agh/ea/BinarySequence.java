package agh.ea;

public class BinarySequence implements ISequence {
	
	private int[] sequence;
	
	public BinarySequence(int length) {
		this.sequence = new int[length];
	}

	@Override
	public int getLength() {
		return this.sequence.length;
	}

	@Override
	public int at(int index) {
		return this.sequence[index];
	}

	@Override
	public void set(int index, int value) {
		this.sequence[index] = value;
	}

}
