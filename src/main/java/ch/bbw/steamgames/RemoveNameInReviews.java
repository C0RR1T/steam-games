package ch.bbw.steamgames;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class RemoveNameInReviews {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var url = RemoveNameInReviews.class.getClassLoader().getResource("reviews.csv");

        assert url != null;
        var br = Files.newBufferedReader(Path.of(url.toURI()));

        var reviews = new ArrayList<String[]>();

        String currentReview = br.readLine();
        int counter = 1;
        while (currentReview != null) {
            if (!reviews.contains(currentReview.split(","))) {
                reviews.add(currentReview.split(","));
                System.out.println(br.readLine());
                System.out.println(counter);
                currentReview = br.readLine();
                counter++;
            }
        }

        br.close();

        var bw = Files.newBufferedWriter(Path.of("./reviews.csv"));

        for (String[] review : reviews) {
            var newReview = Arrays.asList(review);
            newReview.remove(1);
            bw.write(String.format("%s,%s,%s,%s", newReview.get(0), newReview.get(1), newReview.get(2), newReview.get(3)));
            bw.newLine();
        }
        bw.close();
    }
}
