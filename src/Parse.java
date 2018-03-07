import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Parse {
    public static void main(String[] args) throws IOException {
        ArrayList<String> sector = new ArrayList<String>();
        ArrayList<String> firmi = new ArrayList<String>();
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
            System.out.println(sec);
            try {
                Integer chislo = (Integer.valueOf(doc1.getElementsByClass("bold").text().substring(7).toString())/50+1);
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
        doc = Jsoup.connect("http://avtocentr-terminal-motors.biysk24.ru").get();
        Elements mail = doc.getElementsByTag("a");
        for (Element link2 : mail){
            if (link2.text().contains("@"))
            System.out.println(link2.text());
            //System.out.println();
        }
        //System.out.println(site);
        //Elements links = content.getElementsByTag("a");
        //Elements body3 = doc.select("[href].copy");
        //System.out.println(body.attr("href"));
        //System.out.println("body3= "+body3);
    }
}
