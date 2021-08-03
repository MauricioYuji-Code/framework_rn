package network;

import core.Neuron;

import java.util.ArrayList;


public class Mlp {
    private ArrayList<Neuron> neurons;
    private double bias = 0;
    private double predict = 0.0;
    private double error;
    private double output;
    private double deltaW1;
    private double deltaW2;
    private double deltaW3;
    private double deltaW4;
    private double deltaWO1;
    private double deltaWO2;
    private double deltaB;
    private double lr = 2;
    int e = 0;


    public Mlp(ArrayList<Neuron> neurons) {
        this.neurons = neurons;
        iteration();
    }

    public double errorCalc(double t, double s) {
        return Math.floor((t - s) * 100) / 100;
    }

    public double deltaWeigthCalc(double e, double lr, double input) {
        return Math.floor((e * lr * input) * 100) / 100;
    }

    public double deltaBiasCalc(double e, double lr) {
        return Math.floor((e * lr) * 100) / 100;
    }

    public double newWeightCalc(double w, double deltaW) {
        return Math.floor((deltaW + w) * 100) / 100;
    }

    public double newBiasCalc(double b, double deltaB) {
        return Math.floor((deltaB + b) * 100) / 100;
    }

    public double sigmoid(double n) {
        return 1 / (1 + Math.exp(-n));
    }

    public double sum(double ge1, double ge2, double gw1, double gw2) {
        return Math.floor(((ge1 * gw1) + (ge2 * gw2) + this.bias) * 100) / 100;
    }

    public double getError() {
        return error;
    }

    public double getBias() {
        return bias;
    }

    public double getPredict() {
        return predict;
    }

    public double getOutput() {
        return output;
    }

    public double getDeltaW1() {
        return deltaW1;
    }

    public double getDeltaW2() {
        return deltaW2;
    }

    public double getDeltaW3() {
        return deltaW3;
    }

    public double getDeltaW4() {
        return deltaW4;
    }

    public double getDeltaWO1() {
        return deltaWO1;
    }

    public double getDeltaWO2() {
        return deltaWO2;
    }

    public double getDeltaB() {
        return deltaB;
    }

    public double getLr() {
        return lr;
    }

