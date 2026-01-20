package yps.systems.ai.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;

@Node("Tariff") // 1. Cambiamos la etiqueta del nodo en la base de datos
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class Tariff {

    @Id
    @GeneratedValue
    private String elementId;

    // Ej: "Tarifa Estándar Estudiantes", "Tarifa Plana Docentes"
    private String name;

    // Ej: "Aplica para parqueaderos del bloque A y B"
    private String description;

    // IMPORTANTE: El costo monetario (Ej: 0.50)
    private Double cost;

    // La unidad de cobro. Ej: "HORA", "FRACCION", "DIA", "MES"
    private String timeUnit;

    // Moneda. Ej: "USD"
    private String currency;

    // Segmentación: ¿A quién aplica? Ej: "ESTUDIANTE", "DOCENTE", "VISITANTE"
    private String targetRole;

    // Segmentación: ¿Qué vehículo? Ej: "AUTO", "MOTO"
    private String vehicleType;

    // Vigencia de la tarifa (Desde cuándo es válida)
    private LocalDate validFrom;

    // Vigencia de la tarifa (Hasta cuándo es válida)
    private LocalDate validUntil;

    // Si hay conflictos de tarifas, cuál se aplica primero (Ej: 1 = Alta prioridad)
    private Integer priority;

    // Estado para activar/desactivar la tarifa sin borrarla
    private Boolean isActive;

}
