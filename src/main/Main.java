package main;

import core.*;
import network.Perceptron;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Neuron neuron1 = new Neuron();
        neuron1.setInput(1);

        Neuron neuron2 = new Neuron();
        neuron2.setInput(2);

        Neuron neuron3 = new Neuron();
        neuron1.addInputConnection(neuron3, 1);
        neuron2.addInputConnection(neuron3, 2);

        ArrayList<Neuron> neurons = new ArrayList<>();
        neurons.add(neuron1);
        neurons.add(neuron2);
        neurons.add(neuron3);

        Perceptron perceptron = new Perceptron(neurons);



//        System.out.println(neuron1.getInputConnections().get(0).getWeight().getValue());

    }
}
