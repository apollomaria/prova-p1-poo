package br.com.starlog.exception;

// rn02 exceçao checada com classe personalizada que repassa a mensagem que lhe é passada por parametro
public class CapacidadeExcedidaException extends Exception {
    public CapacidadeExcedidaException(String mensagem) {
        super(mensagem);
        // sobrescricao do erro de Exception para a nossa mensagem personalizada
    }
}
