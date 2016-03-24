package br.unicamp.ic.caixaautomatico.impl;

import br.unicamp.ic.caixaautomatico.exceptions.CreditarValorException;
import br.unicamp.ic.caixaautomatico.exceptions.DebitarValorException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterExtratoException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterSaldoException;

public class ContaCor extends ContaBase {

	public ContaCor(String titular, float saldoAtual, int numConta, int senha) {
		super(titular, saldoAtual, numConta, senha);
	}

	@Override
	public float obterSaldo(int pwd) throws ObterSaldoException {
		return super.obterSaldo(pwd);
	}

	@Override
	public String obterExtrato(int pwd) throws ObterExtratoException {
		return super.obterExtrato(pwd);
	}

	@Override
	public boolean debitarValor(String historico, float valor, int pwd) throws DebitarValorException {
		return super.debitarValor(historico, valor, pwd);
	}

	@Override
	public boolean creditarValor(String historico, float valor) throws CreditarValorException {
		return super.creditarValor(historico, valor);
	}
	
	@Override
	public void setLimiteDeSaque(float valor) {
		super.setLimiteDeSaque(valor);
	}

}
