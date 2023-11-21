import java.util.*;

public class Juego {
    private List<Ficha> monton;
    private LinkedList<Ficha> fichasEnMesa;
    private Jugador jugador1;
    private Jugador jugador2;
    private static final int nFichasJugador = 7;

    public List<Ficha> getMonton() {
        return monton;
    }

    public LinkedList<Ficha> getFichasEnMesa() {
        return fichasEnMesa;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

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

    public boolean ponerFicha(Ficha ficha){
        return ponerFicha(ficha, Lado.izquierda);
    }

    public boolean ponerFicha(Ficha ficha, Lado lado){
        jugadorConTurno().setTurno(false);
        jugadorSinTurno().setTurno(true);
        if (fichasEnMesa.isEmpty()) return fichasEnMesa.add(ficha);
        if (lado.equals(Lado.izquierda) && ficha.contiene(numeroLibre(lado))) {
            fichasEnMesa.addFirst(ficha);
            return true;
        }
        if (lado.equals(Lado.derecha) && ficha.contiene(numeroLibre(lado))) {
            fichasEnMesa.addLast(ficha);
            return true;
        }
        return false;
    }

    public boolean obtenerFichaDelMonton(Jugador jugador){
        return jugador.addFicha(monton.remove(0));
    }


    Ficha getMayorDoble(List<Ficha> fichas) {
        return fichas.stream().filter(ficha -> ficha.getValorDerecho() == ficha.getValorIzquierdo())
                .max(Ficha::compareTo).orElse(null);
    }

    public Jugador jugadorConTurno(){
        if (jugador1.isTurno()) return jugador1;
        return jugador2;
    }

    public Jugador jugadorSinTurno(){
        if (jugador1.isTurno()) return jugador2;
        return jugador1;
    }

    public int numeroLibre(Lado lado) {
        if (fichasEnMesa.size() != 1) {
            if (lado == Lado.derecha) {
                Ficha ultima = fichasEnMesa.getLast();
                Ficha penultima = fichasEnMesa.get(fichasEnMesa.size() - 2);

                return ultima.noCoincidencia(penultima);
            } else {
                Ficha primera = fichasEnMesa.getFirst();
                Ficha segunda = fichasEnMesa.get(1);

                return primera.noCoincidencia(segunda);
            }
        }
        if (lado == Lado.derecha) return fichasEnMesa.getLast().getValorDerecho();
        return fichasEnMesa.getLast().getValorIzquierdo();
    }

    public boolean termino(){
        return jugador1.getFichas().isEmpty() || jugador2.getFichas().isEmpty();
    }
}
