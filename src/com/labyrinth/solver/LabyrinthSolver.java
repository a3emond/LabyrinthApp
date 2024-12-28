package com.labyrinth.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LabyrinthSolver {
    private final int[][] maze; // The labyrinth
    private final boolean[][] visited; // To track visited cells
    private final int rows, cols; // Dimensions of the maze

    //Directions for moving up, down, left and right
    private final int[] rowDir = {-1, 1, 0, 0};
    private final int[] colDir = {0, 0, -1, 1};

    public LabyrinthSolver(int[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
        this.visited = new boolean[rows][cols];
    }

    // Solve the maze and return the path
    public List<Cell> solve(int startRow, int startCol, int goalRow, int goalCol) {
        Stack<Cell> stack = new Stack<>();
        List<Cell> path = new ArrayList<>();

        // Start from the initial position
        stack.push(new Cell(startRow, startCol));
        visited[startRow][startCol] = true;

        while (!stack.isEmpty()) {
            Cell current = stack.peek();

            // Check if we've reached the goal
            if (current.row == goalRow && current.col == goalCol) {
                path.addAll(stack); // Copy the stack to the path
                return path;
            }

            boolean moved = false;

            // Check all four directions
            for (int i = 0; i < 4; i++) {
                int newRow = current.row + rowDir[i];
                int newCol = current.col + colDir[i];

                if (isValidMove(newRow, newCol)) {
                    // Mark as visited and move
                    visited[newRow][newCol] = true;
                    stack.push(new Cell(newRow, newCol));
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                // No valid moves, backtrack
                stack.pop();
            }
        }

        // Return an empty path if no solution is found
        return path;
    }

    // Check if a move is valid
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && maze[row][col] == 1 && !visited[row][col];
    }

    // Inner class to represent a cell
    static class Cell {
        int row, col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }






    // Depth-First Search algorythm
    /*
    Algorithm Explanation: Depth-First Search

    Initialization

        Start from the given start point.
        Mark the start cell as visited.
        Push it onto the stack.

    Exploration

        While the stack is not empty:
            Look at the current cell on top of the stack.
            Check its neighbors (up, down, left, right).
        If a neighbor is valid (not visited and a path):
            Mark it as visited.
            Push it onto the stack.
        If no neighbors are valid:
            Backtrack by popping the cell from the stack.

    Goal Check

    If the current cell is the goal point, stop the algorithm and return the path.
    */

}
