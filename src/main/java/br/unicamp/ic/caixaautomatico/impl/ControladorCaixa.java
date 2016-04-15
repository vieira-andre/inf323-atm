package br.unicamp.ic.caixaautomatico.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.unicamp.ic.caixaautomatico.exceptions.DebitarValorException;
import br.unicamp.ic.caixaautomatico.exceptions.EfetuarSaqueException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterExtratoException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterSaldoException;
import br.unicamp.ic.caixaautomatico.exceptions.RecarregarCaixaException;
import br.unicamp.ic.caixaautomatico.spec.ICadastroContas;
import br.unicamp.ic.caixaautomatico.spec.IConta;
import br.unicamp.ic.caixaautomatico.spec.IControladorCaixa;

public class ControladorCaixa implements IControladorCaixa {

	private int senhaDoCaixa;
	private Caixa caixa;
	private ICadastroContas cadastroContas;

	public ControladorCaixa(int senhaCaixa) {
		this.senhaDoCaixa = senhaCaixa;

		this.cadastroContas = new CadastroContas();

		this.caixa = new Caixa(this.senhaDoCaixa);
	}

	@Override
	public float consultarSaldo(int numeroConta, int pwd) throws ObterSaldoException {
		IConta conta = cadastroContas.buscaConta(numeroConta);

		if (conta != null) {
			try {
				return conta.obterSaldo(pwd);
			} catch (ObterSaldoException e) {
				System.out.println(e.getMessage());
			}
		} else {
			throw new ObterSaldoException("Número de conta inválido");
		}

		return -1;
	}

	@Override
	public String consultarExtrato(int numeroConta, int pwd) throws ObterExtratoException {
		IConta conta = cadastroContas.buscaConta(numeroConta);

		if (conta != null) {
			try {
				return conta.obterExtrato(pwd);
			} catch (ObterExtratoException e) {
				System.out.println(e.getMessage());
			}
		} else {
			throw new ObterExtratoException("Número de conta inválido");
		}

		return null;
	}

	@Override
	public boolean efetuarSaque(int numeroConta, int pwd, float valor)
			throws EfetuarSaqueException, DebitarValorException {

		IConta conta = cadastroContas.buscaConta(numeroConta);

		if (conta != null) {
			String historico = getDataAtual() + " - " + "Débito" + " - " + valor;

			if (conta.debitarValor(historico, valor, pwd)) {

				caixa.liberarNotas((int) valor);

				return true;
			}

			return false;
		} else {
			throw new EfetuarSaqueException("Número de conta inválido " + numeroConta);
		}
	}

	@Override
	public void recarregar(int pwd) throws RecarregarCaixaException {
		if (this.validarSenha(pwd)) {
			caixa.recarregar(pwd);
		} else {
			throw new RecarregarCaixaException("Senha incorreta!");
		}
	}

	@Override
	public boolean validarSenha(int pwd) {
		if (pwd == this.senhaDoCaixa) {
			return true;
		}

		return false;
	}

	private String getDataAtual() {
		Calendar data = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		return formato.format(data.getTime()).toString();
	}

}