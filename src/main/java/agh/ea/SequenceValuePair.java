package agh.ea;

public class SequenceValuePair implements Comparable<SequenceValuePair>{
	
	private ISequence sequence;
	private float merit;
	
	public SequenceValuePair(ISequence sequence, float merit) {
		this.setSequence(sequence);
		this.setMerit(merit);
	}
	
   @Override
    public int compareTo(SequenceValuePair other) {
        if (this.getMerit()>other.getMerit()) {
        	return 1;
        	}
        else if (this.getMerit()<other.getMerit()) {
        	return -1;
        	}
        return 0;
    }

	public ISequence getSequence() {
		return sequence;
	}
	
	public void setSequence(ISequence sequence) {
		this.sequence = sequence;
	}

	public float getMerit() {
		return merit;
	}

	public void setMerit(float merit) {
		this.merit = merit;
	}

}
