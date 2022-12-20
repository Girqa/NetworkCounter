package ForTinkoff;

import java.util.Scanner;

public class FourthTask {
    public static void main(String[] args) {
        new FourthTask().solution();
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);
        int n = 10;
        Polygon polygon = new Polygon(n);

        System.out.println(maxAreaFromPoints(polygon.points, 0, n-1));
    }


    class Point {
        private double x;
        private double y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static double triangleArea(Point p1, Point p2, Point p3) {
        return 0.5 * Math.abs(
                (p2.x - p1.x) * (p3.y - p1.y) - (p3.x -p1.x) * (p2.y - p1.y)
        );
    }

    public static double maxAreaFromPoints(Point[] points, int start, int end) {
        int basePointNum = start;
        int leftPointNum = start+1;
        int rightPointNum = end;
        double maxArea = triangleArea(
                points[basePointNum],
                points[leftPointNum],
                points[rightPointNum]
        );

        for (int i = start; i < (end-start)/2 + 1; i++) {
            double curArea = triangleArea(
                    points[basePointNum],
                    points[i],
                    points[end - i]
            );
            if (curArea >= maxArea) {
                maxArea = curArea;
                leftPointNum = i;
                rightPointNum = end - i;
            }
        }

        if (leftPointNum - basePointNum > 3) {
            maxArea += maxAreaFromPoints(points, basePointNum+1, leftPointNum-1);
        }
        if (rightPointNum - leftPointNum > 3) {
            maxArea += maxAreaFromPoints(points, leftPointNum+1, rightPointNum-1);
        }
        if (end+1 - rightPointNum > 3) {
            maxArea += maxAreaFromPoints(points, rightPointNum+1, end);
        }
        return maxArea;
    }

    class Polygon {
        private Point[] points;

        public Polygon(int n) {
            points = new Point[n];
            fillPoints(n, points);
        }

        private void fillPoints(int n, Point[] points) {
            points[0] = new Point(0.0, 0.0);
            double curAngle = 0.0;
            double anglePerStep = 2*Math.PI/n;
            for (int i = 1; i < n; i++) {
                curAngle += anglePerStep;
                Point prevPoint = points[i-1];
                points[i] = new Point(
                        prevPoint.x + Math.cos(curAngle),
                        prevPoint.y + Math.sin(curAngle)
                );
            }
        }
    }
}
