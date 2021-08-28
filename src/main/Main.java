package main;

import core.*;
import network.Perceptron;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Execução de um perceptron
//        Neuron neuron1 = new Neuron();
//        neuron1.setInput(1);
//        Neuron neuron2 = new Neuron();
//        neuron2.setInput(2);
//        Neuron neuron3 = new Neuron();
//        neuron1.addInputConnection(neuron3, 1);
//        neuron2.addInputConnection(neuron3, 2);
//        ArrayList<Neuron> neurons = new ArrayList<>();
//        neurons.add(neuron1);
//        neurons.add(neuron2);
//        neurons.add(neuron3);
//        Perceptron perceptron = new Perceptron(neurons);

        //Execução de um MLP
//        Neuron inputNeuron1 = new Neuron();
//        inputNeuron1.setInput(1);
//        Neuron inputNeuron2 = new Neuron();
//        inputNeuron2.setInput(1);
//        Neuron hidenNeuron1 = new Neuron();
//        Neuron hidenNeuron2 = new Neuron();
//        Neuron outputNeuron1 = new Neuron();
//        inputNeuron1.addInputConnection(hidenNeuron1, 1);
//        inputNeuron1.addInputConnection(hidenNeuron2, 1);
//        inputNeuron2.addInputConnection(hidenNeuron1, 1);
//        inputNeuron2.addInputConnection(hidenNeuron2, 1);
//        hidenNeuron1.addInputConnection(outputNeuron1, 1);
//        hidenNeuron2.addInputConnection(outputNeuron1, 1);
//
//        ArrayList<Neuron> neurons = new ArrayList<>();
//        neurons.add(inputNeuron1);
//        neurons.add(inputNeuron2);
//        neurons.add(hidenNeuron1);
//        neurons.add(hidenNeuron2);
//        neurons.add(outputNeuron1);
//
//        Mlp mlpTest = new Mlp(neurons);

        NeuralNetwork nn = new Perceptron(0.1, 0, 0);
        nn.setStructure("input", 1, 3);
        nn.setStructure("output", 1, 1);
        double sample[] = {0, 1, 0};
        double sample2[] = {1, 0, 1};
        ArrayList<double[]> list = new ArrayList<>();
        list.add(0, sample);
        list.add(1, sample2);
        nn.setInputValues(list);
        nn.connectNeuronIncludingWeigth(1);
//        double[][] weightInput = {{0.5, 1.5}, {0.4, 0.7}, {0.2, 0.8}};
//        double[][] weightHidden = {{0.3, 0.2}, {0.1, 0.5}};
//        nn.setInputWeights(weightInput);
//        nn.setHiddenWeights(weightHidden);
//        nn.sigmoidFunction();
        nn.start();
    }
}
