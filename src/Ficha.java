public class Ficha implements Comparable<Ficha> {
    private int valorIzquierdo;
    private int valorDerecho;

    public Ficha(int valorIzquierdo, int valorDerecho) {
        assert(0 <= valorIzquierdo && valorIzquierdo <= 6);
        assert(0 <= valorDerecho && valorDerecho <= 6);
        this.valorIzquierdo = valorIzquierdo;
        this.valorDerecho = valorDerecho;
    }

    public int getValorIzquierdo() {
        return valorIzquierdo;
    }

    public int getValorDerecho() {
        return valorDerecho;
    }

    @Override
    public int compareTo(Ficha o) {
        return getValorDerecho() - o.getValorDerecho();
    }
}
