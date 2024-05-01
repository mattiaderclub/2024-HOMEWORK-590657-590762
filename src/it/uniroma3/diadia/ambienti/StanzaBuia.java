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

}
