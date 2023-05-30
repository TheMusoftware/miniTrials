package Datas;
import Manager.RadioManager;
import java.io.*;

public class CurrentPlaying {
    private static String songName = null;
    private static String composerName = null;
    private static int totalLike;
    public CurrentPlaying() {
    }

    public static String getSongName() {
        return songName;
    }

    public static void setSongName(String songName) {
        CurrentPlaying.songName = songName;
    }

    public static String getComposerName() {
        return composerName;
    }

    public static void setComposerName(String composerName) {
        CurrentPlaying.composerName = composerName;
    }

    public static int getTotalLike() {
        return totalLike;
    }

    public static void setTotalLike(int totalLike) {
        CurrentPlaying.totalLike = totalLike;
    }
    public static void setInfos(String [] informationForCurrentPlaying) throws IOException {
        /*
        index 0 present Song Name
        index 1 present Composer's name
        index 2 present likes
         */
        CurrentPlaying.setSongName(informationForCurrentPlaying[0]);
        CurrentPlaying.setComposerName(informationForCurrentPlaying[1]);
        CurrentPlaying.setTotalLike(Integer.parseInt(informationForCurrentPlaying[2]));
        CurrentPlaying.printSongInfo();
    }
    public static void printSongInfo() throws IOException {
        String filePath = "src/datas/Comments/"+songName.toLowerCase().replace(" ","")+".txt";
        System.out.printf("|  _______________________________________  |\n" +
                "| / .-----------------------------------. \\ |\n" +
                "| | | /\\ :    %-20s      | | |\n" +
                "| | |/--\\:....................... NR [ ]| | |\n" +
                "| | `-----------------------------------' | |\n" +
                "| |      //-\\\\   |         |   //-\\\\      | |\n" +
                "| |     ||( )||  |_________|  ||( )||     | |\n" +
                "| |      \\\\-//   :....:....:   \\\\-//      | |\n" +
                "| |       _ _ ._  _ _ .__|_ _.._  _       | |\n" +
                "| |      (_(_)| |(_(/_|  |_(_||_)(/_      | |\n" +
                "| |                           |           | |\n" +
                "| `______ ____________________ ____ ______' |\n" +
                "|        /    []             []    \\        |\n" +
                "|       /  ()    %-15s()  \\       |\n" +
                "!______/_____________________________\\______!\n",getSongName(),getComposerName());

        System.out.printf("Likes : %d%n",getTotalLike());
        System.out.println("Comments: ");
        File file = new File(filePath);
        if(!file.exists()) file.createNewFile();
        FileReader reader = new FileReader(filePath);
        try{
            BufferedReader bufferedReader = new BufferedReader(reader);
            String comment = bufferedReader.readLine();
            if(comment == null) System.out.println("There is no comment.");
            while (comment != null){
                System.out.println(comment);
                comment = bufferedReader.readLine();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        updateAllSongstxt();
    }
    public static void updateAllSongstxt() throws IOException {
        RadioManager.updateList();
        String filePath = "src/datas/AllSongs.txt";
        StringBuilder sb = new StringBuilder();
        for (String str : RadioManager.allSongs){
            sb.append(str);
            sb.append("\n");
        }
        String [] all = sb.toString().split("\n");
        for (int i = 0; i < all.length ; i++){
            if(all[i].contains(songName)){
                all[i] = getString();
            }
        }
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < all.length; i++) {
            writer.write(all[i]);
            writer.write("\n");
        }
        writer.close();
        DaysSong.findDaysSong();

    }
    private static String getString(){
        return getSongName()+"-"+getComposerName()+"-"+getTotalLike();
    }
}
