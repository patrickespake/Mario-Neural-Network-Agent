package br.pucpr.neuralnetwork;

// Classe que representa um neurônio
public class Neuron {
	// Dendritos
	private Dendrite[] dendrites;
	// Axônio
	private Axon axon;
	// Pesos
	private float[] weights;
	// Pesos padrões
	private float[] defaultWeights;
	// Tipo da função de ativação
	private String activationType;
	// Constante de aprendizado
	private float constantLearning;

	// Método construtor
	// aceita como pârametros:
	// * numberDendrites = Número de dendritos
	// * defaultWeights = Pesos padrão
	// * activationType = tipo da função de ativação
	public Neuron(int numberDendrites, float[] defaultWeights, String activationType) {
		// Define os pesos padrões
		this.defaultWeights = defaultWeights;
		
		// Se não for definida o tipo da função de ativação,
		// então define como sign
		if (activationType == null) {
			this.activationType = "sign";
		}
		
		// Pesos
		weights = new float[numberDendrites];
		// Dendritos
		dendrites = new Dendrite[numberDendrites];
		
		// Cria valores iniciais randômicos para os pesos
		for (int i = 0; i < weights.length; i++) {
			weights[i] = Util.random(-1, 1);
		}
		
		// Constante de aprendizado
		constantLearning = (float)0.0001;
		// Axônio
		axon = new Axon();
	}
	
	// Soma os dendritos com os seus pesos
	// média ponderada
	public float sumDendrites() {
		float sum = 0;
		
		if (defaultWeights != null && defaultWeights.length > 0) {
			for (int j = 0; j < defaultWeights.length; j++) {
				weights[j] = defaultWeights[j];
			}
		}
		
		for (int i = 0; i < weights.length; i++) {
			sum += dendrites[i].getValue() * weights[i];
		}
		return sum;
	}
	
	// Método de ativação
	// dependendo da soma e do tipo de ativação
	// envia o sinal -1 ou 1 para o axônio
	public void activation() {
		float sum = sumDendrites();

		axon.setSign(1);
		if (sum < 0) {
			axon.setSign(-1);
		}
	}
	
	// Treina o neurônio, afim de obter os melhores pesos para os dendritos
	// aceita como parâmetros:
	// * numberTimes = número de épocas
	// * percentageCorrect = percentual correto para o treinamento ser considerado bem sucedido
	// * examplesTraining = exemplos para o treinamento
	public void train(int numberTimes, int percentageCorrect, Point[] examplesTraining) {
		System.out.println("Treinamento iniciado...");
		
		// Quantidade de treinamento verificado
		int totalVerified = 0;
		// Quantidade de treinamento verificado errado
		int totalVerifiedWrong = 0;
		// Quantidade de treinamento verificado correto
		int totalVerifiedCorrect = 0;
		
		// Repete até o número de épocas ser satisfeito
		for (int i = 0; i < numberTimes; i++) {
			// Percorre todos os exemplos de treinamento
			for (int j = 0; j < examplesTraining.length; j++) {
				// Obtém a saída adivinhada pelo neurônio
				int result = guess(examplesTraining[j]);
				// Calcula o fator de mudança do peso baseado no erro
				// erro = saída desejada - saída adivinhada
				// multiplica pela constante de aprendizado
				float weightChange = constantLearning * (examplesTraining[j].getOutput() - result);
				// Ajusta os pesos baseado no fator de mudança * a entrada
				for (int k = 0; k < weights.length; k++) {
					weights[k] += weightChange * examplesTraining[j].getVals()[k];
				}
				
				// Contabiliza se o valor adivinhado foi correto ou não
				if (result == examplesTraining[j].getOutput()) {
					totalVerifiedCorrect++;
				} else {
					totalVerifiedWrong++;
				}
			}
		}
		
		// Quantidade de treinamentos verificados
		totalVerified = totalVerifiedWrong + totalVerifiedCorrect;
		float totalPercentageCorrect = ((100 * totalVerifiedCorrect ) / totalVerified);
		
		System.out.println("Treinamento terminado...");
		System.out.println("Resultados obtidos:");
		System.out.println(" - Número de épocas executadas: " + numberTimes);
		
		String indicativeSuccess = "bem sucedido";
		
		if (totalPercentageCorrect < percentageCorrect) {
			indicativeSuccess = "mal sucedido";
		}
		
		System.out.println(" - Indicativo de sucesso: Treinamento " + indicativeSuccess);
		System.out.println(" - Porcentagem correto: " + totalPercentageCorrect);
		System.out.println(" - Pesos:");
		
		if (defaultWeights != null && defaultWeights.length > 0) {
			for (int j = 0; j < defaultWeights.length; j++) {
				weights[j] = defaultWeights[j];
			}
		}
		
		for (int i = 0; i < weights.length; i++) {
			System.out.println("   - Peso " + i + ": " + weights[i]);	
		}
	}
	
	// Método que adivinha a saída esperada
	// baseada nas entradas, de acordo com os pesos
	// calculados
	public int guess(Point point) {
		// Define os valores para os dendritos
		setDendrites(point.getVals());
		// Faz a ativação
		activation();
		// Sinal de ativação
		return axon.getSign();
	}

	// Define os valores para os dendritos
	// de acordo com um array de valores
	public void setDendrites(int[] vals) {
		for (int i = 0; i < vals.length; i++) {
			dendrites[i] = new Dendrite(vals[i]);
		}
	}

	// Obtém a constante de aprendizado
	public float getConstantLearning() {
		return constantLearning;
	}

	// Define uma nova constante de aprendizado
	public void setConstantLearning(float constantLearning) {
		this.constantLearning = constantLearning;
	}

	// Obtém o axônio
	public Axon getAxon() {
		return axon;
	}

	// Obtém o tipo da função de ativação usada
	public String getActivationType() {
		return activationType;
	}
}
