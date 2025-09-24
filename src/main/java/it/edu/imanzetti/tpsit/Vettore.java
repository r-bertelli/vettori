package it.edu.imanzetti.tpsit;

public class Vettore {
    private double x0;
    private double y0;
    private double x1;
    private double y1;

    public Vettore(double x0, double y0, double x1, double y1) {
        if (x0 == x1 && y0 == y1) {
            throw new IllegalArgumentException("it.edu.imanzetti.tpsit.Vettore nullo non consentito");
        }
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;

    }

    public Vettore(Vettore v)
    {
        if(v == null) {
            throw new NullPointerException("Il vettore da copiare è null");
        }
        this.x0 = v.x0;
        this.y0 = v.y0;
        this.x1 = v.x1;
        this.y1 = v.y1;
    }


    public double length() {
        return Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
    }

    @Override
    public String toString() {
        return "it.edu.imanzetti.tpsit.Vettore [origine=(" + x0 + "," + y0 + "), vertice=(" + x1 + "," + y1 + "), lunghezza=(" + length() + ")]";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Vettore other = (Vettore) obj;

        double dx1 = this.x1 - this.x0;
        double dy1 = this.y1 - this.y0;
        double dx2 = other.x1 - other.x0;
        double dy2 = other.y1 - other.y0;

        return Double.compare(dx1, dx2) == 0 && Double.compare(dy1, dy2) == 0;
    }

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        // Controllo se la modifica renderebbe il vettore nullo
        if (x0 == this.x1 && this.y0 == this.y1) {
            throw new IllegalArgumentException("Operazione renderebbe il vettore nullo");
        }
        this.x0 = x0;
    }

    public double getY0() {
        return y0;
    }

    public void setY0(double y0) {
        // Controllo se la modifica renderebbe il vettore nullo
        if (this.x0 == this.x1 && y0 == this.y1) {
            throw new IllegalArgumentException("Operazione renderebbe il vettore nullo");
        }
        this.y0 = y0;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        // Controllo se la modifica renderebbe il vettore nullo
        if (this.x0 == x1 && this.y0 == this.y1) {
            throw new IllegalArgumentException("Operazione renderebbe il vettore nullo");
        }
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        // Controllo se la modifica renderebbe il vettore nullo
        if (this.x0 == this.x1 && this.y0 == y1) {
            throw new IllegalArgumentException("Operazione renderebbe il vettore nullo");
        }
        this.y1 = y1;
    }
}
