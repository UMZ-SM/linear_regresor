import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.jblas.*;

public class Plik {
	public static double [][] new_data;
	public static ArrayList<String> data;
	public static double [] theta;
	
	 public static void main(String[] args) {
	        String nazwaPliku = "train.csv";
	        data = odczytajPlik(nazwaPliku);
	        new_data=konwersja(data);
	        zapiszPlik(nazwaPliku, new_data);
	        theta = MatrixNormal(new_data);
	    }
	 
	    public static void zapiszPlik(String nazwaPliku, double[][] dane) {
	        Path sciezka = Paths.get(nazwaPliku);
	        ArrayList<String> out = new ArrayList<>();
	        // Pobranie kolejnego "rzêdu" danych
	        for (double[] seria : dane) {
	            String s = Arrays.toString(seria);
	            out.add(s);
	        }
	        try {
	            Files.write(sciezka, out);
	        } catch (IOException ex) {
	            System.out.println("Nie mogê zapisaæ pliku!");
	        }
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
	            // Rozbijamy liniê na poszczególne linie (przedzielone przecinkami)
	            String[] liniaDaneString = linia.split(",");
	            
	            //Tutaj wstawiamy deskryptor
	            
	            // Tablica do przechowania danych w fomie liczb double
	            double[] liniaDouble = new double[liniaDaneString.length];
	            
	            // Pêtla, która pobiera z tablicy String-ów po jednej liczbie,
	            // konwertuje j¹ na liczbê double i zapisuje w tablicy typu double[]
	            for (int i = 0; i < liniaDouble.length; i++) {
	                liniaDouble[i] = Double.parseDouble(liniaDaneString[i]);
	            }
	            // Dodajemy tablicê z seri¹ danych do tablicy z wszystkimi danymi
	            daneOdczytane[nrLinii] = liniaDouble;
	            nrLinii++;
	        }
	        return daneOdczytane;
	    }
	    
	    public static double [] MatrixNormal(double [][] new_data) {
	    	ComplexDoubleDoubleMatrix X = new ComplexDoubleDoubleMatrix(new_data);
	    	ComplexDoubleDoubleMatrix X_t = X.transpose();
	    	ComplexDoubleDoubleMatrix theta_M=((X.mmul(X_t)).pinv).mmul(X_t);
	    	//trzeba jeszcze theta_M przemno¿yæ przez wektor y ale zapomnia³em czym on jest
	    	double [] theta= new double [2];
	    	theta[1]=theta_M.get(1);
	    	theta[1]=theta_M.get(2);
	    	return theta;
	    }
}
