package br.com.starlog.model;

import java.util.HashMap;
import java.util.Map;

public class BaseLancamento {
    // hashMap privado
    private Map<String, ModuloCarga> modulos;

    // construtor vazio que instancia o hashMap 
    public BaseLancamento() {
        this.modulos = new HashMap<>();
    }
    
    public void cadastrarModulo(ModuloCarga moduloCarga) {
        // metodo failfast no metodo, para garantir integridade da memoria
        if (moduloCarga == null) {
            throw new IllegalArgumentException("ModuloCarga nao pode ser nulo ou vazio.");
        }

        // adicionando a chave do modulo e o modulo passados por parametro no hashmap
        modulos.put(moduloCarga.getCodigoModulo(), moduloCarga);

        System.out.println("Modulo '" + moduloCarga.getCodigoModulo() + "' cadastrado na base com capacidade de " + moduloCarga.getCapacidadeMaxima() + " cargas.");
    }

    // busca super eficiente no hashmap
    public ModuloCarga buscarModulo(String codigo) {
        // metodo failfast no metodo, para garantir integridade da memoria
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Codigo nao pode ser nulo ou vazio.");
        }

        return this.modulos.get(codigo);
    }

    // metodo get conforme diagrama de classes uml
    public Map<String, ModuloCarga> getModulos() {
        return modulos;
    }

}
