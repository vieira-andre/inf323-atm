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

		// this.criarContaCorrente("Titular 1", 1000, 1234);
		// this.criarContaEspecial("Titular 2", 2000, 9876);
		// this.criarContaCorrente("Titular 3", 1750, 5678);
		// this.criarContaEspecial("Titular 4", 2700, 5432);
		// this.criarContaCorrente("Titular 5", 1500, 9012);
		// this.criarContaEspecial("Titular 6", 3000, 1098);
		// this.criarContaCorrente("Titular 7", 800, 3456);
		// this.criarContaEspecial("Titular 8", 4000, 7654);
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