    public void iteration() {

        //Processo oculta1
        // (e1 * w1) + (e2 * w3) = o1
        neurons.get(2).setInput(sum(neurons.get(0).getNetInput(), neurons.get(1).getNetInput(), neurons.get(0).getInputConnections().get(0).getWeight().getValue(), neurons.get(1).getInputConnections().get(0).getWeight().getValue()));
        //Processo oculta2
        // (e1 * w2) + (e2 * w4) = o1
        neurons.get(3).setInput(sum(neurons.get(0).getNetInput(), neurons.get(1).getNetInput(), neurons.get(0).getInputConnections().get(1).getWeight().getValue(), neurons.get(1).getInputConnections().get(1).getWeight().getValue()));

        //Função de ativação
        neurons.get(2).setInput(sigmoid(neurons.get(2).getNetInput()));
        neurons.get(3).setInput(sigmoid(neurons.get(3).getNetInput()));

        System.out.println("Valor oculta1: " + neurons.get(2).getNetInput());
        System.out.println("Valor oculta2: " + neurons.get(3).getNetInput());

        //processo saida1
        //(o1 * ow1) + (o2 * ow2) = s1
        neurons.get(4).setInput(sum(neurons.get(2).getNetInput(), neurons.get(3).getNetInput(), neurons.get(2).getInputConnections().get(0).getWeight().getValue(), neurons.get(3).getInputConnections().get(0).getWeight().getValue()));

        //Função de ativação
        neurons.get(4).setInput(sigmoid(neurons.get(4).getNetInput()));
        output = neurons.get(4).getNetInput();
        output = rounding(output);
        System.out.println("Valor saida1: " + output);

        if (output == predict) {
            System.out.println("Sucesso");
        } else {
            //Todo terminar o backpropagation
            System.out.println("saida " + output + " valor desejado " + predict);
            System.out.println("A rede precisa de treinamento, resultado não corresponde com o esperado");
            System.out.println("iniciando treinamento...");
            System.out.println("Cálculo do erro...");
            this.error = errorCalc(predict, output);
            System.out.println("Valor do erro..." + getError());

            //Saída para oculta
            System.out.println("Cálculo variação do peso...");
            deltaWO1 = deltaWeigthCalc(error, lr, neurons.get(3).getNetInput());
            deltaWO2 = deltaWeigthCalc(error, lr, neurons.get(2).getNetInput());
            System.out.println("Variação do peso WO1..." + getDeltaWO1());
            System.out.println("Variação do peso WO2..." + getDeltaWO2());

            System.out.println("Calculando novos pesos...");
            neurons.get(3).getInputConnections().get(0).getWeight().setValue(newWeightCalc(neurons.get(3).getInputConnections().get(0).getWeight().getValue(), deltaWO1));
            neurons.get(2).getInputConnections().get(0).getWeight().setValue(newWeightCalc(neurons.get(2).getInputConnections().get(0).getWeight().getValue(), deltaWO2));
            System.out.println("Novo peso WO1..." + neurons.get(3).getInputConnections().get(0).getWeight().getValue());
            System.out.println("Novo peso WO2..." + neurons.get(2).getInputConnections().get(0).getWeight().getValue());

            //Oculta para entrada
            System.out.println("Cálculo variação do peso...");
            deltaW1 = deltaWeigthCalc(error, lr, neurons.get(0).getNetInput());
            deltaW3 = deltaWeigthCalc(error, lr, neurons.get(0).getNetInput());
            System.out.println("Variação do peso W1..." + getDeltaW1());
            System.out.println("Variação do peso W3..." + getDeltaW3());
            deltaW1 = deltaWeigthCalc(error, lr, neurons.get(1).getNetInput());
            deltaW3 = deltaWeigthCalc(error, lr, neurons.get(1).getNetInput());
            System.out.println("Variação do peso W2..." + getDeltaW2());
            System.out.println("Variação do peso W4..." + getDeltaW4());

//            System.out.println("Calculando novos pesos...");
            neurons.get(0).getInputConnections().get(0).getWeight().setValue(newWeightCalc(neurons.get(0).getInputConnections().get(0).getWeight().getValue(), deltaW1));
            neurons.get(0).getInputConnections().get(1).getWeight().setValue(newWeightCalc(neurons.get(0).getInputConnections().get(1).getWeight().getValue(), deltaW3));
            neurons.get(1).getInputConnections().get(0).getWeight().setValue(newWeightCalc(neurons.get(1).getInputConnections().get(0).getWeight().getValue(), deltaW2));
            neurons.get(1).getInputConnections().get(1).getWeight().setValue(newWeightCalc(neurons.get(1).getInputConnections().get(1).getWeight().getValue(), deltaW4));
            System.out.println("Novo peso W1..." + neurons.get(0).getInputConnections().get(0).getWeight().getValue());
            System.out.println("Novo peso W3..." + neurons.get(0).getInputConnections().get(1).getWeight().getValue());
            System.out.println("Novo peso W2..." + neurons.get(1).getInputConnections().get(0).getWeight().getValue());
            System.out.println("Novo peso W4..." + neurons.get(1).getInputConnections().get(1).getWeight().getValue());
//
            System.out.println("Cálculo variação do bias...");
            deltaB = deltaBiasCalc(error, lr);
            System.out.println("Cariação do bias..." + getBias());
            System.out.println("Novo bias...");
            this.bias = newBiasCalc(bias, deltaB);
            System.out.println("Valor do novo bias..." + getBias());

            System.out.println("Epoca:" + e + "\n");
            e++;
            iteration();
        }
    }

    public double rounding(double output) {
        if (output <= 0.000000001) {
            return 0;
        } else if (output >= 0.99) {
            return 1;
        } else {
            return output;
        }
    }
}
