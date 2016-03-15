package br.unicamp.ic.caixaautomatico.impl;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContaEsp extends ContaBase {
	public static int ATIVA = 1;
	public static int ENCERRADA = 2;

	private int estado;
	private float saldoAnterior;
	private List<String> datas;
	private List<String> historico;
	private List<Float> valorLanc;
	private int ultLanc;

	private String titular;
	private float saldoAtual;
	private int numConta;
	private int senha;

	private float limiteDeSaque = 500;

	public ContaEsp(String titular, float saldoAtual, int numConta, int senha) {
		this.estado = ATIVA;

		this.titular = titular;
		this.saldoAtual = saldoAtual;
		this.numConta = numConta;
		this.senha = senha;

		this.datas = new ArrayList<String>();
		this.historico = new ArrayList<String>();
		this.valorLanc = new ArrayList<Float>();
		this.ultLanc = this.valorLanc.size() - 1;
	}

	@Override
	public boolean debitarValor(String historico, float valor, int pwd) {
		if (isSacavel(pwd, valor)) {
			this.saldoAnterior = this.saldoAtual;
			this.saldoAtual -= valor;

			this.datas.add(getDataAtual());
			this.historico.add(historico);
			this.valorLanc.add(valor);

			return true;
		}

		return false;
	}

	private String getDataAtual() {
		Calendar data = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		return formato.format(data.getTime()).toString();
	}

	@Override
	public boolean creditarValor(String historico, float valor) {
		if (this.estado == ATIVA && valor > 0) {
			this.saldoAnterior = this.saldoAtual;
			this.saldoAtual += valor;

			this.datas.add(getDataAtual());
			this.historico.add(historico);
			this.valorLanc.add(valor);

			return true;
		}

		return false;
	}

	@Override
	public float obterSaldo(int pwd) {
		if (pwd == this.senha && this.estado == ATIVA) {
			return this.saldoAtual;
		}

		return -1;
	}

	@Override
	public String obterExtrato(int pwd) {
		if (pwd == this.senha && this.estado == ATIVA) {
			StringBuilder sb = new StringBuilder();

			sb.append("Cliente: " + this.titular);
			sb.append("\n");
			sb.append("Conta: " + this.numConta);
			sb.append("\n\n");

			for (int i = 0; i < datas.size(); i++) {
				for (int x = 0; x < historico.size(); x++) {
					for (int y = 0; y < valorLanc.size(); y++) {
						float valorAtualizado = valorLanc.get(y);

						if (historico.get(x).equals(normalizaString("Débito").toLowerCase())) {
							valorAtualizado *= -1;
						}

						sb.append(datas.get(i) + " - " + historico.get(x) + " - "
								+ String.format("%.2f", valorAtualizado));

						sb.append("\n");

						x++;
						i++;
					}
				}
			}

			sb.append("SALDO: " + this.saldoAtual);

			sb.toString();
		}

		return null;
	}

	private String normalizaString(String string) {
		return Normalizer.normalize(string, Form.NFD);
	}

	private boolean isSacavel(int pwd, float valor) {
		if (pwd == this.senha) {
			if (isContaAtiva()) {
				if (areValorESaldoOk(valor)) {
					if (isValorDentroDoLimite(valor)) {
						if (isValorMultiplo10(valor)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	private boolean isContaAtiva() {
		if (this.estado == ATIVA) {
			return true;
		}

		return false;
	}

	private boolean areValorESaldoOk(float valor) {
		if (valor > 0 && this.saldoAtual >= valor) {
			return true;
		}

		return false;
	}

	private boolean isValorDentroDoLimite(float valor) {
		if (valor <= this.limiteDeSaque) {
			return true;
		}

		return false;
	}

	private boolean isValorMultiplo10(float valor) {
		if (valor % 10 == 0) {
			return true;
		}

		return false;
	}

}
