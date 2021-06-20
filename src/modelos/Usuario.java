/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


public class Usuario {
    private int idUsuario;
    private String nickname;
    private String clave;
    private String rol;
    private int estado;
    private String referencia;
    private Empleado empleado;
    
    public Usuario() {
        
    }

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(int idUsuario, String nickname, String clave, String rol, int estado, String referencia) {
        this.idUsuario = idUsuario;
        this.nickname = nickname;
        this.clave = clave;
        this.rol = rol;
        this.estado = estado;
        this.referencia = referencia;
    }

    public Usuario(String nickname, String clave, String rol, int estado, String referencia) {
        this.nickname = nickname;
        this.clave = clave;
        this.rol = rol;
        this.estado = estado;
        this.referencia = referencia;
    }

    public Usuario(int idUsuario, String nickname, String clave, String rol, Empleado empleado) {
        this.idUsuario = idUsuario;
        this.nickname = nickname;
        this.clave = clave;
        this.rol = rol;
        this.estado = estado;
        this.empleado = empleado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
