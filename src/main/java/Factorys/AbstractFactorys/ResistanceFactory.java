package Factorys.AbstractFactorys;

import MathCore.BaseElements.Resistance;
import Interfaces.PassiveElementFactory;
import org.apache.commons.math.complex.Complex;

public class ResistanceFactory implements PassiveElementFactory {
    public Resistance newInstance(double resistance) {
        Resistance element = new Resistance();
        element.setVoltage(Complex.ZERO);
        element.setCurrent(Complex.ZERO);
        element.setResistance(new Complex(resistance, 0.0));
        return element;
    }
}
