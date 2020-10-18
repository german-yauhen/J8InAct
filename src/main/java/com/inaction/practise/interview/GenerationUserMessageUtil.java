package com.inaction.practise.interview;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

@Component
public class GenerationUserMessageUtil {

    private Random random = new Random();

    public List<User> generateUsers(Integer count) {
        List<User> users = new ArrayList<>();
        IntStream.rangeClosed(1, count).forEach(index -> {
            users.add(new User(index, String.format("Name%s", index)));
        });
        return users;
    }

    public List<Message> generateMessages(Integer count, List<User> users) {
        int userCount = users.size() - 1;
        List<Message> messages = new ArrayList<>();
        Optional.ofNullable(count).ifPresent(cnt -> {
            IntStream.rangeClosed(1, count).forEach(index -> {
                User user = users.get(random.nextInt(userCount));
                int userId = user.getUserId();
                Message message = new Message(random.nextInt(count), String.format("text%s", userId),
                        randomDate(), userId);
                messages.add(message);
            });
        });
        return messages;
    }

    private LocalDate randomDate() {
        long fromTimestamp = LocalDate.now().minusDays(3).toEpochDay();
        long toTimestamp = LocalDate.now().toEpochDay();
        return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(fromTimestamp, toTimestamp));
    }

}
