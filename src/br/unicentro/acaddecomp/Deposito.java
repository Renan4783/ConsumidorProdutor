package br.unicentro.acaddecomp;

public class Deposito {
	private int items;
	String estado;

	public Deposito() {
		this.items = 0;
	}

	synchronized void armazenar(boolean running) {
		if (!running) {
			if (items < 10) {
				items += 1;
				estado = "Armazenando";
				notify();
				return;
			} else {
				System.out.println("Deposito cheio!");
				notify();
				return;
			}
		}
		if (items < 10) {
		System.out.println("Armazenado!");
		estado = "Armazenando";
		items += 1;
		notify();
		try {
			while (!estado.equals("Retirando")) {
				wait();
			}
		} catch (InterruptedException exc) {
			System.out.println("Thread Interrompida");
		}
		} else {
			System.out.println("Deposito cheio!");
			notify();
			return;
		}
	}

	synchronized void retirar(boolean running) {
		if (!running) {
			if (items < 1) {
				System.out.println("Deposito vazio!");
				notify();
				return;
			} else {
				items -= 1;
				estado = "Retirando";
				notify();
				return;
			}
		}
		if (items < 1) {
			System.out.println("Deposito vazio!");
			notify();
			return;
		} else {
			System.out.println("Retirado!");
			estado = "Retirando";
			items -= 1;
			notify();
			try {
				while (!estado.equals("Armazenando")) {
					wait();
				}
			} catch (InterruptedException exc) {
				System.out.println("Thread Interrompida");
			}
		}
	}

	int getItems() {
		return items;
	}
}
