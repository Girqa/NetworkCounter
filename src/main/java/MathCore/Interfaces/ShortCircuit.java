package MathCore.Interfaces;

import MathCore.Scheme.Scheme;

public interface ShortCircuit {
    void execute(Scheme scheme);
    void rollBack(Scheme scheme);
}
