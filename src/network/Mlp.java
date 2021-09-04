package network;

import core.Layer;
import core.NeuralNetwork;
import core.Neuron;

import java.util.ArrayList;


public class Mlp extends NeuralNetwork {
    //    private ArrayList<Neuron> neurons;
//    private double error;
    //    private double output;
//    private double deltaW1;
//    private double deltaW2;
//    private double deltaW3;
//    private double deltaW4;
//    private double deltaWO1;
//    private double deltaWO2;
//    private double deltaB;
//    private double lr = 2;
//    int e = 0;
    //Novo
    private double learningRate = 0.1;
    private double predict = 0;
    private double bias = 0;
    private Layer input;
    private Layer hidden;
    private Layer output;
    private ArrayList<Layer> hiddens;

    public Mlp(double learningRate, double predict, double bias) {
        this.learningRate = learningRate;
        this.predict = predict;
        this.bias = bias;
    }

    @Override
    public void setStructure(String type, int nLayer, int nNeuron) {
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
            case "hidden":
                System.out.println("Camada sendo estruturada " + type);
                if (nLayer > 1) {
                    hiddens = new ArrayList<>();
                    for (int i = 0; i <= nLayer; i++) {
                        Layer hidden = new Layer(nNeuron);
                        hiddens.add(i, hidden);
                    }
                }
                Layer hidden = new Layer(nNeuron);
                this.hidden = hidden;
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

    @Override
    public void connectNeuronIncludingWeigth(int weigthValue) {
        System.out.println("Conectando os neurônios");
        int i = 0;
        int k = 0;
        System.out.println("Conectando da entrada até a oculta...");
        System.out.println("Numero de neuronios na oculta: " + hidden.getNeuronsCount());
        while (i < input.getNeuronsCount()) {
            for (int j = 0; j < hidden.getNeuronsCount(); j++) {
                input.getNeurons().get(i).addInputConnection(hidden.getNeurons().get(j), weigthValue);
                System.out.println("O neurônio: " + input.getNeurons().get(i).getNetInput() + " quantas conexões: " + input.getNeurons().get(i).getInputConnections().size() + " e está conectado com o neurônio posição: " + j);
            }
            i++;
        }
        System.out.println("Conectando da oculta até a saída...");
        System.out.println("Numero de neuronios na saida: " + output.getNeuronsCount());
        while (k < hidden.getNeuronsCount()) {
            for (int j = 0; j < output.getNeuronsCount(); j++) {
                hidden.getNeurons().get(k).addInputConnection(output.getNeurons().get(j), weigthValue);
                System.out.println("O neurônio: " + hidden.getNeurons().get(k).getNetInput() + " quantas conexões: " + hidden.getNeurons().get(k).getInputConnections().size() + " e está conectado com o neurônio posição: " + j);
            }
            k++;
        }
    }

    @Override
    public void start() {

        sum();

    }

    // aux += (input.getNeurons().get(i).getNetInput() * input.getNeurons().get(i).getInputConnections().get(0).getWeight().getValue()) + bias;
    public void sum() {
        System.out.println("Realizando a somatória");
        int auxHidden = 0;
        int auxOutput = 0;
        double aux = 0;

        while (auxHidden < hidden.getNeuronsCount()) {
            for (int i = 0; i < input.getNeuronsCount(); i++) {
                 aux =+ (input.getNeurons().get(i).getNetInput() * input.getNeurons().get(i).getInputConnections().get(auxHidden).getWeight().getValue()) ;
            }
            auxHidden ++;
        }
    }

// OLD
//    public Mlp(ArrayList<Neuron> neurons) {
//        this.neurons = neurons;
//        iteration();
//    }
//
//    public double errorCalc(double t, double s) {
//        return Math.floor((t - s) * 1000) / 1000;
//    }
//
//    public double deltaWeigthCalc(double e, double lr, double input) {
//        return Math.floor((e * lr * input) * 1000) / 1000;
//    }
//
//    public double deltaBiasCalc(double e, double lr) {
//        return Math.floor((e * lr) * 1000) / 1000;
//    }
//
//    public double newWeightCalc(double w, double deltaW) {
//        return Math.floor((deltaW + w) * 1000) / 1000;
//    }
//
//    public double newBiasCalc(double b, double deltaB) {
//        return Math.floor((deltaB + b) * 1000) / 1000;
//    }
//
//    public double sigmoid(double n) {
//        return Math.floor(1 / (1 + Math.exp(-n))* 1000) / 1000;
//    }
//
//    public double sum(double ge1, double ge2, double gw1, double gw2) {
//        return Math.floor(((ge1 * gw1) + (ge2 * gw2) + this.bias) * 1000) / 1000;
//    }
//
//    public double getError() {
//        return error;
//    }
//
//    public double getBias() {
//        return bias;
//    }
//
//    public double getPredict() {
//        return predict;
//    }
//
//    public double getOutput() {
//        return output;
//    }
//
//    public double getDeltaW1() {
//        return deltaW1;
//    }
//
//    public double getDeltaW2() {
//        return deltaW2;
//    }
//
//    public double getDeltaW3() {
//        return deltaW3;
//    }
//
//    public double getDeltaW4() {
//        return deltaW4;
//    }
//
//    public double getDeltaWO1() {
//        return deltaWO1;
//    }
//
//    public double getDeltaWO2() {
//        return deltaWO2;
//    }
//
//    public double getDeltaB() {
//        return deltaB;
//    }
//
//    public double getLr() {
//        return lr;
//    }
//
//    public int iteration() {
//        //Processo oculta1
//        // (e1 * w1) + (e2 * w3) = o1
//        neurons.get(2).setInput(sum(neurons.get(0).getNetInput(), neurons.get(1).getNetInput(), neurons.get(0).getInputConnections().get(0).getWeight().getValue(), neurons.get(1).getInputConnections().get(0).getWeight().getValue()));
//        //Processo oculta2
//        // (e1 * w2) + (e2 * w4) = o1
//        neurons.get(3).setInput(sum(neurons.get(0).getNetInput(), neurons.get(1).getNetInput(), neurons.get(0).getInputConnections().get(1).getWeight().getValue(), neurons.get(1).getInputConnections().get(1).getWeight().getValue()));
//
//        //Função de ativação
//        neurons.get(2).setInput(sigmoid(neurons.get(2).getNetInput()));
//        neurons.get(3).setInput(sigmoid(neurons.get(3).getNetInput()));
//
//        System.out.println("Valor oculta1: " + neurons.get(2).getNetInput());
//        System.out.println("Valor oculta2: " + neurons.get(3).getNetInput());
//
//        //processo saida1
//        //(o1 * ow1) + (o2 * ow2) = s1
//        neurons.get(4).setInput(sum(neurons.get(2).getNetInput(), neurons.get(3).getNetInput(), neurons.get(2).getInputConnections().get(0).getWeight().getValue(), neurons.get(3).getInputConnections().get(0).getWeight().getValue()));
//
//        //Função de ativação
//        neurons.get(4).setInput(sigmoid(neurons.get(4).getNetInput()));
//        output = neurons.get(4).getNetInput();
//        output = rounding(output);
//        System.out.println("Valor saida1: " + output);
//
//        if (output == predict) {
//            System.out.println("Sucesso");
//            return 1;
//        } else {
//            //Todo terminar o backpropagation
//            System.out.println("saida " + output + " valor desejado " + predict);
//            System.out.println("A rede precisa de treinamento, resultado não corresponde com o esperado");
//            System.out.println("iniciando treinamento...");
//            System.out.println("Cálculo do erro...");
//            this.error = errorCalc(predict, output);
//            System.out.println("Valor do erro..." + getError());
//
//            //Saída para oculta
//            System.out.println("Cálculo variação do peso...");
//            deltaWO1 = deltaWeigthCalc(error, lr, neurons.get(3).getNetInput());
//            deltaWO2 = deltaWeigthCalc(error, lr, neurons.get(2).getNetInput());
//            System.out.println("Variação do peso WO1..." + getDeltaWO1());
//            System.out.println("Variação do peso WO2..." + getDeltaWO2());
//
//            System.out.println("Calculando novos pesos...");
//            neurons.get(3).getInputConnections().get(0).getWeight().setValue(newWeightCalc(neurons.get(3).getInputConnections().get(0).getWeight().getValue(), deltaWO1));
//            neurons.get(2).getInputConnections().get(0).getWeight().setValue(newWeightCalc(neurons.get(2).getInputConnections().get(0).getWeight().getValue(), deltaWO2));
//            System.out.println("Novo peso WO1..." + neurons.get(3).getInputConnections().get(0).getWeight().getValue());
//            System.out.println("Novo peso WO2..." + neurons.get(2).getInputConnections().get(0).getWeight().getValue());
//
//            //Oculta para entrada
//            System.out.println("Cálculo variação do peso...");
//            deltaW1 = deltaWeigthCalc(error, lr, neurons.get(0).getNetInput());
//            deltaW3 = deltaWeigthCalc(error, lr, neurons.get(0).getNetInput());
//            System.out.println("Variação do peso W1..." + getDeltaW1());
//            System.out.println("Variação do peso W3..." + getDeltaW3());
//            deltaW1 = deltaWeigthCalc(error, lr, neurons.get(1).getNetInput());
//            deltaW3 = deltaWeigthCalc(error, lr, neurons.get(1).getNetInput());
//            System.out.println("Variação do peso W2..." + getDeltaW2());
//            System.out.println("Variação do peso W4..." + getDeltaW4());
//
////            System.out.println("Calculando novos pesos...");
//            neurons.get(0).getInputConnections().get(0).getWeight().setValue(newWeightCalc(neurons.get(0).getInputConnections().get(0).getWeight().getValue(), deltaW1));
//            neurons.get(0).getInputConnections().get(1).getWeight().setValue(newWeightCalc(neurons.get(0).getInputConnections().get(1).getWeight().getValue(), deltaW3));
//            neurons.get(1).getInputConnections().get(0).getWeight().setValue(newWeightCalc(neurons.get(1).getInputConnections().get(0).getWeight().getValue(), deltaW2));
//            neurons.get(1).getInputConnections().get(1).getWeight().setValue(newWeightCalc(neurons.get(1).getInputConnections().get(1).getWeight().getValue(), deltaW4));
//            System.out.println("Novo peso W1..." + neurons.get(0).getInputConnections().get(0).getWeight().getValue());
//            System.out.println("Novo peso W3..." + neurons.get(0).getInputConnections().get(1).getWeight().getValue());
//            System.out.println("Novo peso W2..." + neurons.get(1).getInputConnections().get(0).getWeight().getValue());
//            System.out.println("Novo peso W4..." + neurons.get(1).getInputConnections().get(1).getWeight().getValue());
//
//            System.out.println("Cálculo variação do bias...");
//            deltaB = deltaBiasCalc(error, lr);
//            System.out.println("Cariação do bias..." + getBias());
//            System.out.println("Novo bias...");
//            this.bias = newBiasCalc(bias, deltaB);
//            System.out.println("Valor do novo bias..." + getBias());
//
//            System.out.println("Epoca:" + e + "\n");
//            e++;
//            return iteration();
//        }
//    }
//
//    public double rounding(double output) {
//        if (output <= 0.001) {
//            return 0;
//        } else if (output >= 0.99) {
//            return 1;
//        } else {
//            return output;
//        }
//    }
//

}
