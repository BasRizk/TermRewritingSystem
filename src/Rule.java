import java.util.ArrayList;

public class Rule {

	
	// functor => term1 => term2 (functor) => term3 => ...
	// 							|
	//		(ArrayList)			V => term21 => term 22 => term 23 => ...
	
	Term leftSideTerm;
	Term rightSideTerm;
	
}

class Functor extends Term {
	ArrayList<Term> params = new ArrayList<Term>();
	public Functor(String name) {
		super(name);
	}
}

class Term {
	String name;
	public Term(String name) {
		this.name = name;
	}
}