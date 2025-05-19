package retoPragma.Microusuario.domain.model;

import java.time.LocalDate;

public class User {

    private Long id;
    private String name;
    private String lastname;
    private Long identificationNumber;
    private String numberPhone;
    private LocalDate dateOfBirth;
    private String email;
    private String clave;
    private RolesPlazoleta rol;
    private Long idRestaurant;


    public User() {
    }

    public User(Long id, String name, String lastname, Long identificationNumber, String numberPhone, LocalDate dateOfBirth, String email, String clave, RolesPlazoleta rol, Long idRestaurant) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.identificationNumber = identificationNumber;
        this.numberPhone = numberPhone;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.clave = clave;
        this.rol = rol;
        this.idRestaurant = idRestaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public Long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Long IdentificationNumber) {
        this.identificationNumber = IdentificationNumber;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public RolesPlazoleta getRol() {
        return rol;
    }

    public void setRol(RolesPlazoleta rol) {
        this.rol = rol;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}