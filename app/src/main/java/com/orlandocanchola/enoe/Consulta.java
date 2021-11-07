package com.orlandocanchola.enoe;

public class Consulta {
    public static String generarConsulta(String tipo, String... condiciones) {
        StringBuffer consulta = new StringBuffer();
        switch (tipo) {
            case "histogramaSalario":
                consulta.append("select J where");
                consulta.append(adjuntarCondiciones(condiciones));
                consulta.append(" label J 'Salarios'");
                break;
            case "tendenciaSalario":
                consulta.append("select E, avg(J) where");
                consulta.append(adjuntarCondiciones(condiciones));
                consulta.append(" group by E");
                consulta.append(" label avg(J) 'Salarios'");
                break;
            case "mapaSalario":
                consulta.append("select K, avg(J) where");
                consulta.append(adjuntarCondiciones(condiciones));
                consulta.append(" group by K");
        }
        return consulta.toString();
    }

    private static String adjuntarCondiciones(String[] condiciones) {
        StringBuffer clausula = new StringBuffer();

        clausula.append(" " + condiciones[0]);

        for (String elemento: condiciones) { //Obtengo las demás condiciones
            if (!elemento.isEmpty() && !elemento.equals(condiciones[0])) {
                clausula.append(" and " + elemento);
            }
        }

        return clausula.toString();
    }
}
