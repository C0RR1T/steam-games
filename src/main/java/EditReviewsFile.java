import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditReviewsFile {
    private static final Pattern gameIdPattern = Pattern.compile("(\\d+?),");
    private static final Pattern gameNamePattern = Pattern.compile("\\d+?,(.+?,)");

    public static void main(String[] args) throws FileNotFoundException {
        var inputStream = EditReviewsFile.class.getClassLoader().getResourceAsStream("reviews_raw.csv");
        if (inputStream == null)
            return;

        var printStream = new PrintStream(new FileOutputStream("reviews.csv", true));

        Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8);

        HashMap<String, Integer> reviewsPerGame = new HashMap<>();

        printStream.println(sc.nextLine());
        Random r = new Random();

        var possibleGameIds = getAllGames();
        System.out.println("Finished processing all possible Game Ids");
        System.out.println(possibleGameIds);

        int currentLine = 0;

        while (sc.hasNextLine()) {
            currentLine++;

            String line = sc.nextLine();
            String gameId = getGameId(line);
            if (gameId == null) {
                System.err.println("Faulty line");
                continue;
            }

            var reviewCount = reviewsPerGame.get(gameId);

            if (!possibleGameIds.contains(gameId)) {
                continue;
            }

            if (reviewCount != null && reviewCount > 20) {
                continue;
            }

            System.out.printf("Currently working on line %d%n", currentLine);

            line = line.replaceFirst("(?<=\\d{1,},).+?(?=,)", String.valueOf(r.nextInt(Integer.MAX_VALUE)));




            printStream.println(line);
            reviewsPerGame.put(gameId, Optional.ofNullable(reviewCount).orElse(0) + 1);
        }

        sc.close();
        printStream.close();

    }

    public static String getGameId(String line) {
        Matcher matcher = gameIdPattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(0).replaceAll(",", "");
        }
        return null;
    }

    public static List<String> getAllGames() {

        ArrayList<String> gameIds = new ArrayList<>();
        Scanner sc = new Scanner(Objects.requireNonNull(EditReviewsFile.class.getClassLoader().getResourceAsStream("steam.csv")));
        sc.nextLine();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String gameId = line.split(",")[0];
            if(!gameIds.contains(gameId)) {
                gameIds.add(gameId);
            }
        }

        sc.close();

        return gameIds;
    }

}
