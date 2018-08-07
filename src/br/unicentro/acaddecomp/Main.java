package br.unicentro.acaddecomp;

public class Main {
	public static void main (String args[]){
		Deposito d1 = new Deposito();
		ConsumidorProdutor p1 = new ConsumidorProdutor("Banco", "Produtor", d1);
		ConsumidorProdutor c1 = new ConsumidorProdutor("Cliente", "Consumidor", d1);
		try {
			p1.produtor.join();
			c1.consumidor.join();
		} catch (InterruptedException exc){
			System.out.println("Fork Interrompido!");
		}
	}
}
