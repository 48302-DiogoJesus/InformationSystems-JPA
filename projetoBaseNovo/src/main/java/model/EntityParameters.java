package model;

import Utils.Utils.Parameter;

public class EntityParameters {

    public static Parameter<Integer> ANO(Boolean optional) {
        return new Parameter<>("Ano",optional, InputValidators::INTEGER, Integer.class);
    }

    public static Parameter<String> MATRICULA(Boolean optional) {
        return new Parameter<>("Matrícula",optional, InputValidators::MATRICULA, String.class);
    }

    public static Parameter<String> NIF(Boolean optional) {
        return new Parameter<>("Nif",optional, InputValidators::NIF, String.class);
    }

    public static Parameter<String> CC(Boolean optional) {
        return new Parameter<>("CC",optional, InputValidators::CC, String.class);
    }

    public static Parameter<String> NOME(Boolean optional) {
        return new Parameter<>("Nome",optional, InputValidators::NOME, String.class);
    }

    public static Parameter<String> MORADA(Boolean optional) {
        return new Parameter<>("Morada",optional, InputValidators::MORADA, String.class);
    }

    public static Parameter<String> TELEFONE(Boolean optional) {
        return new Parameter<>("Telefone",optional, InputValidators::TELEFONE, String.class);
    }

    public static Parameter<String> ESTADOGPS(Boolean optional) {
        return new Parameter<>("Estado Gps",optional, InputValidators::NOMEDECONTACTO, String.class);
    }

    public static Parameter<String> NOMECONDUTOR(Boolean optional) {
        return new Parameter<>("Nome Condutor",optional, InputValidators::NOMECONDUTOR, String.class);
    }

    public static Parameter<String> LONGITUDE(Boolean optional) {
        return new Parameter<>("Longitude",optional, InputValidators::LONGITUDE, String.class);
    }

    public static Parameter<String> LATITUDE(Boolean optional) {
        return new Parameter<>("Latitude",optional, InputValidators::LATITUDE, String.class);
    }

    public static Parameter<String> MARCATEMPORAL(Boolean optional) {
        return new Parameter<>("Marca Temporal",optional, InputValidators::LATITUDE, String.class);
    }

    public static Parameter<Integer> GPSID(Boolean optional) {
        return new Parameter<>("GpsID",optional, InputValidators::INTEGER, Integer.class);
    }

    public static Parameter<Integer> NUMALARMES(Boolean optional) {
        return new Parameter<>("NumAlarmes",optional, InputValidators::INTEGER, Integer.class);
    }

}
