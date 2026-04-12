package com.universidad.grupo05.exception;

public class RecursoNoEncontradoException extends RuntimeException {
    private final String recurso;
    private final String campo;
    private final Object valor;

    public RecursoNoEncontradoException(String recurso, String campo, Object valor) {
        super(String.format("%s no encontrado con %s : '%s'", recurso, campo, valor));
        this.recurso = recurso;
        this.campo = campo;
        this.valor = valor;
    }

    public String getRecurso() { return recurso; }
    public String getCampo() { return campo; }
    public Object getValor() { return valor; }
}