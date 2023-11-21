public class Main {
    public static void main(String[] args) {
        Jugador jugador1 = new Jugador();
        Juego juego = new Juego();

        while (juego.noTermine()) {
            Jugador jugador = juego.jugadorConTurno();

            System.out.println("Fichas mesa...");
            System.out.println("Fichas de jugador...");

            int numero = scanner.nextInt();

            System.out.println("1 para lado derecho, 0 para lado izquierdo");
            int lado = scanner.nextInt();

            juego.ponerFicha(jugador, jugador.getFicha(numero - 1), lado == 1 ? Lado.derecha : Lado.izquierda);
        }
    }
}