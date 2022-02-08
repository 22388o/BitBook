package de.cotto.bitbook.backend.model;

public class Input extends InputOutput {
    public static final Input EMPTY = new Input(Coins.NONE, "");

    public Input(Coins value, String address) {
        super(value, address);
    }
}