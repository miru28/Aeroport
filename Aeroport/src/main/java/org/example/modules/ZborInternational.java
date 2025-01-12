package org.example.modules;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.example.interfaces.IZbor;

@JsonTypeName("zborInternational")
public class ZborInternational extends Zbor implements IZbor {
    @JsonProperty()
    private String taraDestinatie;

    public ZborInternational(String destinatie, String taraDestinatie, String codZbor, int capacitate) {
        super(destinatie, codZbor, capacitate);
        this.taraDestinatie = taraDestinatie;
    }

    ZborInternational() {
    }

    @Override
    public void AfiseazaDetaliiZbor() {
        super.AfiseazaDetaliiZbor();
        System.out.println("Tara destinație: " + taraDestinatie);
    }

    public String GetTaraDestinatie() {
        return taraDestinatie;
    }
}