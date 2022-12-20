package MathCore.BaseElements;

import MathCore.Interfaces.CurrentCountable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.math.complex.Complex;

@Data
@NoArgsConstructor
public class Device implements CurrentCountable {
    protected static final double TOLERANCE = 10E-10;
    protected Complex voltage;
    protected Complex resistance;
    protected Complex current;
    protected Node startNode;
    protected Node finishNode;
    protected String type;

    @Override
    public Complex getElementCurrent() {
        Complex elementVoltage = startNode.getPotential().add(finishNode.getPotential().negate());
        return elementVoltage.divide(resistance);
    }
}
