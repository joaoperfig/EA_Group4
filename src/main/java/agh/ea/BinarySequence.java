package agh.ea;

import java.util.Arrays;

public class BinarySequence implements ISequence {
	
	private int[] sequence;
	
	public BinarySequence(int length) {
		this.sequence = new int[length];
	}
	
	public BinarySequence(int[] bits) {
		this.sequence = bits;
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

	@Override
	public ISequence flip(int index) {
		BinarySequence flipped = new BinarySequence(this.sequence.clone());
		if (at(index) == -1) {
			flipped.set(index, 1);
		} else {
			flipped.set(index, -1);
		}
		
		return flipped;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(sequence);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BinarySequence other = (BinarySequence) obj;
		if (!Arrays.equals(sequence, other.sequence))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String result = "#";
		for (int i=0;i<this.sequence.length; i++) {
			if (this.sequence[i] > 0) result += "1";
			else result += "0";
		}
		return result+"#";
	}
}
