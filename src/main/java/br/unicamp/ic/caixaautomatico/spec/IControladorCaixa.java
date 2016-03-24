package br.unicamp.ic.caixaautomatico.spec;

import br.unicamp.ic.caixaautomatico.exceptions.DebitarValorException;
import br.unicamp.ic.caixaautomatico.exceptions.EfetuarSaqueException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterExtratoException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterSaldoException;
import br.unicamp.ic.caixaautomatico.exceptions.RecarregarCaixaException;

public interface IControladorCaixa {

	float consultarSaldo(int numeroConta, int pwd) throws ObterSaldoException;

	String consultarExtrato(int numeroConta, int pwd) throws ObterExtratoException;

	boolean efetuarSaque(int numeroConta, int pwd, float valor) throws EfetuarSaqueException, DebitarValorException;

	void recarregar(int pwd) throws RecarregarCaixaException;

	boolean validarSenha(int pwd);

}
