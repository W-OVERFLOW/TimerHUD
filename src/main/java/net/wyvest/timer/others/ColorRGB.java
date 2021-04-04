package net.wyvest.timer.others;

/*/
Made by MatthewTGM under the GPL-3.0 License
 */

public class ColorRGB {

    private int r, g, b, a;

    public ColorRGB(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public ColorRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 255;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getRGB() {
        return new java.awt.Color(r, g, b).getRGB();
    }

    public int getRGBA() {
        return new java.awt.Color(r, g, b, a).getRGB();
    }

}
