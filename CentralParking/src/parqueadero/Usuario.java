package parqueadero;

import java.util.*;
import java.time.*;

public class Usuario {
    private LocalDate reserva;
    private Carro carro;
    private Puesto puesto;
    private LocalDate expiracion;

    public LocalDate getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(LocalDate expiracion) {
        this.expiracion = expiracion;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
    
    public Usuario(LocalDate reserva, Carro carro, Puesto puesto, LocalDate expiracion) {
        this.reserva = reserva;
        this.carro = carro;
        this.puesto = puesto;
        this.expiracion = expiracion;
    }

    public LocalDate getReserva() {
        return reserva;
    }

    public void setReserva(LocalDate reserva) {
        this.reserva = reserva;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
}
