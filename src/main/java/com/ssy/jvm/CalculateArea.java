package com.ssy.jvm;

public class CalculateArea {

    public static void main(String[] args) {
        // 相切
        Point point1 = new Point(1, 0, 1);
        Point point2 = new Point(3, 0, 1);
        System.out.println(CalculateArea.calculate(point1, point2));

        // 包含
        Point point3 = new Point(1, 0, 2);
        Point point4 = new Point(1, 0, 1);
        System.out.println(CalculateArea.calculate(point3, point4));

        // 相交
        Point point5 = new Point(1, 0, 1);
        Point point6 = new Point(2, 0, 1);
        System.out.println(CalculateArea.calculate(point5, point6));
    }

    /**
     * 计算两个圆相交的面积。
     * 用余弦定理得三角形圆心处的角度，然后计算两个扇形的面积，再减去三角形的面积，虽然中间部分被计算了两次，但是因为相交部分以圆心连线分成对称两块，所以正好得到的就是两圆相交面积。
     *
     * @param point1 点1
     * @param point2 点2
     * @return
     */
    public static double calculate(Point point1, Point point2) {
        double distance = Math.sqrt((point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) * (point1.y - point2.y));
        // 两个圆相离
        if (point1.radius + point2.radius <= distance) {
            return 0;
        }
        // 两个圆相含
        if (Math.abs(point1.radius - point2.radius) >= distance) {
            if (point1.radius - point2.radius >= distance) {
                return Math.acos(-1.0) * point2.radius * point2.radius;
            } else {
                return Math.acos(-1.0) * point1.radius * point1.radius;
            }
        }

        // 两个圆相交
        double angle1 = Math.acos((point1.radius * point1.radius + distance * distance - point2.radius * point2.radius) / (2 * distance * point1.radius));
        double angle2 = Math.acos((point2.radius * point2.radius + distance * distance - point1.radius * point1.radius) / (2 * distance * point2.radius));

        return point1.radius * point1.radius * angle1 + point2.radius * point2.radius * angle2 - Math.sin(angle1) * point1.radius * distance;
    }
}

class Point {
    double x;
    double y;
    double radius;

    public Point() {

    }

    public Point(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }
}
