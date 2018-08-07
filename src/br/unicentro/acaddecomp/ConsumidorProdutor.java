package br.unicentro.acaddecomp;

public class ConsumidorProdutor implements Runnable{
	Thread produtor, consumidor;
	Deposito deposito;
	String tipo;
	
	public ConsumidorProdutor(String nome, String tipo, Deposito deposito){
		if (tipo == "Produtor"){
			produtor = new Thread (this, nome);
			this.tipo = tipo;
			this.deposito = deposito;
			produtor.start();
		} else {
			consumidor = new Thread (this, nome);
			this.tipo = tipo;
			this.deposito = deposito;
			consumidor.start();
		}
	}
	public void run(){
		int i;
		if (tipo == "Produtor"){
			System.out.println(tipo + " " + produtor.getName() + " Iniciando...");
			try {
				for (i=0; i<20; i++){
					Thread.sleep(800);
					System.out.println(tipo + " " + produtor.getName() + " Depositando 1 item " + "Quantidade (" + deposito.getItems() + ")");
					deposito.armazenar(true);
					deposito.armazenar(false);
				}
			} catch (InterruptedException exc){
				System.out.println(tipo + " " + produtor.getName() + " Interrompido!");
			}
			System.out.println(tipo + " " + produtor.getName() + " Terminado!");
		} else if (tipo == "Consumidor") {
			System.out.println(tipo + " " + consumidor.getName() + " Iniciando...");
			try {
				for (i=0; i<20; i++){
					Thread.sleep(800);
					System.out.println(tipo + " " + consumidor.getName() + " Retirando 1 item " + "Quantidade (" + deposito.getItems() + ")");
					deposito.retirar(true);
					deposito.retirar(false);
				}
			} catch (InterruptedException exc){
				System.out.println(tipo + " " + consumidor.getName() + " Interrompido!");
			}
			System.out.println(tipo + " " + consumidor.getName() + " Terminado!");
		}
	}
}
