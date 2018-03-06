public class MyClass {
    public static void main(String args[]) {
        String[] roles = {
            "Gorodnichij","Ammos Fedorovich",
            "Artemij Filippovich",
            "Luka Lukich"};
        String[] textLines = {
            "Gorodnichij: Ja priglasil vas, gospoda, s tem, chtoby soobshhit vam preneprijatnoe izvestie: k nam edet revizor.",
            "Ammos Fedorovich: Kak revizor?",
            "Artemij Filippovich: Kak revizor?",
            "Gorodnichij: Revizor iz Peterburga, inkognito. I eshhe s sekretnym predpisan'em.",
            "Ammos Fedorovich: Vot te na!",
            "Artemij Filippovich: Vot ne bylo zaboty, tak podaj!",
            "Luka Lukich: Gospodi bozhe! eshhe i s sekretnym predpisan'em!"};
        
        StringBuilder answer = new StringBuilder("");
        for (int i = 0; i < roles.length; i++){
            answer.append(roles[i]+":\n");
            for (int j = 0; j < textLines.length; j++){
                if (textLines[j].startsWith(roles[i]+":")){
                    answer.append((j+1)+")"+textLines[j].replaceFirst(roles[i]+":","")+"\n");
                }
            }
            answer.append("\n");
        }
        System.out.println(answer.toString());
    }
}