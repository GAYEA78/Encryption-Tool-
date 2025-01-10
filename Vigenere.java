//Arona Gaye

package encryption;

import java.util.Scanner;

public class Vigenere implements Cipher {
    private String key;
    /* initialize this Vigenere object by getting the key string */
    public Vigenere(Scanner keyboard) { 
        System.out.print("Enter the key string: ");
        this.key = keyboard.nextLine().toUpperCase();
        if (this.key.isEmpty()) {
            System.out.println("Invalid input! Key must be at least one letter long. Try again.");
            System.exit(0);
        }
        if (!this.key.matches("[A-Z]+")) {
            System.out.println("Invalid input! Key must only have letters. Try again.");
            System.exit(0);
        }
    }
    /* encrypt plainText */
    @Override public String encrypt(String plainText) {
        return transformText(plainText, true);
    }

    /* decrypt cipherText */
    @Override public String decrypt(String cipherText) {
        return transformText(cipherText, false);
    }
    
private String transformText(String text, boolean encrypt) {
    text = text.toUpperCase();
    StringBuilder transformed = new StringBuilder();
    String fullKey = buildFullKey(text);

    for (int i = 0; i < text.length(); i++) {
        char c = text.charAt(i);
        if (Character.isLetter(c)) {
            int keyShift = fullKey.charAt(i) - 'A'; 
            if (!encrypt) {
                keyShift = -keyShift; 
            }
            int shiftedIndex = (c - 'A' + keyShift + 26) % 26; 
            transformed.append((char) ('A' + shiftedIndex));
        } else {
            transformed.append(c);
        }
    }
    return transformed.toString();
}


private String buildFullKey(String message) {
    StringBuilder fullKey = new StringBuilder();
    int keyLength = key.length();
    int keyIndex = 0;
    for (char ch : message.toCharArray()) {
        if (Character.isLetter(ch)) {
            fullKey.append(key.charAt(keyIndex));
            keyIndex = (keyIndex + 1) % keyLength; 
        } else {
            fullKey.append((char)0); 
        }
    }
    return fullKey.toString();
}

}
