package br.com.starlog.model;

import java.util.ArrayList;
import java.util.List;

import br.com.starlog.exception.CapacidadeExcedidaException;

public class ModuloCarga {
    // criacao dos atributos privados da classe
    private List<Carga> cargas;;
    private String codigoModulo;
    private int capacidadeMaxima;

    public ModuloCarga(String codigoModulo, int capacidadeMaxima) {
        // validações fail-fast no construtor
        if (capacidadeMaxima <= 0) {
            throw new IllegalArgumentException("Capacidade do modulo nao pode ser igual a 0 ou negativa.");
        }
        if (codigoModulo == null || codigoModulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Codigo do modulo nao pode ser nulo ou vazio.");
        }

        this.codigoModulo = codigoModulo;
        this.capacidadeMaxima = capacidadeMaxima;

        // criação da arraylist com tamanho especifico especificado na hora da instanciação em MainControle
        this.cargas = new ArrayList<>(capacidadeMaxima);
    }

    // metodo com excecao para checar se a quantidade atual de cargas atingiu ou excedeu a capacidadeMaxima da arraylist
    public void carregarCarga(Carga carga) throws CapacidadeExcedidaException {
        if (cargas.size() >= capacidadeMaxima) {
            // disparo do erro
            throw new CapacidadeExcedidaException("Modulo '" + getCodigoModulo() + "' atingiu a capacidade maxima de " + getCapacidadeMaxima() + " cargas.");
        }

        // carregando a carga na lista
        cargas.add(carga);
        System.out.println("Carga " + carga.getCodigoRastreio() + " carregada no modulo com sucesso.");
    }

    
    // streams especificas para cada um dos metodos
    public double calcularSeguroTotal() {
        return this.cargas.stream()
                            // dá a partida no pipeline 

                            .mapToDouble(c -> c.getValorSeguro())
                            // "pega" todos os valores de seguro

                            .sum()
                            // soma todos os valores pegos pelo mapToDouble
                            ;
    }

    public long contarCargasPorCategoria(String categoria) {
        return this.cargas.stream()
                            .filter(c -> c.getCategoria().equalsIgnoreCase(categoria))
                            // filtra todas as cargas na esteira e so deixa passar as que correspondem à categoria passada por parametro

                            .count()
                            // conta todas as cargas que foram deixadas passar
                            ;
    }
    
    public double calcularSeguroCargasPesadas(String categoria, double pesoMinimo) {
        return this.cargas.stream()
                            .filter(c -> c.getCategoria().equalsIgnoreCase(categoria))
                            // filtra as cargas com categoria igual ao parametro passado

                            .filter(c -> c.getPesoKg() > pesoMinimo)
                            // filtra as cargas que sao maiores que o pesoMinimo passado por parametro

                            .mapToDouble(c -> c.getValorSeguro())
                            // pega os valores do seguro das cargas filtradas

                            .sum()
                            // soma todos os valores 
                            ;

    }


    // metodos getters sem setters, conforme especificado no diagrama de classes uml
    public List<Carga> getCargas() {
        return cargas;
    }

    public String getCodigoModulo() {
        return codigoModulo;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

}
