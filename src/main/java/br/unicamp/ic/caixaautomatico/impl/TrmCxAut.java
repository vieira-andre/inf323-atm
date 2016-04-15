package br.unicamp.ic.caixaautomatico.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;

import br.unicamp.ic.caixaautomatico.exceptions.DebitarValorException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterSaldoException;
import br.unicamp.ic.caixaautomatico.exceptions.RecarregarCaixaException;
import br.unicamp.ic.caixaautomatico.spec.ITrmCxAut;

public class TrmCxAut implements ITrmCxAut {

	public static int MODO_SUPERVISOR = 0;
	public static int MODO_CLIENTE = 1;

	private ControladorCaixa controladorCaixa;
	private int modoAtual;

	public TrmCxAut(int senhaCaixa, int modoOperacao) {
		controladorCaixa = new ControladorCaixa(senhaCaixa);
		modoAtual = modoOperacao;
	}

	@Override
	public void iniciarOperacao() {
		int op;

		op = getOp();

		while (op != 9) {
			try {
				switch (op) {
				case 1:
					try {
						float saldo = controladorCaixa.consultarSaldo(getInt("número da conta"), getInt("senha"));

						if (saldo != -1) // testa se consulta foi rejeitada
							System.out.println("Saldo atual: " + saldo);
					} catch (ObterSaldoException e) {
						System.out.println(e.getMessage());
					}

					break;
				case 2:
					boolean b = false;

					try {
						b = controladorCaixa.efetuarSaque(getInt("número da conta"), getInt("senha"), getInt("valor"));
					} catch (DebitarValorException e) {
						System.out.println(e.getMessage());
					}

					if (b) // testa se saque foi aceito
						System.out.println("Pode retirar o dinheiro");
					else
						System.out.println("Pedido de saque recusado");

					break;
				case 3:
					try {
						controladorCaixa.recarregar(getInt("senha"));

						System.out.println("Recarga realizada");
					} catch (RecarregarCaixaException e) {
						System.out.println(e.getMessage());
					}

					break;
				case 8:
					this.alternarModo(getInt("senha do supervisor"));

					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			op = getOp();
		}
	}

	@Override
	public void alternarModo(int senhaSupervisor) {
		try {
			controladorCaixa.validarSenha(senhaSupervisor);

			if (modoAtual == TrmCxAut.MODO_SUPERVISOR)
				modoAtual = TrmCxAut.MODO_CLIENTE;
			else
				modoAtual = TrmCxAut.MODO_SUPERVISOR;

			System.out.println("Alternância de modo realizada");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int getOp() {
		int op;

		do {
			if (modoAtual == 1) { // modo cliente
				op = getInt("opcao: 1 = consulta saldo, 2 = saque, 8=modo supervisor, 9=sai");
				if (op != 1 && op != 2 && op != 8 && op != 9)
					op = 0;
			} else { // modo supervisor
				op = getInt("opcao: 3 = recarrega, 8=modo cliente, 9=sai");
				if (op != 3 && op != 8 && op != 9)
					op = 0;
			}
		} while (op == 0);

		return (op);
	}

	@Override
	public int getInt(String str) {
		Reader r = new BufferedReader(new InputStreamReader(System.in));

		StreamTokenizer st = new StreamTokenizer(r);

		System.out.println("Entre com " + str);

		try {
			st.nextToken();
		} catch (IOException e) {
			System.out.println("Erro na leitura do teclado");
			return (0);
		}

		return ((int) st.nval);
	}
}
