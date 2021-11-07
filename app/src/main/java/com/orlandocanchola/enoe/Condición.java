package com.orlandocanchola.enoe;

public class Condición {

    private Carrera carrera;
    private Salario salario;
    private Periodo periodo;
    private Lugar lugar;
    private NivelEducativo nivelEducativo;
    private Edad edad;
    private Sexo sexo;
    private String tipo;

    public Condición(Carrera carrera) {
        this.carrera = carrera;
        this.tipo = carrera.getTipo();
    }

    public Condición(Salario salario) {
        this.salario = salario;
        this.tipo = salario.getTipo();
    }

    public Condición(Periodo periodo) {
        this.periodo = periodo;
        this.tipo = periodo.getTipo();
    }

    public Condición(Lugar lugar) {
        this.lugar = lugar;
        this.tipo = lugar.getTipo();
    }

    public Condición(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
        this.tipo = nivelEducativo.getTipo();
    }

    public Condición(Edad edad) {
        this.edad = edad;
        this.tipo = edad.getTipo();
    }

    public Condición(Sexo sexo) {
        this.sexo = sexo;
        this.tipo = sexo.getTipo();
    }

    public String generarCondición() {
        switch (tipo) {
            case "carrera":
                return generarCondiciónCarrera();
            case "salarioMínimo":
            case "salarioMáximo":
                return generarCondiciónSalario();
            case "trimestre":
            case "año":
                return generarCondiciónPeriodo();
            case "ciudad":
            case "estado":
            case "municipio":
                return generarCondiciónLugar();
            case "nivelEducativo":
                return generarCondiciónNivelEducativo();
            case "edadMínima":
            case "edadMáxima":
                return generarCondiciónEdad();
            case "sexo":
                return generarCondiciónSexo();
        }
        return "";
    }

    private String generarCondiciónCarrera() {
        int claveCarrera = carrera.getClaveCarrera();

        return "(I = " + claveCarrera + ")";
    }

    private String generarCondiciónSalario() {
        String salarioMínimoString = salario.getSalarioMínimoString();
        String salarioMáximoString = salario.getSalarioMáximoString();

        if(salario.getSalarioMínimoString().isEmpty() && salario.getSalarioMáximoString().isEmpty())
            return "";
        return "(J >= " + salarioMínimoString + " and J <= " + salarioMáximoString + ")";
    }

    private String generarCondiciónPeriodo() {
        String trimestre = periodo.getTrimestre();
        String año = periodo.getAño();
        String periodoString = periodo.getPeriodoString();

        if (trimestre.isEmpty() && año.isEmpty())
            return "";
        return "(E = " + periodoString + ")";
    }

    private String generarCondiciónLugar() {
        String ciudad = lugar.getCiudad();
        String estado = lugar.getEstado();
        String municipio = lugar.getMunicipio();
        int posición = lugar.getPosición();

        if (tipo.equals("ciudad")) {
            if (ciudad.isEmpty())
                return "";
            return "(C = " + posición + ")";
        } else if (tipo.equals("estado")) {
            if (estado.isEmpty())
                return "";
            return "(D = " + posición + ")";
        } else {
            if (municipio.isEmpty())
                return "";
            return "(B = " + posición + ")";
        }
    }

    private String generarCondiciónNivelEducativo() {
        String nivelEducativoString = nivelEducativo.getNivelEducativo();
        int posición = nivelEducativo.getPosición();

        if(nivelEducativoString.isEmpty())
            return "";
        return "(H = " + posición + ")";
    }

    private String generarCondiciónEdad() {
        String edadMínimaString = edad.getEdadMínimaString();
        String edadMáximaString = edad.getEdadMáximaString();

        if (edadMínimaString.isEmpty() && edadMáximaString.isEmpty())
            return "";
        return "(G >= " + edadMínimaString + " and G <= " + edadMáximaString + ")";
    }

    private String generarCondiciónSexo() {
        String sexoString = sexo.getSexo();
        int posición = sexo.getPosición();

        if (sexoString.isEmpty())
            return "";
        return "(F = " + posición + ")";
    }
}
