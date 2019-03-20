
public class X23495698  extends Polynomial {
	public static void main(String args[]) throws Exception {
		Polynomial p = new X23495698(" X^5"), 
				q = new X23495698("X^2 - X + 1");
		Utility.run(p, q);
	}

	public X23495698(String s) {
		// complete this code

		polynomialSplit(s);
	}

	public void polynomialSplit(String s) {
		double coefficient =0;
		int degree =0;
		String[] str = s.split(" ");
		boolean contains = false;
		
		
		for (int i=0; i < str.length; i++)
		{
			String term = str[i];
			String previousTerm = null;
			if(i != 0)
				previousTerm = str[i-1];
			for(int j=0; j< term.length(); j++) {
				if(term.charAt(j) == '^')
					contains = true;
			}

			if(term.indexOf("X") == 0) 
			{
				if(term.length() == 3 && contains)
				{
					coefficient = 1;
					degree = Integer.parseInt(term.substring(2));
				}
				else if(term.length() == 1) 
				{
					degree = 1;
					if(previousTerm.equals("+"))
						coefficient = 1;
					else
						coefficient *= -1;
				}
			}
			else if (term.indexOf("+") == 0 || term.indexOf("-") == 0)
				continue;

			else if(term.length() == 1 && Character.isDigit(term.charAt(0)))
			{
				coefficient = Integer.parseInt(term);
				degree = 0;
				if(previousTerm.equals("-"))
					coefficient*= -1;
				else
					coefficient *= 1;
			}
			else if(term.length() == 2 && Character.isDigit(term.charAt(0))) 
			{

				coefficient = Character.getNumericValue(term.charAt(0));
				degree = 1;
				if(previousTerm.equals("+"))
					coefficient *= 1;
				else
					coefficient *= -1;

			}

			Term T = new Term(coefficient, degree);
			if(data.isEmpty())
			{
				data.addFirst(T);
			}
			else
			{
				data.addLast(T);
			}
		}
	}
	public X23495698() {
		super();
	}

