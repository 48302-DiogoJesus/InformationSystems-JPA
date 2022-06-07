package model;

import Utils.Utils.InputState;

public class InputValidators {
    public static InputState NIF(String nif) {
        if (nif.length() != 9) {
            return new InputState(false, "NIF: 9 characters");
        }
        return new InputState(true, null);
    }
    public static InputState NOME(String nome) {
        if (nome.length() <= 10 || nome.length() > 30) {
            return new InputState(false, "Nome: 11-30 characters.");
        }
        return new InputState(true, null);
    }
    public static InputState MORADA(String morada) {
        if (morada.length() <= 10 || morada.length() > 40) {
            return new InputState(false, "Morada: 11-40 characters.");
        }
        return new InputState(true, null);
    }
    public static InputState TELEFONE(String telefone) {
        if (telefone.length() != 9) {
            return new InputState(false, "Telefone: 9 characters.");
        }
        return new InputState(true, null);
    }
    public static InputState CC(String cc) {
        if (cc.length() != 12) {
            return new InputState(false, "CC: 12 characters.");
        }
        return new InputState(true, null);
    }
    public static InputState NOMEDECONTACTO(String nome) {
        if (nome.length() < 8 || nome.length() > 30) {
            return new InputState(false, "Nome de Contacto: 8-30 characters.");
        }
        return new InputState(true, null);
    }
    public static InputState ESTADOGPS(String estado) {
        if (estado.length() > 20) {
            return new InputState(false, "Estado GPS: 1-20.");
        }
        return new InputState(true, null);
    }
    public static InputState MATRICULA(String matricula) {
        if (matricula.length() != 8) {
            return new InputState(false, "Matricula: 8 characters.");
        }
        return new InputState(true, null);
    }
    public static InputState NOMECONDUTOR(String nome) {
        if (nome.length() < 5 || nome.length() > 30) {
            return new InputState(false, "Nome do Condutor: 5-30 characters.");
        }
        return new InputState(true, null);
    }
    public static InputState LONGITUDE(String longitude) {
        if (longitude.length() > 20) {
            return new InputState(false, "Longitude: 1-20 characters.");
        }
        return new InputState(true, null);
    }
    public static InputState LATITUDE(String latitude) {
        if (latitude.length() > 20) {
            return new InputState(false, "Latitude: 1-20 characters.");
        }
        return new InputState(true, null);
    }
    public static InputState MARCATEMPORAL(String marca_temporal) {
        return new InputState(true, null);
    }
    public static InputState INTEGER(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return new InputState(false, "Parametro deve ser inteiro");
        }
        return new InputState(true, null);
    }
}
