package br.unicamp.ic.caixaautomatico.impl;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.caixaautomatico.spec.ICadastroContas;
import br.unicamp.ic.caixaautomatico.spec.IConta;

public class CadastroContas implements ICadastroContas {
	static List<IConta> registroDeContas = new ArrayList<IConta>();

	static int contador;

	public CadastroContas() {
		// this.criarContaCorrente("Ursula", 500, 1);
		// System.out.println("Criada conta 1 senha 1 com 500,00");
		//
		// this.criarContaCorrente("Mia", 500, 2);
		// System.out.println("Criada conta 2 senha 2 com 500,00");
		//
		// this.criarContaCorrente("Alfredo", 500, 3);
		// System.out.println("Criada conta 3 senha 3 com 500,00");
	}

	@Override
	public IConta buscaConta(int numeroConta) {
		if (numeroConta <= registroDeContas.size()) {
			return registroDeContas.get(numeroConta - 1);
		}

		return null;
	}

	@Override
	public IConta criarContaCorrente(String titular, float saldoAtual, int senha) {
		contador++;

		IConta conta = new ContaCor(titular, saldoAtual, contador, senha);
		registroDeContas.add(conta);

		return conta;
	}

	@Override
	public IConta criarContaEspecial(String titular, float saldoAtual, int senha) {
		contador++;

		IConta conta = new ContaEsp(titular, saldoAtual, contador, senha);
		registroDeContas.add(conta);

		return conta;
	}

}
