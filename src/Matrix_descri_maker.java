
public class Matrix_descri_maker {
	private static String[] l1 = {"grunwald","bu³garska","kopernika","ogrody"};
    private static String[] l2 = {"winogrady","kosmonautów","wilczak","naramowice","hawelañska","naramowicka"};
    private static String[] l3 = {"centrum","stare miasto","garbary"};
    private static String[] l4 = {"pi¹tkowo","kurpiñskiego","batorego","sobieskiego"};
    private static String[] l5 = {"rataje","or³a bia³ego"};
    private static String[] l6 = {"wilda","rolna"};
    private static String[] l7 = {"je¿yce"};
    private static String[] l8 = {"³azarz","matejki"};
    private static String[] l9 = {"górczyn"};
    private static String[] l10 = {"dêbiec"};
    private static String[] l11 = {"œródka","zawady",};
    private static String[] l12 = {"so³acz","plewiska","strzeszyn","podolany"}; //Tu wrzucilem dosiedla ktore nigdzie nie pasuja a maja domki jednorodzinne

    private static String[] o1 = {"remon"};
    private static String[] o2 = {"luksu"};
    private static String[] o3 = {"kamien"};
    private static String[] o4 = {"blok"};
    
    
    private static String [][] parseinfo = {l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,o1,o2,o3,o4};
    
    public static Double[] rowParser(String[] rows){
    	StringBuilder strB = new StringBuilder(rows[0]);
    	strB.append(rows[1]);
    	String line = strB.toString();
    	line = line.toLowerCase();
    	Double [] row = new Double[16];
		       for(int i = 0; i < parseinfo.length;i++){
		    	   row[i]=contains(line, parseinfo[i]);  
		       }
		return row;	
	}

    private static Double contains(String row ,String[] dict){
    	for(String warunek:dict ){
    		if(row.contains(warunek)){
    			return 1.0;
    		}
    	}
    	return 0.0;
    } 
}
