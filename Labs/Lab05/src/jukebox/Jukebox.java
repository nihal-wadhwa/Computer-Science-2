package jukebox;

/**
 * A class that is representative of a Jukebox of Songs.
 *
 * @author Nihal Wadhwa
 */

import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

public class Jukebox
{
    private static final int SIM_NUM = 50000;
    private HashMap<Song,Integer> songSet;
    long seed;
    int numSongs;
    long timeSim;


    /**
     * Creates a jukebox with the songs read in the file file.
     * @param file file with songs being read into the jukebox
     * @throws FileNotFoundException
     */
    public Jukebox (String file) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File(file));
        while(in.hasNextLine())
        {
            String ln = in.nextLine();
            String[] param = ln.split("<SEP>", 4);
            String artist = param[2];
            String song = param[3];
            songSet.put(new Song(artist,song),0);
        }
    }

    /**
     * Runs simulations on the file
     * @param seed helps to generate random number with seed seed
     * @param jArray array of songs used for simulations
     */
    public void simulation(long seed, ArrayList<Song> jArray)
    {
        int numSim = 0;
        Random rand = new Random();
        rand.setSeed(seed);

        while(numSim < SIM_NUM)
        {
            HashSet<Song> songsPlayed = new HashSet<>();
            while(true)
            {
                int randSong = rand.nextInt(songSet.size());
                Song s = jArray.get(randSong);
                if (songsPlayed.contains(s))
                {
                    numSongs += songsPlayed.size();
                    break;
                }
                else
                {
                    songsPlayed.add(s);
                    songSet.replace(s, songSet.get(s)+1);
                }
            }
            numSim++;
        }
        timeSim = System.currentTimeMillis();
    }

    /**
     * Generates the statistics of the simulation
     */
    public void gen_stats()
    {
        System.out.println("Jukebox with " + songSet.size() + " songs starts rockin'...");
        System.out.println("Simulation took " + timeSim/1000 + " second/s");
        System.out.println("Number of simulations: 50000");
        System.out.println("Total number of songs played: " + numSongs);
        System.out.println("Average number of songs played per simulation to get duplicate: " + numSongs/ SIM_NUM);
        System.out.println("Most played song: " + mostPlayed().getArtist());
    }

    /**
     * Returns the most played song in the simulation
     * @return mostPlayedSong
     */
    public Song mostPlayed()
    {
        Song mostPlayedSong = null;
        int highestTime = 0;
        for(Map.Entry<Song, Integer> entry: this.songSet.entrySet())
        {
            if(entry.getValue() > highestTime)
            {
                highestTime = entry.getValue();
                mostPlayedSong = entry.getKey();
            }
        }
        return mostPlayedSong;
    }

    /**
     * Returns all the songs made by the same artist
     * @param songSet set of songs created by
     * @param mostPlayed
     */
    public void songsBySameArtist(HashMap<Song, Integer> songSet, Song mostPlayed)
    {
        Map<Song, Integer> artistSongs = new TreeMap<>();
        for(Map.Entry<Song, Integer> entry: this.songSet.entrySet())
        {
            if(entry.getKey().getArtist().equals(mostPlayed.getArtist()))
            {
                artistSongs.put(entry.getKey(), entry.getValue());
            }
        }
        for(Map.Entry<Song, Integer> entry: artistSongs.entrySet())
        {
            System.out.println(entry.getKey()+" with " + entry.getValue() + " plays.");
        }
    }

    /**
     * Calls all jukebox methods to run simulations and return its stats.
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        if (args.length != 2)
        {
            System.out.println("Usage: java Jukebox filename seed");
        }
        else
        {
            Jukebox j = new Jukebox(args[0]);
            ArrayList<Song> songs = new ArrayList<>(j.songSet.keySet());
            j.simulation(Long.parseLong(args[1]), songs);
            j.gen_stats();
            j.songsBySameArtist(j.songSet, j.mostPlayed());

        }
    }

}
