package org.craftsmanship.mdtdd.cmd;

import java.io.IOException;
import java.util.Scanner;

import org.craftsmanship.mdtdd.app.MdtddApp;

public class Main {

	private static MdtddApp app = new MdtddApp();

	private enum Action {

		a {
			@Override
			public void print() {
				System.out.print( "Nombre: " );
			}
			@Override
			public void perform( String name ) {
				app.registration( name );
			}
		}, b {
			@Override
			public void print() {
				System.out.print( "Nombres: " );
			}
			@Override
			public void perform(String names) {
				String[] users = names.split(" ");
				app.follow(users[0], users[1]);
			}
		}, c {
			@Override
			public void print() {
				System.out.print( "Nombre: " );
			}
			@Override
			public void perform(String name) {
				System.out.println( app.following( name ) );
			}
		};

		public abstract void print();

		public abstract void perform(String data);
	}


	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		String in;
		do {
			printMenu();
			in = sc.nextLine();
			if( "0".equals( in ) ) {
				sc.close();
				exit();
			}
			doAction( in, sc );
		} while ( true );
	}

	private static void doAction( String in, Scanner sc ) {
		Action action;
		try {
			action = Action.valueOf(in);
			action = Enum.valueOf(Action.class, in);
			action.print();
			action.perform( sc.nextLine() );
		} catch (IllegalArgumentException e) {
			System.out.println( "Opción incorrecta ..." );
		} catch (Exception e) {
			System.out.println( "Error: " + e );
		}
	}

	private static void exit() {
		System.out.print( "Adios!!" );
		System.exit(0);
	}

	private static void printMenu() {
		System.out.println( "TwitterApp" );
		System.out.println( "\ta) registrar usuario" );
		System.out.println( "\tb) seguir un usuario" );
		System.out.println( "\tc) usuarios seguidos" );
		System.out.println( "\t0) salir" );
		System.out.print( ": " );
	}
}