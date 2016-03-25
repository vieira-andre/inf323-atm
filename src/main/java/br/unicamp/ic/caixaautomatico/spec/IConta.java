package br.unicamp.ic.caixaautomatico.spec;

import br.unicamp.ic.caixaautomatico.exceptions.CreditarValorException;
import br.unicamp.ic.caixaautomatico.exceptions.DebitarValorException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterExtratoException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterSaldoException;

public interface IConta {
	float obterSaldo(int pwd) throws ObterSaldoException;

	String obterExtrato(int pwd) throws ObterExtratoException;

	boolean debitarValor(String historico, float valor, int pwd) throws DebitarValorException;

	boolean creditarValor(String historico, float valor) throws CreditarValorException;
}
