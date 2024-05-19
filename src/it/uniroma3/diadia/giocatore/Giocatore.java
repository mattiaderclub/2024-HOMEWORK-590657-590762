package it.uniroma3.diadia.giocatore;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;	
	private Borsa borsa;
	private int cfu;
	
	public Giocatore() {
		this.borsa = new Borsa();	
		this.cfu = CFU_INIZIALI;
	}

	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

	public int getCfu() {
		return this.cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;	
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass())
			return false;
		
		Giocatore that = (Giocatore) o;
		return this.getCfu() == that.getCfu() && this.getBorsa().equals(that.getBorsa());
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getCfu() + this.getBorsa().hashCode();
	}
}
