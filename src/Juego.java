import java.util.*;

public class Juego {
    private List<Ficha> monton;
    private LinkedList<Ficha> fichasEnMesa;
    private Jugador jugador1;
    private Jugador jugador2;
    private static final int nFichasJugador = 7;

    public Juego() {
        monton = new ArrayList<>();
        fichasEnMesa = new LinkedList<>();
        List<Ficha> fichasJugador1 = new ArrayList<>();
        List<Ficha> fichasJugador2 = new ArrayList<>();
        for (int i = 0; i < nFichasJugador; i++) {
            for (int j = i; j < nFichasJugador; j++) {
                monton.add(new Ficha(i, j));
            }
        }


        Random random = new Random();

        Collections.shuffle(monton, random);
        while (fichasJugador1.size() < nFichasJugador) {
            fichasJugador1.add(monton.remove(0));
            fichasJugador2.add(monton.remove(0));
        }
        jugador1 = new Jugador(fichasJugador1);
        jugador2 = new Jugador(fichasJugador1);
        Ficha mayorDoble1 = getMayorDoble(fichasJugador1);
        Ficha mayorDoble2 = getMayorDoble(fichasJugador2);

        if (mayorDoble1 != null && mayorDoble2 != null) {

            if (mayorDoble1.compareTo(mayorDoble2) < 0) {
                jugador2.setTurno(true);
            } else {
                jugador1.setTurno(true);
            }

        } else if (mayorDoble1 != null) {

            jugador1.setTurno(true);

        } else if (mayorDoble2 != null) {
            jugador2.setTurno(true);
        } else {
            int turno = (int)(Math.random() * 2) + 1;
            if (turno == 1) jugador1.setTurno(true);
            else jugador2.setTurno(true);
        }
    }

    public boolean ponerFicha(Jugador jugador, Ficha ficha){
        return ponerFicha(jugador,ficha, Lado.izquierda);
    }

    public boolean ponerFicha(Jugador jugador,Ficha ficha, Lado lado){
        jugador.setTurno(false);
        if (jugador1.equals(jugador)) jugador2.setTurno(true);
        else jugador1.setTurno(true);
        if (fichasEnMesa.isEmpty()) return fichasEnMesa.add(ficha);
        if (lado.equals(Lado.izquierda) && ficha.getValorDerecho() == fichasEnMesa.getFirst().getValorIzquierdo()){
            fichasEnMesa.addFirst(ficha);
            return true;
        }
        if (lado.equals(Lado.derecha) && ficha.getValorIzquierdo() == fichasEnMesa.getLast().getValorDerecho()){
            fichasEnMesa.addLast(ficha);
            return true;
        }
        System.out.println("No es tu turno");
        return false;
    }


    Ficha getMayorDoble(List<Ficha> fichas) {
        return fichas.stream().filter(ficha -> ficha.getValorDerecho() == ficha.getValorIzquierdo())
                .max((f1, f2) -> f1.compareTo(f2)).orElse(null);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();

    }
}
