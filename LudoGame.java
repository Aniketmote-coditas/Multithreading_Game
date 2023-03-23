package game;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LudoGame {
    public static ArrayList<Ludo> players = new ArrayList<>(); // list of players
    public static void main(String[] args) {
        int[] board = new int[40]; // the game board
        Lock lock = new ReentrantLock(); // lock for synchronization
        // create 2 players
        for (int i = 1; i <= 2; i++) {
            players.add(new Ludo(i, board, lock));
        }
        // create and start threads for each player
        for (int i = 0; i < players.size(); i++) {
            Thread t = new Thread(players.get(i));
            t.start();
        }
    }
}
