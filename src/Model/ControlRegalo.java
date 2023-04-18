package Model;

import Shared.FileReader;
import Shared.JsonInterpreter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControlRegalo {
    public static List<Proveedor> listaProveedores = new ArrayList<>();
    public static List<Regalo> listaRegalo = new ArrayList<>();
    public void ejecutarPrograma() {
        Scanner reader = new Scanner(System.in);

        System.out.print("Ingrese la edad: ");
        int edadIngresada = reader.nextInt();

        System.out.print("Ingrese el precio máximo a pagar: ");
        double precioMaximo = reader.nextDouble();

        try {
            String contentProveedores = FileReader.readFileAsString("Resources/Proveedores.json");
            String contentRegalos = FileReader.readFileAsString("Resources/Productos.json");

            cargarProveedores(contentProveedores);
            cargarRegalos(contentRegalos);

            List<Regalo> listaFiltroRegalos = consultarInformacionRegalos(edadIngresada, precioMaximo);
            if (!listaFiltroRegalos.isEmpty()) {
                for (Regalo regalo : listaFiltroRegalos) {
                    System.out.println(regalo);
                }
            }
            else System.out.println("No se tiene productos para esa edad o precio");
        } catch (IOException e) {
            System.out.println("Ha ourrido un problema cargando los datos desde el json");
        }
    }

    /**
     * Método encargado de a partir de una edad y un precio máximo retornar los regalos que cumplen con ambas
     * condiciones y retornarlos.
     * @param edadRecomendada Edad que se busca para los regalos
     * @param precioMaximoAPagar precio máximo que se puede pagar para buscar en el rango
     * @return una lista con los regalos que se encuentran en los filtros ingresados
     */
    public List<Regalo> consultarInformacionRegalos(int edadRecomendada, double precioMaximoAPagar){
        List<Regalo> listaFiltroRegalos = new ArrayList<>();
        for (Regalo regalo:listaRegalo) {
            if (regalo.getEdadRecomendada() == edadRecomendada && regalo.getPrecioTotal() <= precioMaximoAPagar) {
                listaFiltroRegalos.add(regalo);
            }
        }
        return listaFiltroRegalos;
    }

    /**
     * Método encargado de crear las instancias de los regalos, a partir de un JSON array que contiene la información
     * @param contentRegalos String que contiene la información de los regalos en JSON
     */
    private void cargarRegalos(String contentRegalos) {
        JsonInterpreter jsonInterpreterRegalos = new JsonInterpreter(contentRegalos);
        JSONArray jsonArrayRegalos = jsonInterpreterRegalos.convertStringToJSONArray();

        for (Object json : jsonArrayRegalos) {
            JSONObject jsonObject = (JSONObject) json;
            for (Proveedor prov : listaProveedores) {
                if (((JSONObject) json).getString("proveedor").equals(prov.getNombreProveedor())) {
                    listaRegalo.add(new Regalo(jsonObject.getString("nombre"), jsonObject.getInt("edad"),
                            jsonObject.getDouble("precio"), prov));
                }
            }
        }
    }
    /**
     * Método encargado de crear las instancias de los productos, a partir de un JSON array que contiene la información
     * @param contentProveedores String que contiene la información de los productos en JSON
     */
    private void cargarProveedores(String contentProveedores) {
        JsonInterpreter jsonInterpreterProveedores = new JsonInterpreter(contentProveedores);
        JSONArray jsonArrayProveedores = jsonInterpreterProveedores.convertStringToJSONArray();
        for (Object json : jsonArrayProveedores) {
            JSONObject jsonObject = (JSONObject) json;
            listaProveedores.add(new Proveedor(jsonObject.getString("nombre"), jsonObject.getDouble("precioEnvio")));
        }
    }
}
