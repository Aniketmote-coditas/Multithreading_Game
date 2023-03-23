package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.locks.Lock;

class Ludo implements Runnable {
    private int player; // player number
    private int position; // player's current position on the board
    private int[] board; // the game board
    private Random rand; // random number generator
    private Lock lock; // lock for synchronization

    // Constructor
    public Ludo(int player, int[] board, Lock lock) {
        this.player = player;
        this.position = 0;
        this.board = board;
        this.rand = new Random();
        this.lock = lock;
        //Collection a = new ArrayList<>();

    }

    // Roll the dice
    private int rollDice() {
        return rand.nextInt(6) + 1;
    }

    // Move the player
    private void movePlayer(int steps) {
        position += steps;
        if (position >= board.length) {
            position -= steps;
        }
    }

    // Check if the player wins
    private boolean checkWin() {
        return (position == board.length - 1);
    }
    private static int num=1;

    int temp=0;
    // Run the game
    public void run() {
        while (!checkWin()) {
            int steps = rollDice();
            System.out.println("Player " + player + " rolled " + steps);
            if(steps==6 && temp==0) {
                lock.lock(); // acquire the lock
                try {
                    board[position] = 0;
                    movePlayer(steps);
                    System.out.println("Player " + player + " moved to position " + position);
                    if (board[position] != 0) {
                        System.out.println("Player " + player + " bumped into another player!");
                        Ludo bumpedPlayer = LudoGame.players.get(board[position] - 1);
                        bumpedPlayer.position = 0;
                        System.out.println("Player " + bumpedPlayer.player + " moved back to position " + bumpedPlayer.position);
                    } else {
                        board[position] = player;
                    }
                } finally {
                    lock.unlock(); // release the lock
                }
                try {
                    Thread.sleep(1000); // sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(steps!=6 && temp!=0){
                lock.lock(); // acquire the lock
                try {
                    board[position] = 0;
                    movePlayer(steps);
                    System.out.println("Player " + player + " moved to position " + position);
                    if (board[position] != 0) {
                        System.out.println("Player " + player + " bumped into another player!");
                        Ludo bumpedPlayer = LudoGame.players.get(board[position] - 1);
                        bumpedPlayer.position = 0;
                        System.out.println("Player " + bumpedPlayer.player + " moved back to position " + bumpedPlayer.position);
                    } else {
                        board[position] = player;
                    }
                } finally {
                    lock.unlock(); // release the lock
                }
                try {
                    Thread.sleep(1000); // sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Player " + player + " wins!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+"position "+num++);

    }
}
