package pl.patrykgala.ee.testing.cdi;

public class NumberProvider {

    private int number = 0;

    public int increment() {
        return ++number;
    }
}
