package br.ic.unicamp.caixaautomatico.impl;

import br.ic.unicamp.caixaautomatico.spec.IControladorCaixa;

public class ControladorCaixa implements IControladorCaixa {

	public ControladorCaixa(int senhaCaixa) {
		// TODO código do construtor
	}

	@Override
	public float consultarSaldo(int numeroConta, int pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean efetuarSaque(int numeroConta, int pwd, float valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void recarregar(int pwd) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validarSenha(int pwd) {
		// TODO Auto-generated method stub
		return false;
	}

}
