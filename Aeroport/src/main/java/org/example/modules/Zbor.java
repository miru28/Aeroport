package org.example.modules;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.example.interfaces.IZbor;

@JsonTypeName("zbor")
public class Zbor implements IZbor {
    @JsonProperty()
    private String codZbor;
    @JsonProperty()
    private String destinatie;
    @JsonProperty()
    private int capacitate;

    public Zbor(String destinatie, String codZbor, int capacitate) {
        this.destinatie = destinatie;
        this.codZbor = codZbor;
        this.capacitate = capacitate;
    }

    public Zbor() {
    }


    @Override
    public String GetCodZbor() {
        return codZbor;
    }

    @Override
    public String GetDestinatie() {
        return destinatie;
    }

    @Override
    public int GetCapacitate() {
        return capacitate;
    }

    @Override
    public void SetCodZbor(String codZbor) {
        this.codZbor = codZbor;
    }

    @Override
    public void SetDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    @Override
    public void SetCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    @Override
    public void AfiseazaDetaliiZbor() {
        System.out.println("Cod zbor: " + codZbor);
        System.out.println("Destinatie: " + destinatie);
        System.out.println("Capacitate: " + capacitate);
    }
}