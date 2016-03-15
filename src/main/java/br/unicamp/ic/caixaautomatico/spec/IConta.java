package br.unicamp.ic.caixaautomatico.spec;

public interface IConta {
	float obterSaldo(int pwd);

	String obterExtrato(int pwd);

	boolean debitarValor(String historico, float valor, int pwd);

	boolean creditarValor(String historico, float valor);
}
