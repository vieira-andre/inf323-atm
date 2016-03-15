package br.unicamp.ic.caixaautomatico.impl;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.caixaautomatico.spec.ICadastroContas;
import br.unicamp.ic.caixaautomatico.spec.IConta;

public class CadastroContas implements ICadastroContas {
	List<ContaBase> registroDeContas = new ArrayList<ContaBase>();

	static int contador;

	@Override
	public IConta buscaConta(int numeroConta) {
		if (this.registroDeContas.contains(numeroConta)) {
			return registroDeContas.get(numeroConta);
		}

		return null;
	}

	@Override
	public IConta criarContaCorrente(String titular, float saldoAtual, int senha) {
		contador++;

		IConta conta = new ContaCor(titular, saldoAtual, contador, senha);

		return conta;
	}

	@Override
	public IConta criarContaEspecial(String titular, float saldoAtual, int senha) {
		contador++;

		IConta conta = new ContaEsp(titular, saldoAtual, contador, senha);

		return conta;
	}

}
