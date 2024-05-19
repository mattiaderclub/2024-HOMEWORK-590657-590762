package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String attrezzoLuminoso;
	
	public StanzaBuia(String nome, String attrezzoLuminoso) {
		super(nome);
		this.attrezzoLuminoso = attrezzoLuminoso;
	}
	
	@Override
	public String getDescrizione() {
		if (!hasAttrezzo(attrezzoLuminoso)) {
			return "qui c'è un buio pesto";
		}
		else
			return super.getDescrizione();     
    }
	
	public String getAttrezzoLuminoso() {
		return this.attrezzoLuminoso;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass())
			return false;
		
		StanzaBuia that = (StanzaBuia) o;
		return super.equals(o) && this.getAttrezzoLuminoso().equals(that.getAttrezzoLuminoso());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + this.getAttrezzoLuminoso().hashCode();
	}
}
