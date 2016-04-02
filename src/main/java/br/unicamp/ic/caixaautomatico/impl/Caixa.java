package br.unicamp.ic.caixaautomatico.impl;

import br.unicamp.ic.caixaautomatico.exceptions.RecarregarCaixaException;
import br.unicamp.ic.caixaautomatico.spec.ICaixa;

public class Caixa implements ICaixa {

	private int senha;
	private float saldoDoCaixa;

	public Caixa(int senhaCaixa) {
		this.senha = senhaCaixa;
		this.saldoDoCaixa = 0;
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
		this.saldoDoCaixa -= quantidade;

		for (int i = 0; i < quantidade; i++) {
			System.out.println("===/ R$ 10,00 /===>");
		}
	}

}
