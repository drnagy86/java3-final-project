package com.nagy.ch06;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserProfileServlet", value = "/user/profile")
public class UserProfileServlet extends HttpServlet {

    private final List<User> users = new ArrayList<>();
    private final User[] usersArr = new User[2];

    @Override
    public void init() throws ServletException {
        User user1 = new User(1L,"derrick.nagy@kirkwood.edu", "Derrick", "Nagy");
        Map<String, Boolean> permissions = new HashMap<>();

        permissions.put("active", true);
        permissions.put("admin", true);
        user1.setPermissions(permissions);
        users.add(user1);
        usersArr[0] = user1;


        User user2 = new User(2L,"random@kirkwood.edu", "Ran", "Dom");
        Map<String, Boolean> permissions2 = new HashMap<>();
        permissions2.put("active", true);
        permissions2.put("admin", false);
        user2.setPermissions(permissions2);

        users.add(user2);

        usersArr[1] = user2;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDateTime today =LocalDateTime.now();
        String dayOfWeek = today.getDayOfWeek().toString();
        int currentYear = today.getYear();

        request.setAttribute("user", usersArr[0]);
        request.setAttribute("usersArr", usersArr);
        request.setAttribute("usersList", users);
        request.setAttribute("today", today);
        request.setAttribute("dayOfWeek", dayOfWeek);
        request.setAttribute("currentYear", currentYear);
        request.setAttribute("fullName", usersArr[0].getFirstName() + " " + usersArr[0].getLastName());

        request.setAttribute("a", "request");
        HttpSession session = request.getSession();
        session.setAttribute("a", "session");

        Map<Long, Character> vowels = new HashMap<>();
        vowels.put(1L, 'A');
        vowels.put(2L, 'E');
        vowels.put(3L, 'I');
        vowels.put(4L, 'O');
        vowels.put(5L, 'U');

        request.setAttribute("vowels", vowels);


        request.getRequestDispatcher("/WEB-INF/ch06/profile.jsp").forward(request, response);


    }

}
