package com.freetoursegovia.freetoursegovia.controller;

import javax.servlet.http.HttpSession;

import com.freetoursegovia.freetoursegovia.model.User;
import com.freetoursegovia.freetoursegovia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SessionAttributes({"user_id"})
@RequestMapping(path = "/api/login")
public class SpringBootSessionController {

    @Autowired
    UserService userService;

    /**
        Login control
     */
    @PostMapping("/access")
    public User login(@RequestBody User user, HttpSession session) {

        try{
            User controlUser = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
            // Create session with user id
            session.setAttribute("user_id",controlUser.getId());
            return controlUser;
        } catch (Exception e){
            System.out.println(e);
            User notFound = new User();
            notFound.setName("User not found");
            notFound.setRol("0");
            return notFound;
        }

    }


    // Function to check if user has session initiated
    @GetMapping("/session")
    public User checkSession(HttpSession session){

        // String userId = (String) session.getAttribute("user_id");

        // int id = Integer.parseInt(userId);
        // System.out.println("####################################" + userId);
        System.out.println("####################################33" + session.getAttribute("user_id"));
        User user = userService.findUserById(6);

        return user;
    }

/*


    @PostMapping("/addNote")
    public String addNote(@RequestParam("note") String note, HttpServletRequest request) {
        //get the notes from request session
        List<String> notes = (List<String>) request.getSession().getAttribute("NOTES_SESSION");
        //check if notes is present in session or not
        if (notes == null) {
            notes = new ArrayList<>();
            // if notes object is not present in session, set notes in the request session
            request.getSession().setAttribute("NOTES_SESSION", notes);
        }
        notes.add(note);
        request.getSession().setAttribute("NOTES_SESSION", notes);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        List<String> notes = (List<String>) session.getAttribute("NOTES_SESSION");
        model.addAttribute("notesSession", notes != null ? notes : new ArrayList<>());
        return "home";
    }

    @PostMapping("/invalidate/session")
    public String destroySession(HttpServletRequest request) {
        //invalidate the session , this will clear the data from configured database (Mysql/redis/hazelcast)
        request.getSession().invalidate();
        return "redirect:/home";
    }
*/

}