package fr.emse.test;

import java.util.Vector; 

public class MoneyBag implements IMoney{
	private Vector<Money> fMonies = new Vector<Money>(); 
	
	MoneyBag(Money m1, Money m2) { 
		appendMoney(m1); 
		appendMoney(m2); 
	} 

	MoneyBag(Money[] bag) {
	    for (Money m : bag) {
	        appendMoney(m);
	    }
	}

	
	private IMoney simplify() {
	    // Retirer les Money avec montant 0
	    fMonies.removeIf(m -> m.amount() == 0);

	    if (fMonies.isEmpty()) {
	        return new Money(0, "CHF"); // ou une convention pour zéro
	    }
	    if (fMonies.size() == 1) {
	        return fMonies.firstElement(); // retourne directement le Money
	    }
	    return this;
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
	
/*	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;                  // même référence → égalité
	    if (obj == null || getClass() != obj.getClass()) return false;
	    MoneyBag other = (MoneyBag) obj;
	    return fMonies.equals(other.fMonies);
	}*/
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof MoneyBag)) return false;
	    MoneyBag other = (MoneyBag) obj;

	    if (fMonies.size() != other.fMonies.size()) return false;

	    for (Money m : fMonies) {
	        boolean found = false;
	        for (Money om : other.fMonies) {
	            if (m.equals(om)) { // utilise equals de Money
	                found = true;
	                break;
	            }
	        }
	        if (!found) return false;
	    }
	    return true;
	}



	@Override
    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }

	@Override
	public IMoney addMoney(Money m) {
	    MoneyBag result = new MoneyBag(fMonies.toArray(new Money[0]));
	    result.appendMoney(m);
	    return result.simplify();
	}

	@Override
	public IMoney addMoneyBag(MoneyBag bag) {
	    MoneyBag result = new MoneyBag(fMonies.toArray(new Money[0]));
	    for (Money m : bag.fMonies) {
	        result.appendMoney(m);
	    }
	    return result.simplify();
	}



}
