package network;

import core.Neuron;

import java.util.ArrayList;


public class Mlp {
    private ArrayList<Neuron> neurons;


    public Mlp(ArrayList<Neuron> neurons) {
        this.neurons = neurons;
    }

    public double errorCalc(double t, double s) {
        return t - s;
    }

    public double deltaWeigthCalc(double e, double lr, double input) {
        return e * lr * input;
    }

    public double deltaBiasCalc(double e, double lr) {
        return e * lr;
    }

    public double newWeightCalc(double w, double deltaW) {
        return deltaW + w;
    }

    public double newBiasCalc(double b, double deltaB) {
        return deltaB + b;
    }

    public double sigmoid(double n) {
        return 1 / (1 + Math.exp(-n));
    }

}
