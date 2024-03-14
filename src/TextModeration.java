import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextModeration {
    public static void main(String[] args) {
        String text = "Hey there! This is a sample text with some offensive words like idiot and stupid.";

        // Define a list of offensive words
        String[] offensiveWords = {"idiot", "stupid", "dummy", "moron"};

        // Call the moderation method
        String moderatedText = moderateText(text, offensiveWords);

        // Print the moderated text
        System.out.println("Original text:");
        System.out.println(text);
        System.out.println("\nModerated text:");
        System.out.println(moderatedText);
    }

    // Method to moderate text
    public static String moderateText(String text, String[] offensiveWords) {
        // Create a pattern to match offensive words
        StringBuilder patternBuilder = new StringBuilder();
        patternBuilder.append("\\b(");
        for (int i = 0; i < offensiveWords.length; i++) {
            patternBuilder.append(Pattern.quote(offensiveWords[i]));
            if (i < offensiveWords.length - 1) {
                patternBuilder.append("|");
            }
        }
        patternBuilder.append(")\\b");
        Pattern pattern = Pattern.compile(patternBuilder.toString(), Pattern.CASE_INSENSITIVE);

        // Replace offensive words with asterisks
        Matcher matcher = pattern.matcher(text);
        StringBuilder moderatedText = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(moderatedText, "*".repeat(matcher.group().length()));
        }
        matcher.appendTail(moderatedText);

        return moderatedText.toString();
    }
}