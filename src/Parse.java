import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.util.*;

public class Parse {
    public static void main(String[] args) throws IOException {
        FileWriter file = new FileWriter("firmy.txt");
        FileWriter flpochta = new FileWriter("pochta.txt");
        ArrayList<String> sector = new ArrayList<String>();
        ArrayList<String> firmi = new ArrayList<String>();
        ArrayList<String> unfirmi = new ArrayList<String>();
        ArrayList<String> pochta = new ArrayList<String>();
        ArrayList<String> unpochta = new ArrayList<String>();
        Document doc,doc1, doc2;
        //Получение секторов фирм
        Elements body = Jsoup.connect("http://biysk24.ru/companies/").get().getElementsByClass("copy");
        for (Element link : body){
            if (link.className().equals("copy"))
            sector.add(link.attr("href"));
        }
        //Получение страниц с фирмами и полусение фирм
        for (String sec : sector){
            doc1 = Jsoup.connect("http://biysk24.ru/companies/"+sec).get();
            Elements site = doc1.getElementsByTag("td").select("b").select("a");
            for (Element link1 : site){
                firmi.add(link1.attr("href"));
            }
            //System.out.println(sec);
            try {
                Integer chislo = (Integer.valueOf(doc1.getElementsByClass("bold").text().substring(7))/50+1);
                System.out.println(chislo);
                for (int i = 1; i <= chislo; i++) {
                    doc2 = Jsoup.connect("http://biysk24.ru/companies/" + sec + (50 * i)).get();
                    site = doc2.getElementsByTag("td").select("b").select("a");
                    for (Element link1 : site) {
                            firmi.add(link1.attr("href"));
                    }
                }
            }catch (NumberFormatException e) {
                continue;
            }
        }
        System.out.println(firmi);
        //Сбор почты
        for (String link2 : firmi) {
            if (!link2.contains("_") &&
                    !link2.contains("@") &&
                    link2.contains("http") &&
                    !unfirmi.contains(link2)) {
                unfirmi.add(link2);
                file.write(link2+"\n");
            }
        }
        System.out.println(unfirmi);
        file.close();
        for (String firma : unfirmi){
            doc = Jsoup.connect(firma).get();
            Elements mail = doc.getElementsByTag("a");
            for (Element link2 : mail) {
                if (link2.text().contains("@")) {
                    pochta.add(link2.text());
                }
            }
        }
        System.out.println(pochta);
        for (String pochta1 : pochta){
            if (!unpochta.contains(pochta1)){
                unpochta.add(pochta1);
                flpochta.write(pochta1+"\n");
            }
        }
        System.out.println(unpochta);
        flpochta.close();
    }
}
