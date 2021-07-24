package main;

import core.*;

public class Main {

    public static void main(String[] args) {

        Weight testWeight = new Weight(2);
        System.out.println(testWeight.getValue());

        Neuron neuron1 = new Neuron();
        neuron1.setInput(1);
        Neuron neuron2 = new Neuron();
        neuron2.setInput(1);
        Neuron neuron3 = new Neuron();

        Connection connection1 = new Connection(neuron1, neuron3);
        Connection connection2 = new Connection(neuron2, neuron3);

        connection1.setWeight(testWeight);
        connection2.setWeight(testWeight);

        Layer inputLayer = new Layer();
        Layer outputLayer = new Layer();

        inputLayer.addNeuron(neuron1);
        inputLayer.addNeuron(neuron2);
        outputLayer.addNeuron(neuron3);

        NeuralNetwork perceptron = new NeuralNetwork();
        perceptron.addLayer(inputLayer);
        perceptron.addLayer(outputLayer);
    }
}
