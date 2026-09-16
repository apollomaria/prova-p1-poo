package br.com.starlog.model;

public class Carga {
    // private final pois codigoRastreio é um atributo privado e que possui imutabilidade após a criação do objeto
    private final String codigoRastreio;
    private String categoria;
    private double pesoKg;
    private double valorSeguro;

    public Carga(String codigoRastreio, String categoria, double pesoKg, double valorSeguro) {
        // validação fail-fast no construtor de codigoRastreio
        if (codigoRastreio == null || codigoRastreio.trim().isEmpty()) {
            throw new IllegalArgumentException("Codigo de rastreio da carga nao pode ser nulo ou vazio.");
        }

        // princípio fail-fast aplicado em todos os atributos do construtor para garantir integridade do estado em memória
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria da carga nao pode ser nula ou vazia.");
        }
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("Peso precisa ter valor estritamente positivo.");
        }
        if (valorSeguro <= 0) {
            throw new IllegalArgumentException("Valor do Seguro precisa ter valor estritamente positivo.");
        }

        this.codigoRastreio = codigoRastreio;
        this.categoria = categoria;
        this.pesoKg = pesoKg;
        this.valorSeguro = valorSeguro;
    }

    // toString sobrescrito para retornar o padrao textual desejado na regra de negocio01
    @Override
    public String toString() {
        return "Carga [rastreio=" + codigoRastreio + ", categoria=" + categoria + ", peso=" + pesoKg
                + "kg, seguro R$=" + valorSeguro + "]";
    }


    // metodos hashCode() e equals() baseados no codigoRastreio para garantir que instancias distintas na RAM com codigos iguais sejam tratadas como duplicatas
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoRastreio == null) ? 0 : codigoRastreio.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Carga other = (Carga) obj;
        if (codigoRastreio == null) {
            if (other.codigoRastreio != null)
                return false;
        } else if (!codigoRastreio.equals(other.codigoRastreio))
            return false;
        return true;
    }



    // metodos getters de todos os atributos
    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    // metodos setters dos atributos que nao sao o codigoRastreio, pois este, uma vez que criado, é imutavel (ao contrario dos demais)
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

}
