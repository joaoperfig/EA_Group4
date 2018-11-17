package agh.ea;

public interface ISequence {

	int getLength();
	
	int at(int index);
	
	@Override
	String toString();
}
