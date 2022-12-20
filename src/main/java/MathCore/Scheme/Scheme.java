package MathCore.Scheme;

import MathCore.BaseElements.Device;
import MathCore.BaseElements.Node;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.linear.*;

import java.util.List;

@Data
@Slf4j
public class Scheme {
    private List<Device> devices;
    private List<Node> nodes;

    private Complex[][] A;
    private Complex[] J;
    private  Complex[][] Y;
    private Complex[] E;
    private Complex[] U;

    public Scheme(List<Device> devices, List<Node> nodes) {
        this.devices = devices;
        this.nodes = nodes;
    }

    public void fillMatrices() {
        int connections = nodes.size();  // число узлов
        int branches = devices.size();   // число ветвей
        A = new Complex[connections - 1][branches];
        Y = new Complex[branches][branches];
        J = new Complex[branches];
        E = new Complex[branches];
        U = new Complex[connections - 1];

        for (int i = 0; i < branches; i++) {
            Y[i][i] = Complex.ONE.divide(
                    devices.get(i).getResistance()
            );
            J[i] = devices.get(i).getCurrent();
            E[i] = devices.get(i).getVoltage();
            for (int j = 0; j < connections - 1; j++) {
                if (devices.get(i).getStartNode().equals(nodes.get(j))) {
                    A[j][i] = Complex.ONE.multiply(-1.0);
                } else if (devices.get(i).getFinishNode().equals(nodes.get(j))) {
                    A[j][i] = Complex.ONE;
                } else {
                    A[j][i] = Complex.ZERO;
                }
            }
            for (int k = 0; k < branches; k++) {
                if (k != i) {
                    Y[i][k] = Complex.ZERO;
                }
            }
        }
    }

    public Complex[] solveSystem() {
        FieldMatrix<Complex> a = new Array2DRowFieldMatrix<>(A);
        FieldMatrix<Complex> y = new Array2DRowFieldMatrix<>(Y);
        FieldVector<Complex> j = new ArrayFieldVector<>(J);
        FieldVector<Complex> e = new ArrayFieldVector<>(E);

        // Матрица узловых проводимостей
        FieldMatrix<Complex> nodeConductance = a.multiply(y).multiply(a.transpose());
        // Вектор узловых токов
        FieldVector<Complex> nodeCurrent = a.operate(j.add(y.operate(e)));
        // Решатель уравнения
        FieldDecompositionSolver<Complex> solver =
                new FieldLUDecompositionImpl<>(nodeConductance).getSolver();

        if (!solver.isNonSingular()) {
            log.warn("Matrix is singular!");
        }
        // Решение уравнения
        FieldVector<Complex> solution = solver.solve(nodeCurrent);
        U = solution.toArray();
        modifyNodes();
        return U;
    }

    private void modifyNodes() {
        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).setPotential(U[i]);
        }
    }
}
