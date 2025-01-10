//Arona Gaye

package encryption;

import java.util.Scanner;

public class Caesar implements Cipher {
    private int shift;
    
    /* initialize this Caesar object by getting the shift */
    public Caesar(Scanner keyboard) { 
        while (true) {
            System.out.print("Enter the Caesar shift value (0-25): ");
            String input = keyboard.nextLine();
            try {
                this.shift = Integer.parseInt(input);
                if (this.shift < 0 || this.shift > 25) {
                    System.out.println("Invalid input! Enter 0-25 next time. Try again.");
                    System.exit(0);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter an integer next time. Try again.");
                System.exit(0);
            }
        }
    }

    /* encrypt plainText */
    @Override public String encrypt(String plainText) {
        return transformText(plainText, this.shift);
    }

    /* decrypt cipherText */
    @Override public String decrypt(String cipherText) {
        return transformText(cipherText, -this.shift);
    }
    private String transformText(String text, int shift) {
        text = text.toUpperCase();
        StringBuilder transformed = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int originalAlphabetPosition = c - 'A';
                int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                if (newAlphabetPosition < 0) {
                    newAlphabetPosition += 26;
                }
                char newCharacter = (char) ('A' + newAlphabetPosition);
                transformed.append(newCharacter);
            } else {
                transformed.append(c); 
            }
        }
        return transformed.toString();
    }
}
