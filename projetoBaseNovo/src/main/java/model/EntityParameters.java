package model;

import Utils.Utils.Parameter;

public class EntityParameters {
    public static Parameter<Integer> ANO() {
        return new Parameter<>("Ano", InputValidators::INTEGER, Integer.class);
    }
    public static Parameter<String> MATRICULA() {return new Parameter<>("Matrícula", true, InputValidators::MATRICULA, String.class);}
    public static Parameter<String> NIF() {
        return new Parameter<>("Nif", InputValidators::NIF, String.class);
    }
    public static Parameter<String> CC() {
        return new Parameter<>("CC", InputValidators::CC, String.class);
    }
    public static Parameter<String> NOME() {
        return new Parameter<>("Nome", InputValidators::NOME, String.class);
    }
    public static Parameter<String> MORADA() {return new Parameter<>("Morada", InputValidators::MORADA, String.class);}
    public static Parameter<String> TELEFONE() {return new Parameter<>("Telefone", InputValidators::TELEFONE, String.class);}
    public static Parameter<String> ESTADOGPS() {return new Parameter<>("Estado do GPS", InputValidators::ESTADOGPS, String.class);}
    public static Parameter<String> NOMECONDUTOR() {return new Parameter<>("Telefone", InputValidators::NOMECONDUTOR, String.class);}
    public static Parameter<String> LONGITUDE() {return new Parameter<>("Telefone", InputValidators::LONGITUDE, String.class);}
    public static Parameter<String> LATITUDE() {return new Parameter<>("Telefone", InputValidators::LATITUDE, String.class);}
    public static Parameter<String> MARCATEMPORAL() {return new Parameter<>("Telefone", InputValidators::LATITUDE, String.class);}
    public static Parameter<Integer> GPSID() {return new Parameter<>("Gps", InputValidators::INTEGER, Integer.class);}
    public static Parameter<Integer> NUMALARMES() {return new Parameter<>("Gps", InputValidators::INTEGER, Integer.class);}
}
