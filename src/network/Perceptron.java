package network;
import core.*;

import java.util.ArrayList;

public class Perceptron extends NeuralNetwork {
    private double bias = 0;
    private double predict = 0;
    private double error;
    private ArrayList<Double> deltaW;
    private double deltaB;
    private double learningRate = 0.1;
    //    int e = 0;
    private Layer input;
    private Layer output;

    public Perceptron(double learningRate, double predict, double bias) {
        this.learningRate = learningRate;
        this.predict = predict;
        this.bias = bias;
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

    @Override
    public void setStructure(String type, int nLayer, int nNeuron) {
        if (type.equals("hidden")) {
            System.out.println("Isso não é um perceptron!!");
            System.exit(0);
        }

        if (nLayer <= 0 || nNeuron <= 0) {
            System.out.println("Ops, algo errado no numero de camadas/neurônios");
            System.exit(0);
        }

        switch (type) {
            case "input":
                System.out.println("Camada sendo estruturada " + type);
                Layer input = new Layer(nNeuron);
                this.input = input;
                break;
            case "output":
                System.out.println("Camada sendo estruturada " + type);
                Layer output = new Layer(nNeuron);
                this.output = output;
                break;
            default:
                System.out.println("Você não utilizou nem input ou output como tipo da camada");
                System.exit(0);
        }


    }

    @Override
    public void setInputValues(ArrayList inputValues) {
        ArrayList<double[]> samples = inputValues;
        System.out.println("Valores da camada de entrada: ");
        for (int i = 0; i < input.getNeuronsCount(); i++) {
            input.getNeurons().get(i).setInput(samples.get(0)[i]); //Exemplo apenas da primeira amostra
            System.out.println(input.getNeurons().get(i).getNetInput());
        }

    }

    //Feedfoward
    @Override
    public void connectNeuronIncludingWeigth(int weigthValue) {
        System.out.println("Conectando os neurônios");
        for (int i = 0; i < input.getNeuronsCount(); i++) {
            System.out.println(input.getNeurons().get(i).getNetInput());
            input.getNeurons().get(i).addInputConnection(output.getNeurons().get(0), weigthValue);
            System.out.println("O neurônio " + input.getNeurons().get(i).getNetInput() + " tem conexão? " + input.getNeurons().get(i).hasInputConnections());
        }
    }

    public void start() {
        System.out.println("Start Perceptron!!");
        output.getNeurons().get(0).setOutput(FunctionActivation.degrau(sum()));
        System.out.println("Valor do neurônio de saída: " + output.getNeurons().get(0).getOutput());
        while (output.getNeurons().get(0).getOutput() != predict) {
            System.out.println("A rede precisa de treinamento, resultado não corresponde com o esperado");
            System.out.println("iniciando treinamento...");
            output.getNeurons().get(0).setOutput(FunctionActivation.degrau(sum()));
            System.out.println("Valor do neurônio de saída: " + output.getNeurons().get(0).getOutput());
            backPropagation();
        }
        System.out.println("Rede treinada! \nResultado final da saída: " + output.getNeurons().get(0).getOutput() + " Valor esperado: " + predict);


    }

    public double sum() {
        System.out.println("Realizando a somatória...");
        double aux = 0;
        for (int i = 0; i < input.getNeuronsCount(); i++) {
            aux += (input.getNeurons().get(i).getNetInput() * input.getNeurons().get(i).getInputConnections().get(0).getWeight().getValue()) + bias;
        }
        System.out.println("Resultado da somatória: " + aux);
        return aux;
    }

    public void backPropagation() {
        this.error = errorCalc(predict, output.getNeurons().get(0).getOutput());
        System.out.println("Valor do erro..." + error);
        System.out.println("Cálculo variação do peso...");
        this.deltaW = new ArrayList<>();
        for (int i = 0; i < input.getNeuronsCount(); i++) {
            deltaW.add(i, deltaWeigthCalc(error, learningRate, input.getNeurons().get(i).getNetInput()));
            System.out.println("Valores inseridos dentro da lista deltaW: " + deltaW.get(i));
        }
        System.out.println("Cálculo variação do bias...");
        this.deltaB = deltaBiasCalc(error, learningRate);
        System.out.println("Criação do bias..." + deltaB);
        System.out.println("Calculando novos pesos...");
        for (int i = 0; i < input.getNeuronsCount(); i++) {
            input.getNeurons().get(i).getInputConnections().get(0).getWeight().setValue(newWeightCalc(input.getNeurons().get(i).getInputConnections().get(0).getWeight().getValue(), deltaW.get(i)));
            System.out.println("Valores dos novos pesos: " + input.getNeurons().get(i).getInputConnections().get(0).getWeight().getValue());
        }
        System.out.println("Novo bias...");
        this.bias = newBiasCalc(bias, deltaB);
        System.out.println("Valor do novo bias..." + bias);
    }

}