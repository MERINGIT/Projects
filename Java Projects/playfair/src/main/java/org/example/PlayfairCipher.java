package org.example;

import java.util.Scanner;

public class PlayfairCipher {
    static final int SIZE = 5;

    // Method 1: Generate Key Matrix
    static char[][] generateKeyTable(String key) {
        char[][] keyT = new char[SIZE][SIZE];
        boolean[] visited = new boolean[26];
        int row = 0, col = 0;

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!visited[ch - 'A']) {
                keyT[row][col] = ch;
                visited[ch - 'A'] = true;
                col++;
                if (col == SIZE) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            if (!visited[c - 'A']) {
                keyT[row][col] = c;
                col++;
                if (col == SIZE) {
                    col = 0;
                    row++;
                }
            }
        }

        return keyT;
    }

    // Method 2: Encrypt using Playfair Cipher
    static String encrypt(String plaintext, char[][] keyT) {
        StringBuilder plaintextNoSpaces = new StringBuilder(plaintext.replaceAll("[^A-Z]", "").replace("J", "I"));
        StringBuilder ciphertext = new StringBuilder();

        while (plaintextNoSpaces.length() > 0) {
            char a = plaintextNoSpaces.charAt(0);
            char b;
            plaintextNoSpaces.deleteCharAt(0);

            if (plaintextNoSpaces.length() > 0) {
                b = plaintextNoSpaces.charAt(0);
                plaintextNoSpaces.deleteCharAt(0);
            } else {
                b = 'X'; // Add an extra character to make it even
            }

            int[] posA = findPosition(keyT, a);
            int[] posB = findPosition(keyT, b);

            if (posA[0] == posB[0]) {
                ciphertext.append(keyT[posA[0]][(posA[1] + 1) % SIZE]);
                ciphertext.append(keyT[posB[0]][(posB[1] + 1) % SIZE]);
            } else if (posA[1] == posB[1]) {
                ciphertext.append(keyT[(posA[0] + 1) % SIZE][posA[1]]);
                ciphertext.append(keyT[(posB[0] + 1) % SIZE][posB[1]]);
            } else {
                ciphertext.append(keyT[posA[0]][posB[1]]);
                ciphertext.append(keyT[posB[0]][posA[1]]);
            }
        }

        return ciphertext.toString();
    }

    // Method 3: Decrypt using Playfair Cipher
    static String decrypt(String ciphertext, char[][] keyT) {
        StringBuilder plaintext = new StringBuilder();

        // Add an extra character if the ciphertext length is odd
        if (ciphertext.length() % 2 != 0) {
            ciphertext += 'X';
        }

        while (ciphertext.length() > 0) {
            char a = ciphertext.charAt(0);
            char b = ciphertext.charAt(1);
            ciphertext = ciphertext.substring(2);

            int[] posA = findPosition(keyT, a);
            int[] posB = findPosition(keyT, b);

            if (posA[0] == posB[0]) {
                plaintext.append(keyT[posA[0]][(posA[1] + SIZE - 1) % SIZE]);
                plaintext.append(keyT[posB[0]][(posB[1] + SIZE - 1) % SIZE]);
            } else if (posA[1] == posB[1]) {
                plaintext.append(keyT[(posA[0] + SIZE - 1) % SIZE][posA[1]]);
                plaintext.append(keyT[(posB[0] + SIZE - 1) % SIZE][posB[1]]);
            } else {
                plaintext.append(keyT[posA[0]][posB[1]]);
                plaintext.append(keyT[posB[0]][posA[1]]);
            }
        }

        return plaintext.toString();
    }

    // Utility method to find position of a character in key matrix
    static int[] findPosition(char[][] keyT, char ch) {
        int[] pos = new int[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (keyT[i][j] == ch) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    // Driver code
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input key
        System.out.print("Enter the secret key (uppercase letters only): ");
        String key = scanner.nextLine();

        // Generate Key Table
        char[][] keyT = generateKeyTable(key);

        // Input plaintext
        System.out.println("Enter the plaintext (press Enter twice to finish):");
        StringBuilder plaintextBuilder = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            plaintextBuilder.append(line);
            plaintextBuilder.append('\n');
        }
        String plaintext = plaintextBuilder.toString().toUpperCase().replaceAll("[^A-Z]", "");

        // Encrypt using Playfair Cipher
        String ciphertext = encrypt(plaintext, keyT);
        System.out.println("Ciphertext: " + ciphertext);


        // Decrypt using Playfair Cipher
        String decryptedText = decrypt(ciphertext, keyT);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}
