package Model;

import org.json.JSONObject;

public class Regalo {
    private String nombreRegalo;
    private int edadRecomendada;
    private double precioBase;
    private Proveedor proveedor;
    private double precioTotal;
    public Regalo(String nombreRegalo, int edadRecomendada, double precioBase, Proveedor proveedor) {
        this.nombreRegalo = nombreRegalo;
        this.edadRecomendada = edadRecomendada;
        this.precioBase = precioBase;
        this.proveedor = proveedor;
        this.precioTotal = precioBase + proveedor.getPrecioEnvio();
    }

    public int getEdadRecomendada() {
        return edadRecomendada;
    }
    public String getNombreRegalo() {
        return nombreRegalo;
    }
    public double getPrecioTotal() {
        return precioTotal;
    }

    @Override
    public String toString() {
        return nombreRegalo + '-' +" precio base: $" + precioBase + "- precio de envío: $" + (precioTotal-precioBase) +
                "- precio total: $" + precioTotal;
    }
}
