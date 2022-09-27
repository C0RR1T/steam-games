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
    private static final Pattern csvLinePattern = Pattern.compile("^(\\d+?),(.+?),(.+?),(\\d+),(\\d+)$");
    private static final Pattern wrappingQuationmarksPattern = Pattern.compile("(^\"{1,}(?:.+))|((?<=.{1,}?)\"{1,}$)");

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

        for (int currentLine = 1; sc.hasNextLine(); currentLine++) {

            String line = sc.nextLine();
            String gameId = getGameId(line);
            

            if(gameId == null) {
                System.err.println("faulty line, Skipping..");
                continue;
            }

            var reviewCount = reviewsPerGame.get(gameId);

            if (!possibleGameIds.contains(gameId)) {
                continue;
            }

            final int REVIEWS_PER_GAME = 5;
            if (reviewCount != null && reviewCount > REVIEWS_PER_GAME) {
                continue;
            }

            String[] processedLine = getSplittedLine(line);

            System.out.printf("Currently working on line %d%n", currentLine);

            printStream.println(String.format("%s,%s,%s,%s,%s", processedLine[0], processedLine[1], processedLine[2], processedLine[3], processedLine[4]));

            reviewsPerGame.put(processedLine[0], Optional.ofNullable(reviewCount).orElse(0) + 1);
        }

        sc.close();
        printStream.close();

    }

    public static String getGameId(String line) {
        String result = "";
        for(char c: line.toCharArray()) {
            if(c == ',') break;
            else if(Character.isDigit(c)) {
                result += c;
            } else {
                return null;
            }
        }
        return result;
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

    private static final Random r = new Random();

    public static String[] getSplittedLine(String line) {
        Matcher matcher = csvLinePattern.matcher(line);
        if(!matcher.matches()) {
            return null;
        }
        String gameId = matcher.group(1);
        String reviewText = matcher.group(3);
        String reviewScore = matcher.group(4);
        String reviewVotes = matcher.group(5);

        reviewText = wrappingQuationmarksPattern.matcher(reviewText).replaceAll("").replaceAll("\"", "\"\"");
        reviewText = String.format("\"%s\"", reviewText);

        return new String[]{gameId, String.valueOf(r.nextInt(Integer.MAX_VALUE)), reviewText, reviewScore, reviewVotes};
    }

}
