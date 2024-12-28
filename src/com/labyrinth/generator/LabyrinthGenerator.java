package com.labyrinth.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LabyrinthGenerator {
    private final int rows, cols; // Dimensions of the maze
    private final int[][] maze; // Labyrinth grid
    private final Random random = new Random();

    //Directions for moving up, down, left and right
    private final int[] rowDir = {-1,1,0,0};
    private final int[] colDir = {0,0,-1,1};

    public LabyrinthGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new int[rows][cols];
        initializeMaze();
    }

    // public method to generate the maze
    public int[][] generate() {
        carvePath(0,0); // Start from the top-left corner (0,0)
        return maze;
    }

    // initialize the maze with all walls (0)
    private void initializeMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = 0; // 0 = wall
            }
        }
    }

    // Recursive backtracking method
    private void carvePath(int row, int col) {
        maze[row][col] = 1; // Mark the current cell as a path

        // Shuffle directions to randomize maze
        List<Integer> directions = new ArrayList<>();
        for (int i = 0; i < 4; i++) directions.add(i);
        Collections.shuffle(directions);

        for (int i : directions) {
            int newRow = row + rowDir[i];
            int newCol = col + colDir[i];

            // Check if the new cell is valid for carving
            if (isValidCell(newRow, newCol)) {
                carvePath(newRow, newCol); // Recursively carve the path
            }
        }
    }

    // Check if a cell is valid for carving
    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && maze[row][col] == 0;
    }

    // Print the maze (for debugging)
    public void printMaze() {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell == 0 ? "█" : " ");
            }
            System.out.println();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        LabyrinthGenerator generator = new LabyrinthGenerator(10, 10); // 10x10 maze
        int[][] generatedMaze = generator.generate();
        generator.printMaze();
    }
}





/*
Recursive Backtracking Algorithm
How It Works:
    Start at a random cell (or a fixed cell like [0][0]).
    Mark the current cell as part of the maze (e.g., set its value to 1).
    Randomly choose a neighbor and check if it is a valid candidate for connection:
        A neighbor is valid if:
            It hasn’t been visited yet.
            Connecting it won’t create cycles.
    Move to the chosen neighbor and repeat the process (recursion).
    If there are no valid neighbors, backtrack to the previous cell and try again.
 */