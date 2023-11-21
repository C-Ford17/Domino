import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        int opcion = 0;
        Jugador jugador;
        while(!juego.termino() && opcion != -1){
            jugador = juego.jugadorConTurno();
            String fichas = "";
            if (!juego.getFichasEnMesa().isEmpty()) fichas = mostrarFichasEnMesa(juego.getFichasEnMesa()) + "\n";
            StringBuilder menu1 = new StringBuilder();
            menu1.append(fichas).append("¿Que quieres hacer?\n").
                    append("1. Poner ficha\n").append("2. Agarrar ficha del monton\n").append("3. Salir");
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu1));
            switch (opcion){
                case 1:
                    StringBuilder menu2 = new StringBuilder();
                    menu2.append(fichas).append(listarFichasJugador(jugador.getFichas()));
                    int opcion1 = Integer.parseInt(JOptionPane.showInputDialog(menu2));
                    String[] opciones = {"Izquierda","Derecha"};
                    if (!juego.getFichasEnMesa().isEmpty()){
                        int ladoOpcion = JOptionPane.showOptionDialog(null, fichas + "¿En que lado la vas a colocar?",
                                "Seleccion de lado", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null ,opciones,opciones[0]);
                        Lado lado = null;
                        if(ladoOpcion == 0) lado= Lado.izquierda;
                        else lado = Lado.derecha;
                        if (juego.ponerFicha(jugador.getFichas().get(opcion1-1), lado)){
                            jugador.getFichas().remove(opcion1-1);
                            JOptionPane.showMessageDialog(null, "Se ha puesto la ficha correctamente");
                        }else JOptionPane.showMessageDialog(null, "No se ha podido poner la ficha");
                    }
                    else {
                        if (juego.ponerFicha(jugador.getFichas().get(opcion1-1))){
                            jugador.getFichas().remove(opcion1-1);
                            JOptionPane.showMessageDialog(null, "Se ha puesto la ficha correctamente");
                        }else JOptionPane.showMessageDialog(null, "No se ha podido poner la ficha");
                    }

                    break;
                case 2:
                    juego.obtenerFichaDelMonton(jugador);
                    JOptionPane.showMessageDialog(null, "Has agarrado una ficha del monton");
                    break;
                case 3:
                    opcion = -1;
                    break;
            }
        }
    }

    public static String listarFichasJugador(List<Ficha> fichas){
        StringBuilder fichitas = new StringBuilder();
        for (int i = 0; i < fichas.size(); i++) {
            fichitas.append(i+1).append(". (").append(fichas.get(i).getValorIzquierdo()).append(",").
                append(fichas.get(i).getValorDerecho()).append(")").append("\n");
        }
        return fichitas.toString();
    }

    public static String mostrarFichasEnMesa(LinkedList<Ficha> fichas) {
        StringBuilder fichotas = new StringBuilder();
        if (fichas.size() == 1){
            fichotas.append("(").append(fichas.get(0).getValorIzquierdo()).append(",").
                    append(fichas.get(0).getValorDerecho()).append(")");
            return fichotas.toString();
        }
        if (fichas.get(0).getValorDerecho() == fichas.get(1).coincidencia(fichas.get(0)))
            fichotas.append("(").append(fichas.get(0).getValorIzquierdo()).append(",").
                    append(fichas.get(0).getValorDerecho()).append(")");
        else fichotas.append("(").append(fichas.get(0).getValorDerecho()).append(",").
                append(fichas.get(0).getValorIzquierdo()).append(")");
        for (int i = 1; i < fichas.size(); i++) {
            if (fichas.get(i).getValorIzquierdo() == fichas.get(i - 1).coincidencia(fichas.get(i)))
                fichotas.append("(").append(fichas.get(i).getValorIzquierdo()).append(",").
                        append(fichas.get(i).getValorDerecho()).append(")");
            if (fichas.get(i).getValorDerecho() == fichas.get(i - 1).coincidencia(fichas.get(i)))
                fichotas.append("(").append(fichas.get(i).getValorDerecho()).append(",").
                        append(fichas.get(i).getValorIzquierdo()).append(")");
        }
        return fichotas.toString();
    }
}