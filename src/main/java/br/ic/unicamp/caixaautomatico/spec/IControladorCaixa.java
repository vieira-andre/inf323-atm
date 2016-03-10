package br.ic.unicamp.caixaautomatico.spec;

public interface IControladorCaixa {

	float consultarSaldo(int numeroConta, int pwd);

	boolean efetuarSaque(int numeroConta, int pwd, float valor);

	void recarregar(int pwd);

	boolean validarSenha(int pwd);

}
