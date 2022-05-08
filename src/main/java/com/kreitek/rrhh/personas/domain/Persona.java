package com.kreitek.rrhh.personas.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
//@Table(name = "RRHH_PERSONA")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private Date fechaNacimiento;
    //@Transient
    private Integer edad;

    @Column(length = 10, nullable = true)
    private String telefono;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.edad = calculateAge(fechaNacimiento, Calendar.getInstance().getTime());
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        if (this.edad == null && fechaNacimiento != null){
            this.edad = calculateAge(fechaNacimiento, Calendar.getInstance().getTime());
        }
        return edad;
    }

    private int calculateAge(Date birthDate, Date currentDate) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        int age = (d2 - d1) / 10000;
        return age;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
