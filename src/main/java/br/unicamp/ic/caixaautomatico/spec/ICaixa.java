package br.unicamp.ic.caixaautomatico.spec;

public interface ICaixa {

	void recarregar(int pwd);

	float obterSaldoCaixa();

	boolean validarSenha(int pwd);

	void liberarNotas(int quantidade);

}
