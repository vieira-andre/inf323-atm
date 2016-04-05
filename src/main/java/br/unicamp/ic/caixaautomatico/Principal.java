package br.unicamp.ic.caixaautomatico;

import br.unicamp.ic.caixaautomatico.impl.TrmCxAut;

public class Principal {

	public static void main(String[] args) {
		TrmCxAut meuCaixaAut = new TrmCxAut(123, TrmCxAut.MODO_SUPERVISOR);

		meuCaixaAut.iniciarOperacao();
	}

}