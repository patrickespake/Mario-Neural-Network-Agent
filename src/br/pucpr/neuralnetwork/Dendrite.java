package br.pucpr.neuralnetwork;

// Classe que representa um dendrito
public class Dendrite {
	// Valor para o dendrito
	private int value;

	// Método construtor
	// aceita como parâmetro:
	// * value = valor para o dendrito
	public Dendrite(int value) {
		this.value = value;
	}

	// Obtém o valor atual do dendrito
	public int getValue() {
		return value;
	}
}
