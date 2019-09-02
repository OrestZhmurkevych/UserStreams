package edu.userstreams;

import edu.userstreams.models.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class App {

    private static Logger logger = LogManager.getLogger("App");

    private static List<User> listFiller() {
        final List<User> users = new ArrayList<>();
        users.add(new User(1, "Petro", "Petrenko", "Petrovych", "05.07.1977",
                new BigDecimal(100.0)));
        users.add(new User(2, "Ivan", "Ivanenko", "Ivanovych", "17.08.1959",
                new BigDecimal(975.0)));
        users.add(new User(3, "Andrij", "Andrijenko", "Andrijovych", "07.11.1992",
                new BigDecimal(890.5)));
        users.add(new User(4, "Alex", "Mahone", "Oleksandrovych", "07.10.1992",
                new BigDecimal(781.5)));
        users.add(new User(5, "Vasyl", "Vasylenko", "Vasyljovych", "12.09.2001",
                new BigDecimal(95.5)));
        users.add(new User(6, "Andrij", "Ohijenko", "Andrijovych", "08.10.1995",
                new BigDecimal(250.0)));
        users.add(new User(7, "Ihor", "Ihorenko", "Ihorovych", "01.01.1991",
                new BigDecimal(1000.0)));
        users.add(new User(8, null, "Strov", "Ivanovych", "03.03.1984",
                new BigDecimal(123.0)));
        users.add(new User(9, "Andrij", "Syla", "Myhajlovych", "10.04.1987",
                new BigDecimal(1250.5)));
        users.add(new User(10, "Myhajlo", "Myhajlenko", "Jaroslavovych", "25.12.1999",
                new BigDecimal(5000.0)));
        return users;
    }

    private static List<Integer> filterListByUsername(final List<User> users, final String name) {
        List<Integer> result;
        if (StringUtils.isEmpty(name)) {
            result = Collections.emptyList();
        } else {
            result = users.stream()
                    .filter(user -> name.equals(user.getFirstName()))
                    .map(User::getId)
                    .collect(Collectors.toList());
        }
        return result;
    }

    private static List<Integer> getUsersCodesWIthSalaryInRange(final BigDecimal min, final BigDecimal max, final List<User> users) {
        return users.stream()
                .filter(user -> min.compareTo(user.getSalary()) == -1 && max.compareTo(user.getSalary()) == 1)
                .map(User::getId)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        final BigDecimal rangeFrom = new BigDecimal(100);
        final BigDecimal rangeTo = new BigDecimal(1000);
        final List<User> users = listFiller();
        final List<Integer> andrijsCodes = filterListByUsername(users, "Andrij");
        logger.info("IDs of users with the name Andrij --> " + andrijsCodes);
        List<Integer> listOfUsersCodesWithSalaryFromHundredToThousand = getUsersCodesWIthSalaryInRange(rangeFrom, rangeTo, users);
        logger.info("IDs of users with salary more than 100 and less than 1000 --> " + listOfUsersCodesWithSalaryFromHundredToThousand);
    }
}
