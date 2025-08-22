package fr.emse.test;

import java.util.Vector; 

public class MoneyBag implements IMoney{
	private Vector<Money> fMonies = new Vector<Money>(); 
	
	MoneyBag(Money m1, Money m2) { 
		appendMoney(m1); 
		appendMoney(m2); 
	} 
	
	MoneyBag(Money bag[]) { 
		for (int i = 0; i < bag.length; i++) 
		appendMoney(bag[i]); 
	}
	
	private void appendMoney(Money m) { 
		if (fMonies.isEmpty()) { 
			fMonies.add(m); 
		} else { 
			int i = 0; 
			while ((i < fMonies.size()) 
			&& (!(fMonies.get(i).currency().equals(m.currency())))) 
			i++; 
			if (i >= fMonies.size()) { 
				fMonies.add(m); 
			} else { 
				fMonies.set(i, new Money(fMonies.get(i).amount() + 
				m.amount(), m.currency())); 
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;                  // même référence → égalité
	    if (obj == null || getClass() != obj.getClass()) return false;
	    MoneyBag other = (MoneyBag) obj;
	    return fMonies.equals(other.fMonies);
	}

	public IMoney add(IMoney m) { 
		return m.addMoneyBag(this); 
	} 


}
