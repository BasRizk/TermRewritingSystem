import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	
	private static final Pattern linePattern = Pattern.compile("^[a-z]+([(][\\w()]+([,][\\w()]+)*[)])?[=]([\\w()]+)$");
	private static final Pattern functorPattern = Pattern.compile("^[a-z]+([(][\\w()]+([,][\\w()]+)*[)])$");
	private static final Pattern constantPattern = Pattern.compile("^([a-z][\\w]*)+$");
	private static final Pattern varPattern = Pattern.compile("^([A-Z][\\w]*)+$");
	private static final Pattern functorUnpackingPattern = Pattern.compile("^[(](([a-z][\\\\w]*)+([,]([a-z][\\\\w]*)+)*)[)]$");
	
	ArrayList<Rule> rules = new ArrayList<Rule>();
	
	public static void main(String[] args) {
		parseRule("add(a,b)=abc", new Rule());
		parseTerm(new Term("abc(abc)"));
	}
	

	public static Rule parseRule(String line, Rule rule) {
		
		Matcher m = linePattern.matcher(line);
		String leftSide;
		String innerLeftSide;
		String rightSide;

	    // if an occurrence if a pattern was found in a given string...
	    if (!m.find()) {
	        return null;	        
	    }
	    
	    
	    // ...then you can use group() methods.
        System.out.println(m.group(0)); // whole matched expression
        System.out.println(m.group(1)); // first expression from round brackets (Testing)
        System.out.println(m.group(2)); // second one (123)
        System.out.println(m.group(3)); // third one (Testing)
        
        leftSide = m.group(0);
        innerLeftSide = m.group(1);
        rightSide = m.group(3);
        
        rule.leftSideTerm = new Term(leftSide);
        rule.rightSideTerm = new Term(rightSide);
        return rule;
	}
	
	public static Term parseTerm(Term term) {
		String line = term.name;
		Matcher m = functorPattern.matcher(line);
		
	    if (m.find()) {
    		System.out.println("Functor");
	        System.out.println(m.group(0));
	        ((Functor)term).params.addAll(unpackFunctor(m.group(1)));
	    } else {
	    	m = constantPattern.matcher(line);
	    	if(m.find()) {
	    		System.out.println("Constant");
		        System.out.println(m.group(0));
	    	} else {
				m = varPattern.matcher(line);
				if(m.find()) {
		    		System.out.println("Var");
			        System.out.println(m.group(0));
				} else {
					// TODO throw syntax error - incorrect file format
					return null;
				}
	    	}
	    }
	    
	    return term;
	}
	
	public static ArrayList<Term> unpackFunctor(String line) {
		ArrayList<Term> terms = new ArrayList<Term>();
		Matcher m = functorUnpackingPattern.matcher(line);
		if(!m.find()) {
			return null;
		}
		
		String [] params = m.group(1).split(",");
		for(int i = 0; i< params.length; i++) {
			terms.add(parseTerm(new Term(params[i])));
		}
		
		return terms;
	}
}
