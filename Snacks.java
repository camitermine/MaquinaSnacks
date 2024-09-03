package maquina_snacks;

import java.util.ArrayList;
import java.util.List;

public class Snacks {
    private static final List<Snack> snacks;

    //bloque static inicializador. Como no vamos a crear obj no necesito
    //un constructor

    static{
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("Chizitos", 60));
        snacks.add(new Snack("Palitos", 40));
    }

    public static void agregarSnack(Snack snack) {
        snacks.add(snack);
    }

    public static void mostrarSnacks() {
        var inventarioSnacks = "";
        for(var snack : snacks) {
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println("---Snacks en el Inventario---");
        System.out.println(inventarioSnacks);
    }

    public static List<Snack> getSnacks() {
        return snacks;
    }
}
