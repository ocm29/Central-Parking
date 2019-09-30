package parqueadero;

import java.util.*;
import java.time.*;

public class Carro {

    private String placa;
    private String propietario;
    private String tipo;
    private LocalDateTime horaDeEntrada;

    public Carro(String placa, String propietario, String tipo) {
        this.placa = placa;
        this.propietario = propietario;
        this.tipo = tipo;
    }

    
    
    public Carro(String placa, String propietario, String tipo, LocalDateTime hora) {
        this.placa = placa;
        this.propietario = propietario;
        this.tipo = tipo;
        this.horaDeEntrada = hora;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String isTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
