package br.unicamp.ic.caixaautomatico.impl;

import br.unicamp.ic.caixaautomatico.exceptions.RecarregarCaixaException;
import br.unicamp.ic.caixaautomatico.spec.ICaixa;

public class Caixa implements ICaixa {

	private int senha;
	private float saldoDoCaixa;

	public Caixa(int senhaCaixa) {
		this.senha = senhaCaixa;
	}

	@Override
	public void recarregar(int pwd) throws RecarregarCaixaException {
		if (this.validarSenha(pwd)) {
			this.saldoDoCaixa = 1000;
		} else {
			throw new RecarregarCaixaException("Senha incorreta!");
		}
	}

	@Override
	public float obterSaldoCaixa() {
		return this.saldoDoCaixa;
	}

	@Override
	public boolean validarSenha(int pwd) {
		if (pwd == this.senha) {
			return true;
		}

		return false;
	}

	@Override
	public void liberarNotas(int quantidade) {
		// TODO Auto-generated method stub

	}

}
