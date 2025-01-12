package org.example.interfaces;

import java.util.List;

public interface IAeroport {
    void AdaugaZbor(IZbor zbor);

    void AfiseazaToateZborurile();

    void SalveazaAeroport(String filePath);

    void IncarcaAeroport(String filePath);

    void SetNume(String nume);

    String GetNume();

    List<IZbor> GetZboruri();
}
