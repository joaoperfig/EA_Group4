package agh.ea;

public interface ISequence {

	int getLength();

	int at(int index);

	void set(int index, int value);

	ISequence flip(int index);

	@Override
	String toString();
}
