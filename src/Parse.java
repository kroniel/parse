import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parse {
    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("http://biysk24.ru/companies/").get();
        Elements body = doc.getElementsByClass("copy");
        for (Element link : body){
            if (link.className().equals("copy"))
            System.out.println(link.attr("href"));
        }
        Document doc1 = Jsoup.connect("http://biysk24.ru/companies/section25.html").get();
        Elements site = doc1.getElementsByTag("td").select("b").select("a");
        for (Element link1 : site){
            System.out.println(link1.attr("href"));
        }
        Document doc2 = Jsoup.connect("http://avtocentr-terminal-motors.biysk24.ru").get();
        Elements mail = doc2.getElementsByTag("a");
        for (Element link2 : mail){
            if (link2.text().contains("@"))
            System.out.println(link2.text());
            System.out.println();
        }
        //System.out.println(site);
        //Elements links = content.getElementsByTag("a");
        //Elements body3 = doc.select("[href].copy");
        //System.out.println(body.attr("href"));
        //System.out.println("body3= "+body3);
    }
}
