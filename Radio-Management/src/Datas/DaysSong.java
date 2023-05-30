package Datas;

import java.io.FileWriter;
import java.io.IOException;

import static Manager.RadioManager.allSongs;

public class DaysSong {
    private static String songName = null;
    private  static  String composerName = null;
    private static  int totalLike;

    public static String getSongName() {
        return songName;
    }

    public static void setSongName(String songName) {
        DaysSong.songName = songName;
    }

    public static String getComposerName() {
        return composerName;
    }

    public static void setComposerName(String composerName) {
        DaysSong.composerName = composerName;
    }

    public static int getTotalLike() {
        return totalLike;
    }

    public static void setTotalLike(int totalLike) {
        DaysSong.totalLike = totalLike;
    }
    public static void  findDaysSong() throws IOException {
        int mostLike = 0;
        for (int i = 0; i < allSongs.size(); i++) {
            String [] songInfo = allSongs.get(i).split("-");
            if(Integer.parseInt(songInfo[2])>mostLike){
                mostLike = Integer.parseInt(songInfo[2]);
               setSongName(songInfo[0]);
               setComposerName(songInfo[1]);
               setTotalLike(Integer.parseInt(songInfo[2]));
            }
        }
        FileWriter writer = new FileWriter("src/datas/SongOfTheDay.txt");
        writer.write(getString());
        writer.close();
    }
    private static String getString(){
        return getSongName()+"-"+getComposerName()+"-"+getTotalLike();
    }
}
