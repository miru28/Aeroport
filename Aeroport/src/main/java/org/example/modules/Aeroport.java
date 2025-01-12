package org.example.modules;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.interfaces.IAeroport;
import org.example.interfaces.IZbor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonRootName("aeroport")
public class Aeroport implements IAeroport {
    @JsonProperty()
    private String nume;

    @JsonProperty()
    private final List<IZbor> zboruri;

    public Aeroport(String nume) {
        this.nume = nume;
        this.zboruri = new ArrayList<>();
    }

    public Aeroport() {
        this.nume = "";
        this.zboruri = new ArrayList<>();
    }

    @Override
    public void AdaugaZbor(IZbor zbor) {
        zboruri.add(zbor);
    }

    @Override
    public List<IZbor> GetZboruri() {
        return zboruri;
    }

    @Override
    public void AfiseazaToateZborurile() {
        System.out.println("Aeroport " + this.nume);
        for (IZbor zbor : zboruri) {
            zbor.AfiseazaDetaliiZbor();
            System.out.println();
        }
    }

    @Override
    public String GetNume() {
        return nume;
    }

    @Override
    public void SetNume(String nume) {
        this.nume = nume;
    }

    @Override
    public void SalveazaAeroport(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), this);
        } catch (IOException e) {
            System.err.println("Error saving airport and flights to file: " + e.getMessage());
        }
    }

    @Override
    public void IncarcaAeroport(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Aeroport loadedAeroport = objectMapper.readValue(new File(filePath), Aeroport.class);

            this.zboruri.clear();
            this.zboruri.addAll(loadedAeroport.zboruri);
            this.nume = loadedAeroport.nume;

        } catch (IOException e) {
            System.err.println("Error loading airport and flights from file: " + e.getMessage());
        }
    }
}