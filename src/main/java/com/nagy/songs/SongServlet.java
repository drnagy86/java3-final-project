package com.nagy.songs;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedHashSet;


// /songs or /songs?go=view (to view a list of songs)
// /songs?go=add&song=1 (to add a single song to the playlist - don't allow duplicates)
// /songs?go=list (to view the playlist with every song's YouTube video embedded or linked)
// /songs?go=empty (to remove all songs from the playlist)
// /songs?go=remove&song=1 (to remove a single song from the playlist)
// /songs?go=logout (to logout and show the login page)


@WebServlet(name = "SongServlet", value = "/songs")
public class SongServlet extends HttpServlet {
    // list of songs (with no duplicates
    private LinkedHashSet<Song> songLibrary = new LinkedHashSet<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String go = request.getParameter("go");
        if (go == null){
            go = "browse";
        }
        // add log in


        switch (go){
            case "add":
                //addToPlaylist(request, response);
                break;
            case "list":
                //viewPlaylist(request,response);
                break;
            case "empty":
                //emptyPlaylist(request, response);
                break;
            case "remove":
                //removeSong(request,response);
                break;
            case "logout":
                //removeSong(request,response);
                break;
            case "browse":
            default:
                //browse(request, response);
                break;

        }
    }

    private void createSongLibrary (){
        //Song barbieGirl = new Song();


    }


}
