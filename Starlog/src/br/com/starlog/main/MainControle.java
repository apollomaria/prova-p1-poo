package br.com.starlog.main;

import java.util.HashSet;

import br.com.starlog.exception.CapacidadeExcedidaException;
import br.com.starlog.model.BaseLancamento;
import br.com.starlog.model.Carga;
import br.com.starlog.model.ModuloCarga;

public class MainControle {
    public static void main(String[] args) {
        // P01
        // instanciacao das 4 cargas
        Carga c1 = new Carga("ORB-101-SP", "CRIOGENICA", 2.5, 450);
        Carga c2 = new Carga("ORB-102-RJ", "PADRAO", 8, 120);
        Carga c3 = new Carga("ORB-103-MG", "CRIOGENICA", 12, 850);
        Carga c4 = new Carga("ORB-104-PR", "BIOLOGICA", 15, 300);

        // mensagens de saida no console
        System.out.println(c1);
        System.out.println(c4);





        // P02
        // instanciacao do modulo orbital
        ModuloCarga modulo = new ModuloCarga("MOD-ALFA-01", 3);
        
        // instanciacao da base
        BaseLancamento base = new BaseLancamento();

        // cadastro do modulo na base
        base.cadastrarModulo(modulo);

        // o sout da confirmacao do cadastro ja esta inserida diretamente no proprio metodo cadastrarModulo
        // linha 24 de BaseLancamento:    
        // System.out.println("Modulo '" + moduloCarga.getCodigoModulo() + "' cadastrado na base com capacidade de " + moduloCarga.getCapacidadeMaxima() + " cargas.");





        // P03
        // como o metodo carregarCarga() tem um throws, é preciso deixa-lo em torno de um bloco try catch
        try {
            // carregamento de 3 cargas no modulo
            modulo.carregarCarga(c1);
            modulo.carregarCarga(c2);
            modulo.carregarCarga(c3);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }



        // P04
        try {
            // carregamento da 4 carga, ou seja, a que excede a capacidade maxima do modulo
            modulo.carregarCarga(c4);
        } catch (CapacidadeExcedidaException e) {
            // disparo do erro de capacidade maxima
            System.out.println("Excecao capturada: " + e.getMessage());
        }



        // P05 
        // como o metodo buscarModulo retorna um objeto ModuloCarga, criamos um novo objeto que recebe esse return
        ModuloCarga pesquisado = base.buscarModulo("MOD-ALFA-01");

        // mensagem de saida com o codigo do objeto que foi localizado na base
        System.out.println("Modulo localizado na base: " + pesquisado.getCodigoModulo());



        // P06
        // sout do Stream de calculo dos valorSeguro 
        System.out.println("Seguro total no modulo: R$" + modulo.calcularSeguroTotal());



        // P07
        // sout do count() por categoria
        System.out.println("Cargas CRIOGENICA: " + modulo.contarCargasPorCategoria("CRIOGENICA"));


        // P08
        // sout do metodo de cargas pesadas
        System.out.println("Seguro de cargas criticas (CRIOGENICA > 5kg): R$" + modulo.calcularSeguroCargasPesadas("CRIOGENICA", 5));



        // P09
        // criacao do hashset
        HashSet<Carga> manifesto = new HashSet<>();
        
        // adicao da carga c1
        manifesto.add(c1);

        // instancia do clone
        Carga clone = new Carga("ORB-101-SP", "CRIOGENICA", 9, 990);
        
        // adicao do clone e da carga c2 no hashset criado
        manifesto.add(clone);
        manifesto.add(c2);

        // tamanho do manifesto para conferir o bloqueio de duplicatas por conta do equals() e do hashcode() no codigoRastreio
        System.out.println("Tamanho do manifesto (HashSet): " + manifesto.size());


        try {
            // criacao de uma carga que viola as regras do construtor
            Carga testeErro = new Carga("", "PADRAO", 1, 50);
        } catch (IllegalArgumentException e) {
            // disparo do erro do construtor
            System.out.println("Construtor validado: " + e.getMessage());
        }

    }
}
