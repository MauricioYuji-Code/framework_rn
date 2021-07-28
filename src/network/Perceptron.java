package network;

import core.*;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {
    private ArrayList<Neuron> neurons;
    private double bias = 0;
    private double predict = 0;
    private double error;
    private double output;
    private double deltaW1;
    private double deltaW2;
    private double deltaB;
    private double lr = 0.1;


    public Perceptron(ArrayList<Neuron> neurons) {
        this.neurons = neurons;
        iteration();
    }

    public Perceptron(ArrayList<Neuron> neurons, double bias, double predict) {
        this.neurons = neurons;
        this.bias = bias;
        this.predict = predict;
    }


    public double errorCalc(double t, double s) {
        return t - s;
    }

    public double deltaWeigthCalc(double e, double lr, double input) {
        return e * lr * input;
    }

    //Analisar se necessário o calculo da viariação do bias
    public double deltaBiasCalc(double e, double lr) {
        return e * lr;
    }

    public double newWeightCalc(double w, double deltaW) {
        return deltaW + w;
    }

    public double newBiasCalc(double b, double deltaB) {
        return deltaB + b;
    }

    public double sum() {
        System.out.println("Somatória...");
        return (neurons.get(0).getNetInput() * neurons.get(0).getInputConnections().get(0).getWeight().getValue()) + (neurons.get(1).getNetInput() * neurons.get(1).getInputConnections().get(0).getWeight().getValue());
    }

    public double degrau(double n) {
        System.out.println("Função de ativação Degrau...");
        if (n > 0) {
            System.out.println("Retornou 1");
            return 1;
        } else {
            System.out.println("Retornou 0");
            return 0;
        }
    }

    public void iteration() {
        this.output = degrau(sum());
        neurons.get(2).setInput(output);
        if (output == predict) {
            System.out.println("Sucesso");
        } else {
            System.out.println("Cálculo do erro...");
            this.error = errorCalc(predict, output);
            System.out.println("Cálculo variação do peso...");
            deltaW1 = deltaWeigthCalc(error, lr, neurons.get(0).getNetInput());
            deltaW2 = deltaWeigthCalc(error, lr, neurons.get(1).getNetInput());
            System.out.println("Cálculo variação do bias...");
            deltaB = deltaBiasCalc(error, lr);
            System.out.println("Novos pesos...");
            neurons.get(0).getInputConnections().get(0).getWeight().setValue(newWeightCalc(neurons.get(0).getInputConnections().get(0).getWeight().getValue(), deltaW1));
            neurons.get(1).getInputConnections().get(0).getWeight().setValue(newWeightCalc(neurons.get(1).getInputConnections().get(0).getWeight().getValue(), deltaW1));
            System.out.println("Novo bias...");
            this.bias = newBiasCalc(bias, deltaB);
            iteration();
        }
    }


}
