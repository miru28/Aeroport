package org.example.modules;

import org.example.interfaces.IZbor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AeroportTest {

    private Aeroport aeroport;
    private final String testFilePath = "C:\\Users\\Miruna\\Desktop\\ANUL 2\\MIP\\PROIECTaeroport\\Aeroport\\src\\zboruri_test .json";

    @BeforeEach
    void setUp() {
        aeroport = new Aeroport("Aeroport Test");
    }

    @AfterEach
    void tearDown() {
        // Remove the test file after execution to avoid clutter
        File file = new File(testFilePath);
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }

    @Test
    void adaugaZbor() {
        IZbor zbor = new Zbor("New York", "NY123", 200);

        aeroport.AdaugaZbor(zbor);

        List<IZbor> zboruri = aeroport.GetZboruri();
        assertNotNull(zboruri);
        assertEquals(1, zboruri.size());
        assertEquals("New York", zboruri.getFirst().GetDestinatie());
    }

    @Test
    void getZboruri() {
        IZbor zbor1 = new Zbor("New York", "NY123", 200);
        IZbor zbor2 = new Zbor("London", "LD456", 300);

        aeroport.AdaugaZbor(zbor1);
        aeroport.AdaugaZbor(zbor2);

        List<IZbor> zboruri = aeroport.GetZboruri();
        assertNotNull(zboruri);
        assertEquals(2, zboruri.size());
        assertEquals("London", zboruri.get(1).GetDestinatie());
    }

    @Test
    void afiseazaToateZborurile() {
        // Test that the method runs without exceptions
        IZbor zbor = new Zbor("Tokyo", "TK789", 500);
        aeroport.AdaugaZbor(zbor);

        assertDoesNotThrow(() -> aeroport.AfiseazaToateZborurile());
    }

    @Test
    void salveazaAeroport() {

        IZbor zbor = new Zbor("Paris", "FR123", 100);
        aeroport.AdaugaZbor(zbor);

        aeroport.SalveazaAeroport(testFilePath);

        File file = new File(testFilePath);
        assertTrue(file.exists());
    }

    @Test
    void incarcaAeroport() {

        String fileContent = """
                {
                  "nume": "Aeroport Test",
                  "zboruri": [
                    {
                      "type": "zbor",
                      "destinatie": "Berlin",
                      "codZbor": "BL001",
                      "capacitate": 150
                    }
                  ]
                }
                """;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write(fileContent);
        } catch (Exception e) {
            fail("Test file could not be created.");
        }


        aeroport.IncarcaAeroport(testFilePath);

        List<IZbor> zboruri = aeroport.GetZboruri();
        assertNotNull(zboruri);
        assertEquals(1, zboruri.size());
        assertEquals("Berlin", zboruri.getFirst().GetDestinatie());
    }
}