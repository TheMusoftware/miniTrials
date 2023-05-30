package Manager;

import Datas.CurrentPlaying;
import Memberships.BaseMembership;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class RadioManager implements Runnable {
    Random random = new Random();
    public static List<String> allSongs;
    public RadioManager() throws FileNotFoundException {
        updateList();
    }
    public void playRandom() throws IOException, JavaLayerException {
        String playing = allSongs.get(random.nextInt(allSongs.size()));
        playSong(playing);
        updateList();
    }
    private void playSong(String str) throws IOException, JavaLayerException {
        String [] informationForCurrentPlaying = str.split("-");
        CurrentPlaying.setInfos(informationForCurrentPlaying);
        // System.out.println("Now playing "+CurrentPlaying.getSongName());
        //CurrentPlaying.printSongInfo();
        setPlayerSetting();
        updateList();
    }
    public static void updateList() throws FileNotFoundException {
        String filePath = "src/datas/AllSongs.txt";
        File file = new File(filePath);
        Scanner songs = new Scanner(file);
        allSongs = new ArrayList<>();
        while (songs.hasNextLine()){
            allSongs.add(songs.nextLine());
        }

    }

    @Override
    public void run() {
        try {
            while (true){
                int counter = 0;
                File file = new File("src/Datas/queue.txt");
                if(!file.exists()) file.createNewFile();
                FileReader reader = new FileReader(file);
                BufferedReader bf = new BufferedReader(reader);
                String song = bf.readLine();
                bf.close();
                reader.close();
                if(song!= null){
                    CurrentPlaying.setInfos(song.split("-"));
                    file.delete();
                    BaseMembership.setRequestInQueue(false);
                }
                else {
                    playRandom();
                }
                counter++;
                setPlayerSetting();
                player.play();
                if(counter>0) System.out.println("\nInput 0 to refresh.");

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }

    }

    static File music;
    static FileInputStream fis;
    static BufferedInputStream bif;
    static Player player;

    private  void setPlayerSetting() throws FileNotFoundException, JavaLayerException {
        String musicPath = "src/Datas/Samples/" + CurrentPlaying.getSongName().toLowerCase().replace(" ", "") + ".mp3";
        music = new File(musicPath);
        fis = new FileInputStream(music);
        bif = new BufferedInputStream(fis);
        player = new Player(bif);
    }
    public static void setQueue(String[] songInfo) throws IOException {
        String queuePath = "src/Datas/queue.txt";
        File file = new File(queuePath);
        FileWriter writer = new FileWriter(file);
        writer.write(songInfo[0] + "-" + songInfo[1] + "-" + songInfo[2]);
        writer.close();
    }

}

