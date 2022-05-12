import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //copy your file's path here
        FileReader fileReader = new FileReader("contacts.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        String name="";
        String lastname="";
        String phone="";
        String cards="";
        int counter;
        boolean a = true;
        int startLastName = 0,endLastname = 0,startPhone =0,endPhone= 0;
        //indexes are based on First Name,Middle Name,Last Name,Title,Suffix,Nickname,Given Yomi,Surname Yomi,E-mail Address,E-mail 2 Address,E-mail 3 Address,Home Phone,Home Phone 2,Business Phone,Business Phone 2,Mobile Phone,Car Phone,Other Phone,Primary Phone,Pager,Business Fax,Home Fax,Other Fax,Company Main Phone,Callback,Radio Phone,Telex,TTY/TDD Phone,IMAddress,Job Title,Department,Company,Office Location,Manager's Name,Assistant's Name,Assistant's Phone,Company Yomi,Business Street,Business City,Business State,Business Postal Code,Business Country/Region,Home Street,Home City,Home State,Home Postal Code,Home Country/Region,Other Street,Other City,Other State,Other Postal Code,Other Country/Region,Personal Web Page,Spouse,Schools,Hobby,Location,Web Page,Birthday,Anniversary,Notes
        for (String l: lines) {
            name = l.substring(0,l.indexOf(","));
            counter = 0;
            for (int i = l.indexOf(","); i < l.length(); i++) {
                if (l.charAt(i)==','){
                    counter++;
                }
                if (counter==2&&a){
                    startLastName=i+1;
                    a=false;
                }
                if (counter==3){
                    endLastname=i;
                    a=true;
                }
                if (counter==15&&a){
                    startPhone=i+1;
                    a=false;
                }
                if (counter==16){
                    endPhone=i;
                    a=true;
                }
            }
            lastname = l.substring(startLastName,endLastname);
            phone = l.substring(startPhone,endPhone);
            cards+="BEGIN:VCARD\n" +
                    "VERSION:2.1\n" +
                    "N:"+lastname+";"+name+";;;\n" +
                    "FN:"+name+" "+lastname+"\n" +
                    "TEL;CELL:"+phone+"\n" +
                    "TEL;CELL:"+phone+"\n" +
                    "END:VCARD\n";
        }
        System.out.println(cards);
        try {
            FileWriter myWriter = new FileWriter("contacts.vcf");
            myWriter.write(cards);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
