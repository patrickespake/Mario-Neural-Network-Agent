package br.pucpr.neuralnetwork;

// Classe que representa um axônio
public class Axon {
	// Sinal de saída
	private int sign;
	
	// Obtém o sinal do axônio
	public int getSign() {
		return sign;
	}

	// Define o sinal para o axônio
	public void setSign(int sign) {
		this.sign = sign;
	}
	
	// Transforma o sinal em um valor booleano
	public boolean signToBoolean() {
		if (sign == -1) {
			return false;
		}
		return true;
	}
}
