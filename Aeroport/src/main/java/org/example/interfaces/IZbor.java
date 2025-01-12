package org.example.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.example.modules.Zbor;
import org.example.modules.ZborInternational;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Zbor.class, name = "zbor"),
        @JsonSubTypes.Type(value = ZborInternational.class, name = "zborInternational")
})
public interface IZbor {
    String GetCodZbor();

    String GetDestinatie();

    int GetCapacitate();

    void SetCodZbor(String codZbor);

    void SetDestinatie(String destinatie);

    void SetCapacitate(int capacitate);

    void AfiseazaDetaliiZbor();
}
