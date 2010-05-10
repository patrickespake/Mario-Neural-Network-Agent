package br.pucpr.neuralnetwork;

// Classe que representa um ponto de treinamento
public class Point {
	// Valores de entrada
	private int[] vals;
	// Saída esperada
	private int output;
	
	// Método construtor
	// aceita os parâmetros:
	// * obs1 = tipo de obstáculo na posição 1
	// * obs2 = tipo de obstáculo na posição 2
	// * output = saída esperada
	public Point(int obs1, int obs2, int output) {
		vals = new int[3];
		vals[0] = obs1;
		vals[1] = obs2;
		// Bias para quando as entradas forem zero
		vals[2] = 1;
		this.output = output;
	}

	// Valores de entrada
	public int[] getVals() {
		return vals;
	}

	// Saída esperada
	public int getOutput() {
		return output;
	}	
}