	public Polynomial add(Polynomial p) {
		Polynomial ans = new X23495698();
		Polynomial a = this;
		double coefficient =0;
		int degree = 0;

		DNode<Term> x;
		DNode<Term> y;
		try {
			x = a.data.getFirst();
			y = p.data.getFirst();
			if(x.getData().getCoefficient() == 0)
				x=x.getNext();
			Term myTerm = null;
			Term term2 = null;

			while(x.getData() !=null && y.getData() != null) 
			{
				myTerm = x.getData();
				term2 = y.getData();


				if(myTerm.getDegree() == term2.getDegree())
				{
					coefficient = x.getData().getCoefficient() + y.getData().getCoefficient();
					degree = x.getData().getDegree();
					Term t = new Term(coefficient, degree);
					ans.data.addLast(t);
					x=x.getNext();
					y=y.getNext();
				}
				else if(myTerm.getDegree() < term2.getDegree()) 
				{
					Term t2 = new Term(term2.getCoefficient(), term2.getDegree());
					ans.data.addLast(t2);
					y=y.getNext();

				}
				else if(myTerm.getDegree() > term2.getDegree())
				{
					Term t3 = new Term(myTerm.getCoefficient(), myTerm.getDegree());
					ans.data.addLast(t3);
					x=x.getNext();
				}
			}
			while(x.getData() != null)
			{
				myTerm = x.getData();
				x=x.getNext();
				Term t4 = new Term(myTerm.getCoefficient(), myTerm.getDegree());
				ans.data.addLast(t4);
				//System.out.println("ans" + ans);
			}
			while(y.getData() != null)
			{
				term2 = y.getData();
				y = y.getNext();
				Term t5 = new Term(term2.getCoefficient(), term2.getDegree());
				ans.data.addLast(t5);
				//System.out.println("ans" + ans);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;
	}

	public Polynomial subtract(Polynomial p) {
		Polynomial ans = new X23495698();
		// complete this code
		Polynomial a = this;
		double coefficient =0;
		int degree = 0;
		DNode<Term> y;
		DNode<Term> x;
		try {
			x = a.data.getFirst();

			y = p.data.getFirst();
			Term myTerm = null;
			Term term2 = null;
			if(x.getData().getCoefficient() == 0)
				x=x.getNext();
			while(x.getData() !=null && y.getData() != null) 
			{
				myTerm = x.getData();
				term2 = y.getData();

				if(myTerm.getDegree() == term2.getDegree())
				{
					coefficient = x.getData().getCoefficient() - y.getData().getCoefficient();
					degree = x.getData().getDegree();
					Term t = new Term(coefficient, degree);
					ans.data.addLast(t);
					x=x.getNext();
					y=y.getNext();
				}
				else if(myTerm.getDegree() < term2.getDegree()) 
				{
					Term t2 = new Term(-term2.getCoefficient(), term2.getDegree());
					ans.data.addLast(t2);
					y=y.getNext();

				}
				else if(myTerm.getDegree() > term2.getDegree())
				{
					Term t3 = new Term(myTerm.getCoefficient(), myTerm.getDegree());
					ans.data.addLast(t3);
					x=x.getNext();
				}
			}
			while(x.getData() != null)
			{
				myTerm = x.getData();
				x=x.getNext();
				Term t4 = new Term(myTerm.getCoefficient(), myTerm.getDegree());
				ans.data.addLast(t4);
			}
			while(y.getData() != null)
			{
				term2 = y.getData();
				y = y.getNext();
				Term t5 = new Term(term2.getCoefficient(), term2.getDegree());
				ans.data.addLast(t5);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;
	}

	public Polynomial multiply(Polynomial p) {
		Polynomial ans = new X23495698();
		// complete this code

		Polynomial a = this;
		double coefficient =1;
		int degree = 0;
		DNode<Term> x;
		try {
			x = a.data.getFirst();
			if(x.getData().getCoefficient() == 0)
				x=x.getNext();
			while(x.getData() != null )
			{
				DNode<Term> y = p.data.getFirst();
				while(y.getData() !=null)
				{
					coefficient = x.getData().getCoefficient() * y.getData().getCoefficient();
					degree = x.getData().getDegree() + y.getData().getDegree();
					ans.data.addLast(new Term(coefficient, degree));
					y=y.getNext();
				}
				x=x.getNext();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;
	}


	public Polynomial divide(Polynomial p) throws Exception {
		Polynomial ans = new X23495698();
		Polynomial remainder = new X23495698();
		Polynomial mult = new X23495698();
		double coefficient = 1;
		Polynomial a =this;
		Polynomial b = p;
		DNode<Term> x = a.data.getFirst();
		DNode<Term> y = b.data.getFirst();

		if(x.getData().getCoefficient() == 0)
			x=x.getNext();
		while( y.getData().getDegree() < x.getData().getDegree())
		{
			coefficient = x.getData().getCoefficient() / y.getData().getCoefficient();
			int degree = x.getData().getDegree() - y.getData().getDegree();
			ans.data.addLast(new Term(coefficient, degree));
			remainder = (p.multiply(ans));
			remainder = a.subtract(remainder);

			while( y.getData().getDegree() >= x.getData().getDegree()) {
			x = remainder.data.getFirst();
			y = b.data.getFirst();
			if(x.getData().getCoefficient() == 0)
				x=x.getNext();
			coefficient = x.getData().getCoefficient() / y.getData().getCoefficient();
			degree = x.getData().getDegree() - y.getData().getDegree();
			ans.data.addLast(new Term(coefficient, degree));
			mult.data.addLast(new Term(coefficient, degree));
			mult = (p.multiply(mult));
			remainder = mult.subtract(remainder);
			
			x = remainder.data.getFirst();
			y = b.data.getFirst();
			if(x.getData().getCoefficient() == 0)
				x=x.getNext();
			coefficient = x.getData().getCoefficient() / y.getData().getCoefficient();
			degree = x.getData().getDegree() - y.getData().getDegree();
			ans.data.addLast(new Term(coefficient, degree));
			mult.data.addLast(new Term(coefficient, degree));
			mult = (p.multiply(mult));
			remainder = mult.subtract(remainder);
			
			return ans;
			}
		}
		return ans;
	}

	public Polynomial remainder(Polynomial p) throws Exception {
		Polynomial ans = new X23495698();
		Polynomial remainder = new X23495698();
		// complete this code
		// was only done till first two term
		int count =0;
		Polynomial mult = new X23495698();
		double coefficient = 1;
		Polynomial a =this;
		Polynomial b = p;
		DNode<Term> x = a.data.getFirst();
		DNode<Term> y = b.data.getFirst();
		if(x.getData().getCoefficient() == 0)
			x=x.getNext();
		while(count != 2)
		{
			coefficient = x.getData().getCoefficient() / y.getData().getCoefficient();
			int degree = x.getData().getDegree() - y.getData().getDegree();
			ans.data.addLast(new Term(coefficient, degree));
			remainder = (p.multiply(ans));
			remainder = a.subtract(remainder);



			x = remainder.data.getFirst();
			y = b.data.getFirst();
			if(x.getData().getCoefficient() == 0)
				x=x.getNext();
			coefficient = x.getData().getCoefficient() / y.getData().getCoefficient();
			degree = x.getData().getDegree() - y.getData().getDegree();
			ans.data.addLast(new Term(coefficient, degree));
			mult.data.addLast(new Term(coefficient, degree));
			mult = (p.multiply(mult));
			remainder = mult.subtract(remainder);

			return remainder;
		}
		return remainder;

	}
}

