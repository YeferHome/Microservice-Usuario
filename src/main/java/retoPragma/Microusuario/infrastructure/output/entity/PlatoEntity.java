package retoPragma.Microusuario.infrastructure.output.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "plato")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlato;

    private String nombrePlato;
    private String descripcionPlato;
    private Long precioPlato;
    private String urlPlato;
    private String categoriaPlato;
    private Boolean activo;
    private Long idRestaurante;
}