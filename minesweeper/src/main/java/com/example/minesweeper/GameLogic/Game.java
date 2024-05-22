package com.example.minesweeper.GameLogic;

import java.util.Random;

public class Game {
    private int width;
    private int height;
    private int minesCount;
    private String[][] field;
    private boolean completed;

    // Конструктор
    public Game(int width, int height, int minesCount) {
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;
        this.field = new String[height][width];
        this.completed = false;

        // Инициализация поля
        initializeField();
    }

    private void initializeField() {
        // Заполнение поля пробелами
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = " ";
            }
        }

        // Установка мин случайным образом
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < minesCount) {
            int row = random.nextInt(height);
            int col = random.nextInt(width);
            if (!field[row][col].equals("X")) {
                field[row][col] = "X";
                minesPlaced++;
            }
        }

        // Заполнение поля числами
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (!field[row][col].equals("X")) {
                    int minesAround = countMinesAround(row, col);
                    field[row][col] = String.valueOf(minesAround);
                }
            }
        }
    }

    private int countMinesAround(int row, int col) {
        int mines = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                    if (field[newRow][newCol].equals("X")) {
                        mines++;
                    }
                }
            }
        }
        return mines;
    }

    public void makeMove(int row, int col) {
        if (field[row][col].equals("X")) {
            completed = true;
            // Открыть все мины
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (field[i][j].equals("X")) {
                        field[i][j] = "X";
                    }
                }
            }
        } else if (field[row][col].equals("0")) {
            openAdjacentCells(row, col);
        } else {
            field[row][col] = field[row][col]; // Открыть ячейку
        }

        // Проверка на завершение игры
        checkIfCompleted();
    }

    private void openAdjacentCells(int row, int col) {
        // Реализация открытия смежных ячеек
        // Тут должен быть ваш код для открытия смежных ячеек
    }

    private void checkIfCompleted() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j].equals(" ")) {
                    return;
                }
            }
        }
        completed = true;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j].equals("X")) {
                    field[i][j] = "M";
                }
            }
        }
    }

    // Getters и Setters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public String[][] getField() {
        return field;
    }

    public boolean isCompleted() {
        return completed;
    }
}
