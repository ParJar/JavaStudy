import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CollectionsLab {
    public static void main(String[] args) {
        mapsToMaps();
    }
    public static void mapsToMaps(){
        Map<String, Integer> channelToSubscribers    = new TreeMap<>(); // channel, numSubscribers
        Map<String, String> channelToPublisher       = new TreeMap<>(); // channel, publisher
        Map<String, Integer> publisherToSubscribers  = new TreeMap<>(); // publisher, numSubscribers

        // channel -> number of subscribers
        // K -> V1
        channelToSubscribers.put("JustForLaughs", 120_000);
        channelToSubscribers.put("JustForGags", 10_000);
        channelToSubscribers.put("ContemplationTechniques", 10_000);
        channelToSubscribers.put("A New Earth", 20_000);

        // channel -> publisher
        // K -> V2
        channelToPublisher.put("JustForLaughs", "Charlie Chaplin");
        channelToPublisher.put("JustForGags", "Charlie Chaplin");
        channelToPublisher.put("ContemplationTechniques", "Echhart Tolle");
        channelToPublisher.put("A New Earth", "Echhart Tolle");

        // 1. Setup "publisherToSubscribers"
        // publisher -> number of subscribers (total)
        // V2 -> V1
        channelToPublisher.forEach((k,v) -> {
            if( publisherToSubscribers.get(v) != null) {
                publisherToSubscribers.put(v, publisherToSubscribers.get(v) + channelToSubscribers.get(k));
            } else {
                publisherToSubscribers.put(v, channelToSubscribers.get(k));
            }
        });

        // 2. Output "publisherToSubscribers"

        publisherToSubscribers.forEach((k,v) -> System.out.println("publisher: "+ k +"; numSubscribers: " + v));

        // 3. Who has the most/least subscribers?

        String key = Collections.max(publisherToSubscribers.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Publisher with most subscribers: " + key + " " + Collections.max(publisherToSubscribers.values()));

        key = Collections.min(publisherToSubscribers.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Publisher with fewest subscribers: " + key + " " + Collections.min(publisherToSubscribers.values()));

    }
}
