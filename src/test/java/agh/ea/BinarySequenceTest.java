package agh.ea;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinarySequenceTest {

	@Test
	void shouldBeEqualWithSameArray() {
		BinarySequence bs = new BinarySequence(new int[] {-1});
		assertEquals(new BinarySequence(new int[] {-1}), bs);
	}
	
	@Test
	void shouldNotBeEqualWithAnotherArray() {
		BinarySequence bs = new BinarySequence(new int[] {-1});
		assertNotEquals(new BinarySequence(new int[] {1}), bs);
	}
	
	@Test
	void shouldHaveSameHashWithSameArray() {
		BinarySequence bs1 = new BinarySequence(new int[] {1, -1, 1});
		BinarySequence bs2 = new BinarySequence(new int[] {1, -1, 1});
		assertEquals(bs1.hashCode(), bs2.hashCode());
	}
	
	@Test
	void shouldHaveDifferentHashWithDifferentArray() {
		BinarySequence startingSequence = new BinarySequence(new int[] {1, -1, 1});
		BinarySequence flippedSequence = new BinarySequence(new int[] {1, -1, -1});
		assertNotEquals(startingSequence.hashCode(), flippedSequence.hashCode());
	}
	
	@Test
	void shouldChangeValueOnFlip() {
		BinarySequence goalSequence = new BinarySequence(new int[] {1, -1, 1});
		BinarySequence startingSequence = new BinarySequence(new int[] {1, -1, -1}); 
		ISequence flippedSequence = startingSequence.flip(2);
		
		assertNotEquals(startingSequence.at(2), flippedSequence.at(2));
		assertEquals(goalSequence, flippedSequence);
		assertEquals(goalSequence.hashCode(), flippedSequence.hashCode());
	}

}
