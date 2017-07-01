package pl.patrykgala.ee.testing.cdi;

import javax.inject.Inject;

public class Bean {

    @Inject
    NumberProvider provider;

    public int getNumber() {
        return provider.increment();
    }
}
