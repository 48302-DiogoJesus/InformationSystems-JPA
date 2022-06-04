package model;

import DataScopes.DataScope;
import model.Entities.Cliente;
import model.Entities.EstadosGps;
import model.Entities.Gps;
import model.Entities.Veiculo;

import java.util.HashMap;

public class ParameterValidOptions {

    private static final int RESULTS_LIMIT = 5;

    static HashMap<String, String> Clientes() {
        HashMap<String, String> options = new HashMap<>();
        try (DataScope<Cliente, String> ds = new DataScope<>(Cliente.class)) {
            for (Cliente c: ds.getAll(RESULTS_LIMIT)) {
                options.put(c.getPK(), "Nif: " + c.getPK() + " | Nome: " + c.getNome() + " | Telefone: " + c.getTelefone());
            }
            ds.validateWork();
            return options;
        } catch (Exception e) {
            System.out.println("Could not fetch 'clientes' from database: " + e.getMessage());
        }
        return options;
    }

    static HashMap<String, String> Gps() {
        HashMap<String, String> options = new HashMap<>();
        try (DataScope<Gps, Integer> ds = new DataScope<>(Gps.class)) {
            for (Gps c: ds.getAll(RESULTS_LIMIT)) {
                options.put(c.getPK().toString(), "GPS Id: " +  c.getPK().toString());
            }
            ds.validateWork();
            return options;
        } catch (Exception e) {
            System.out.println("Could not fetch 'gps' from database: " + e.getMessage());
        }
        return options;
    }

    static HashMap<String, String> EstadosGPS() {
        HashMap<String, String> options = new HashMap<>();
        try (DataScope<EstadosGps, String> ds = new DataScope<>(EstadosGps.class)) {
            for (EstadosGps e: ds.getAll(RESULTS_LIMIT)) {
                options.put(e.getPK(), "Estado GPS: " + e.getEstado());
            }
            ds.validateWork();
            return options;
        } catch (Exception e) {
            System.out.println("Could not fetch 'Estados GPS' from database: " + e.getMessage());
        }
        return options;
    }

    static HashMap<String, String> Veiculos() {
        HashMap<String, String> options = new HashMap<>();
        try (DataScope<Veiculo, String> ds = new DataScope<>(Veiculo.class)) {
            for (Veiculo v: ds.getAll(RESULTS_LIMIT)) {
                options.put(v.getPK(),
                        "Matrícula: " + v.getMatricula() +
                        " | Número de alarmes: " + v.getNum_alarmes() +
                        " | Estado GPS: " + v.getEstado_gps().getEstado() +
                        " | Condutor: " + v.getNome_condutor() +
                        " | Telefone do Condutor: " + v.getTelefone_condutor()
                );
            }
            ds.validateWork();
            return options;
        } catch (Exception e) {
            System.out.println("Could not fetch 'Veiculos' from database: " + e.getMessage());
        }
        return options;
    }
}
