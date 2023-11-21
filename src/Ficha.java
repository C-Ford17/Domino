public class Ficha implements Comparable<Ficha> {
    private final int valorIzquierdo;
    private final int valorDerecho;

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

    public boolean contiene(int numero) {
        return valorDerecho == numero || valorIzquierdo == numero;
    }

    public int noCoincidencia(Ficha ficha){
        if (valorDerecho == ficha.valorDerecho || valorDerecho == ficha.valorIzquierdo) return valorIzquierdo;
        return valorDerecho;
    }
}
