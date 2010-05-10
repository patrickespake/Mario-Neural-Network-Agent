package br.pucpr.neuralnetwork;

import java.util.Random;

public class Util {
	private static Random internalRandom;
	
	public static float random(float howbig) {
		if (howbig == 0) return 0;

		if (internalRandom == null) internalRandom = new Random();

		float value = 0;
		do {
			value = internalRandom.nextFloat() * howbig;
		} while (value == howbig);
			return value;
	}
	
	public static float random(float howsmall, float howbig) {
		if (howsmall >= howbig) return howsmall;
		float diff = howbig - howsmall;
		return random(diff) + howsmall;
	}
}
