package br.unicamp.ic.caixaautomatico.spec;

import br.unicamp.ic.caixaautomatico.exceptions.RecarregarCaixaException;

public interface ICaixa {

	void recarregar(int pwd) throws RecarregarCaixaException;

	float obterSaldoCaixa();

	boolean validarSenha(int pwd);

	void liberarNotas(int quantidade);

}
