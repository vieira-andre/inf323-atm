package br.unicamp.ic.caixaautomatico.impl;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.caixaautomatico.spec.ICadastroContas;
import br.unicamp.ic.caixaautomatico.spec.IConta;

public class CadastroContas implements ICadastroContas {
	static List<IConta> registroDeContas = new ArrayList<IConta>();

	static int contador;

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
		conta.setLimiteDeSaque(200f);

		registroDeContas.add(conta);

		return conta;
	}

	@Override
	public IConta criarContaEspecial(String titular, float saldoAtual, int senha) {
		contador++;

		IConta conta = new ContaEsp(titular, saldoAtual, contador, senha);
		conta.setLimiteDeSaque(500f);

		registroDeContas.add(conta);

		return conta;
	}

}
