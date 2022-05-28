package BusinessLogic;

import Utils.Utils;

public class BLService
{
    /*
    EXEMPLO DE UTILIZAÇÃO MAIS COMPLEXO
    public void insertClienteParticular(
            String nif,
            String cc,
            String nome,
            String morada,
            String id_referenciador,
            String telefone
    ) {
        try (
                AbstractDataScope<Cliente, String> ds_cliente = new AbstractDataScope<>(Cliente.class);
                AbstractDataScope<ClienteParticular, Cliente> ds_clienteparticular = new AbstractDataScope<>(ClienteParticular.class)
        ) {
            Cliente referenciador = ds_cliente.getSingle(id_referenciador);
            if (referenciador == null)
                throw new Exception("Referenciador não existente");

            Cliente newCliente = new Cliente();
            newCliente.setNif(nif);
            newCliente.setReferenciador(referenciador);
            newCliente.setNome(nome);
            newCliente.setMorada(morada);
            newCliente.setTelefone(telefone);

            ds_cliente.create(newCliente);

            ClienteParticular cp = new ClienteParticular();
            cp.setCc(cc);
            cp.setId_cliente(newCliente);

            ds_clienteparticular.create(cp);

            // Vote
            ds_cliente.validateWork();
            ds_clienteparticular.validateWork();
        } catch (Exception e) {
            System.out.println("Application Exception: " + e.getMessage());
        }
    }
    */

    public void insertClienteParticular(
        String nif,
        String cc,
        String nome,
        String morada,
        String id_referenciador,
        String telefone
    ) {
        Object[] args = {nif, cc, nome, morada, id_referenciador, telefone};
        Utils.CallStoredProcedure("insert_cliente_particular", args);

    }
}
