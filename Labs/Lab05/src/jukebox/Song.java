package jukebox;
/**
 * A class that is representative of a Song
 *
 * @author Nihal Wadhwa
 */


import java.util.Objects;

public class Song implements Comparable<Song>
{
    protected String artist;
    protected String song;

    /**
     * Creates a new song
     * @param artist sets artist of song
     * @param song sets name of song
     */
    public Song(String artist, String song)
    {
        this.artist = artist;
        this.song = song;

    }

    /**
     * Returns artist of song
     * @return artist of song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns name of song
     * @return name of song
     */
    public String getSong() {
        return song;
    }

    /**
     * Sees if one song is equal to another
     * @return boolean: true if they are equal and false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song1 = (Song) o;
        return Objects.equals(artist, song1.artist) &&
                Objects.equals(song, song1.song);
    }

    /**
     * Returns hashcode of Song object
     * @return hashcode of song
     */
    @Override
    public int hashCode()
    {
        return artist.hashCode() + song.hashCode();
    }


    /**
     * Compares two songs
     * @return
     */
    @Override
    public int compareTo(Song o)
    {
        if (this.artist.compareTo(o.getArtist()) == 0)
        {
            return Integer.compare(this.song.compareTo(o.getSong()),0);
        }
        else if (this.artist.compareTo(o.getArtist()) < 0)
        {
            return -1;
        }
        else
            return 1;

    }
}
