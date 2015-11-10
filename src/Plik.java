import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.jblas.*;

public class Plik {
	public static double [][] new_data;
	public static ArrayList<String> data;
	public static double [] theta;
	public static double [] y;
	
	 public static void main(String[] args) {
	        String nazwaPliku = "train.csv";
	        try {
				data = odczytajPli();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        new_data=konwersja(data);
	        zapiszPlik(nazwaPliku, new_data);
	        theta = thetaFun(new_data);
	        wynik(theta);
	    }
	 
	 public static void wynik(double []theta){
		 for(int i=0;i<theta.length;i++){
			 System.out.println("theta =");System.out.println(theta[i]);
		 }
	 }
	 
	    public static void zapiszPlik(String nazwaPliku, double[][] dane) {
	        Path sciezka = Paths.get(nazwaPliku);
	        ArrayList<String> out = new ArrayList<>();
	        // Pobranie kolejnego "rz�du" danych
	        for (double[] seria : dane) {
	            String s = Arrays.toString(seria);
	            out.add(s);
	        }
	        try {
	            Files.write(sciezka, out);
	        } catch (IOException ex) {
	            System.out.println("Nie mog� zapisa� pliku!");
	        }
	    }
	    
	    public static ArrayList<String> odczytajPli() throws FileNotFoundException {
	        ArrayList<String> odczytuje = new ArrayList<String>();
	        File csv = new File("train.csv");
			Scanner odczyt = new Scanner(csv);
			
	        while(odczyt.hasNextLine()){
	        	odczytuje.addAll(new ArrayList<String>(Arrays.asList(odczyt.nextLine().split(","))));
	        	}
	        return odczytuje;
	    }
	        
	    public static ArrayList<String> odczytajPlik(String nazwaPliku) {
	        Path sciezka = Paths.get(nazwaPliku);
	        ArrayList<String> odczyt = new ArrayList<String>();
	        try {
	            odczyt = (ArrayList) Files.readAllLines(sciezka);
	        } catch (IOException ex) {
	            System.out.println("Brak pliku!");
	        }
	        return odczyt;
	    }
	 
	    public static double [][] konwersja(ArrayList<String> dane) {
	        // Tablica dla odczytanych danych
	        double[][] daneOdczytane = new double[dane.size()][];
	        int nrLinii = 0;
	        // Pobranie kolejnych linii z listy
	        for (String linia : dane) {
	            // Rozbijamy lini� na poszczeg�lne linie (przedzielone przecinkami)
	            String[] liniaDaneString = linia.split(",");
	            y[nrLinii]=Double.parseDouble(liniaDaneString[2]);
	            
	            //Tutaj wstawiamy deskryptor
	            String [] rows = new String [2];
	            rows[0]=liniaDaneString[7];
	            rows[1]=liniaDaneString[8];
	            Double [] added = Matrix_descri_maker.rowParser(rows);
	            
	            int liniaDoubleSize = liniaDaneString.length - 2 + added.length;//dolozyc rozmiar dodanych tablic
	            // Tablica do przechowania danych w fomie liczb double
	            double[] liniaDouble = new double[liniaDoubleSize]; 
	            // P�tla, kt�ra pobiera z tablicy String-�w po jednej liczbie,
	            // konwertuje j� na liczb� double i zapisuje w tablicy typu double[]
	            for (int i = 0; i < liniaDouble.length-2; i++) {
	                liniaDouble[i] = Double.parseDouble(liniaDaneString[i]);
	            }
	            //p�tla dodaj�ca wygenerowan� przez deskryptor
	            int k=0;
	            for(int i = liniaDouble.length; i < liniaDoubleSize; i++){
	            	liniaDouble[i]=added[k];
	            	k++;
	            }
	            // Dodajemy tablic� z seri� danych do tablicy z wszystkimi danymi
	            daneOdczytane[nrLinii] = liniaDouble;
	            nrLinii++;
	        }
	        return daneOdczytane;
	    }
	    
	    public static double [] thetaFun (double [][] new_data){
	    	// przepisanie mew_data na macierz
	    	DoubleMatrix X = new DoubleMatrix(new_data);
	    	// stworzenie macierzy transponowonej new_data
	    	DoubleMatrix X_t = X.transpose();
	    	DoubleMatrix y_m = new DoubleMatrix(y);
	    	// Macierz thetaM jest przedstawieniem wsp�czynnik�w w postaci macierzowej
	    	DoubleMatrix thetaM = (Solve.pinv(((X_t.mmul(X)))).mmul(X_t)).mmul(y_m);
	    	
	    	//nie pami�tam czym jest y
	    	// podejrzenia �� wektor y jest:
	    	//								wektorem z�orzonym z samych wynik�w czyli z (2) kolumny
	    	
	    	// utworzenie tymczasowej talicu do odczytania theta z macietzy thetaM
	    	double [] new_theta = new double [new_data.length];
	    	//p�tla odczytuj�ca
	    	for(int i=0;i<new_data.length;i++){
	    		new_theta[i]=thetaM.get(i);
	    	}
	    	return new_theta;
	    }
}
