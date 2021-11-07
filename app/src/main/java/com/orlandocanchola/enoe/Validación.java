package com.orlandocanchola.enoe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validación {
    private Salario salario;
    private Periodo periodo;
    private Lugar lugar;
    private Edad edad;
    private String tipo;

    public Validación(Salario salario) {
        this.salario = salario;
        this.tipo = salario.getTipo();
    }

    public Validación(Periodo periodo) {
        this.periodo = periodo;
        this.tipo = periodo.getTipo();
    }

    public Validación(Lugar lugar) {
        this.lugar = lugar;
        this.tipo = lugar.getTipo();
    }

    public Validación(Edad edad) {
        this.edad = edad;
        this.tipo = edad.getTipo();
    }

    public boolean esVálido() {
        switch (tipo) {
            case "salarioMínimo":
                return salarioMínimoEsVálido();
            case "salarioMáximo":
                return salarioMáximoEsVálido();
            case "trimestre":
                return trimestreEsVálido();
            case "año":
                return añoEsVálido();
            case "ciudad":
                return ciudadEsVálido();
            case "estado":
                return estadoEsVálido();
            case "municipio":
                return municipioEsVálido();
            case "edadMínima":
                return edadMínimaEsVálido();
            case "edadMáxima":
                return edadMáximaEsVálido();
        }
        return false;
    }

    public boolean salarioMínimoEsVálido() {
        if (salario.getSalarioMínimoString().isEmpty() || salario.getSalarioMáximoString().isEmpty()) {
            return salarioMínimoVacíoVálido();
        } else if (salario.getSalarioMínimoString().length() >= 7) {
            salario.setMensajeError("Solamente 6 cifras");
            return false;
        } else if (salario.getSalarioMínimoInt() > salario.getSalarioMáximoInt()) {
            salario.setMensajeError("Tiene que ser menor a salario máximo");
            return false;
        } else {
            salario.setMensajeError("");
            return true;
        }
    }

    private boolean salarioMínimoVacíoVálido() {
        if (salario.getSalarioMínimoString().isEmpty() && !salario.getSalarioMáximoString().isEmpty()) {
            salario.setMensajeError("Ingresa salario mínimo");
            return false;
        } else {
            salario.setMensajeError("");
            return true;
        }
    }

    public boolean salarioMáximoEsVálido() {
        if(salario.getSalarioMáximoString().isEmpty() || salario.getSalarioMínimoString().isEmpty()) {
            return salarioMáximoVacíoVálido();
        } else if(salario.getSalarioMáximoString().length() >= 7) {
            salario.setMensajeError("Solamente 6 cifras");
            return false;
        } else if(salario.getSalarioMáximoInt() < salario.getSalarioMínimoInt()) {
            salario.setMensajeError("Tiene que ser mayor a salario mínimo");
            return false;
        } else {
            salario.setMensajeError("");
            return true;
        }
    }

    private boolean salarioMáximoVacíoVálido() {
        if(salario.getSalarioMáximoString().isEmpty() && !salario.getSalarioMínimoString().isEmpty()) {
            salario.setMensajeError("Ingresa salario máximo");
            return false;
        } else {
            salario.setMensajeError("");
            return true;
        }
    }

    public boolean trimestreEsVálido() {
        if(periodo.getTrimestre().isEmpty() || periodo.getAño().isEmpty()) {
            return trimestreVacíoVálido();
        } else if(periodo.getPeriodoInt() > 194) {
            periodo.setMensajeError("El periodo no existe");
            return false;
        } else {
            periodo.setMensajeError("");
            return true;
        }
    }

    private boolean trimestreVacíoVálido() {
        if (periodo.getTrimestre().isEmpty() && !periodo.getAño().isEmpty()) {
            periodo.setMensajeError("Ingresa trimestre");
            return false;
        } else {
            periodo.setMensajeError("");
            return true;
        }
    }

    public boolean añoEsVálido() {
        if(periodo.getAño().isEmpty() || periodo.getTrimestre().isEmpty()) {
            return añoVacíoVálido();
        } else if(periodo.getPeriodoInt() > 194) {
            periodo.setMensajeError("El periodo no existe");
            return false;
        } else {
            periodo.setMensajeError("");
            return true;
        }
    }

    private boolean añoVacíoVálido() {
        if (periodo.getAño().isEmpty() && !periodo.getTrimestre().isEmpty()) {
            periodo.setMensajeError("Ingresa año");
            return false;
        } else {
            periodo.setMensajeError("");
            return true;
        }
    }

    public boolean ciudadEsVálido() {
        if (!lugar.getCiudad().isEmpty() || !lugar.getEstado().isEmpty() || !lugar.getMunicipio().isEmpty()) {
            return ciudadOcupadoVálido();
        } else {
            lugar.setMensajeError("");
            return true;
        }
    }

    private boolean ciudadOcupadoVálido() {
        if (!lugar.getCiudad().isEmpty() && !lugar.getEstado().isEmpty()) {
            lugar.setMensajeError("Ingresa ciudad o estado");
            return false;
        } else if (!lugar.getCiudad().isEmpty() && !lugar.getMunicipio().isEmpty()) {
            lugar.setMensajeError("Ingresa ciudad o municipio");
            return false;
        } else {
            lugar.setMensajeError("");
            return true;
        }
    }

    public boolean estadoEsVálido() {
        if (!lugar.getCiudad().isEmpty() || !lugar.getEstado().isEmpty()) {
            return estadoOcupadoVálido();
        } else {
            lugar.setMensajeError("");
            return true;
        }
    }

    private boolean estadoOcupadoVálido() {
        if (!lugar.getCiudad().isEmpty() && !lugar.getEstado().isEmpty()) {
            lugar.setMensajeError("Ingresa ciudad o estado");
            return false;
        } else {
            lugar.setMensajeError("");
            return true;
        }
    }

    public boolean municipioEsVálido() {
        if (!lugar.getCiudad().isEmpty() || !lugar.getEstado().isEmpty() || !lugar.getMunicipio().isEmpty()) {
            return municipioOcupadoVálido();
        } else {
            lugar.setMensajeError("");
            return true;
        }
    }

    private boolean municipioOcupadoVálido() {
        if (!lugar.getCiudad().isEmpty() && !lugar.getMunicipio().isEmpty()) {
            lugar.setMensajeError("Ingresa ciudad o municipio");
            return false;
        } else {
            lugar.setMensajeError("");
            return true;
        }
    }

    public boolean edadMínimaEsVálido() {
        if (edad.getEdadMínimaString().isEmpty() || edad.getEdadMáximaString().isEmpty()) {
            return edadMínimaVacíaVálido();
        } else if (edad.getEdadMínimaString().length() >= 3) {
            edad.setMensajeError("Solamente 2 cifras");
            return false;
        } else if (edad.getEdadMínimaInt() > edad.getEdadMáximaInt()) {
            edad.setMensajeError("Tiene que ser menor a edad máxima");
            return false;
        } else {
            edad.setMensajeError("");
            return true;
        }
    }

    private boolean edadMínimaVacíaVálido() {
        if (edad.getEdadMínimaString().isEmpty() && !edad.getEdadMáximaString().isEmpty()) {
            edad.setMensajeError("Ingresa edad mínima");
            return false;
        } else {
            edad.setMensajeError("");
            return true;
        }
    }

    public boolean edadMáximaEsVálido() {
        if(edad.getEdadMáximaString().isEmpty() || edad.getEdadMínimaString().isEmpty()) {
            return edadMáximaVacíaVálido();
        } else if(edad.getEdadMáximaString().length() >= 3) {
            edad.setMensajeError("Solamente 2 cifras");
            return false;
        } else if(edad.getEdadMáximaInt() < edad.getEdadMínimaInt()) {
            edad.setMensajeError("Tiene que ser mayor a edad mínima");
            return false;
        } else {
            edad.setMensajeError("");
            return true;
        }
    }

    private boolean edadMáximaVacíaVálido() {
        if(edad.getEdadMáximaString().isEmpty() && !edad.getEdadMínimaString().isEmpty()) {
            edad.setMensajeError("Ingresa edad máxima");
            return false;
        } else {
            edad.setMensajeError("");
            return true;
        }
    }
}
