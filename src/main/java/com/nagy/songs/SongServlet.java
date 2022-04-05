package com.nagy.songs;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashSet;
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
    private HashSet<Song> playlist = new HashSet<>();


    @Override
    public void init() throws ServletException {
        Song barbieGirl = new Song("Barbie Girl", "Aqua", "ZyhrYis509A");
        Song trulyMadlyDeeply = new Song("Truly Madly Deeply", "Savage Garden", "WQnAxOQxQIU");
        Song ironic = new Song("Ironic", "Alanis Morissette", "Jne9t8sHpUc");
        Song kissMe = new Song("Kiss Me", "Sixpence None the Richer", "8N-qO3sPMjc");
        Song oneHeadlight = new Song("One Headlight", "The Wallflowers", "Zzyfcys1aLM");
        Song longDecember = new Song("A Long December", "Counting Crows", "1D5PtyrewSs");
        Song novemberRain = new Song("November Rain", "Guns N'Roses", "8SbUC-UaAxE");

        playlist.add(barbieGirl);
        playlist.add(trulyMadlyDeeply);
        playlist.add(ironic);
        playlist.add(kissMe);
        playlist.add(oneHeadlight);
        playlist.add(longDecember);
        playlist.add(novemberRain);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String go = request.getParameter("go");
        if (go == null){
            go = "browse";
        }
        // add log in

        switch (go){
            case "add":
                addToPlaylist(request, response);
                break;
            case "list":
                viewUserPlaylist(request, response);
                break;
            case "empty":
                emptyPlaylist(request, response);
                break;
            case "remove":
                removeSong(request,response);
                break;
            case "logout":

                // log out
                HttpSession session = request.getSession();
                if (session.getAttribute("username")!= null){
                    response.sendRedirect("support/login?logout");
                    return;
                }


                break;
            case "browse":
            default:
                viewPlaylist(request,response);
                break;
        }
    }

    private void emptyPlaylist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        HashSet<Song> userPlaylist = (HashSet<Song>) session.getAttribute("userPlaylist");
        userPlaylist.clear();
        session.setAttribute("userPlaylist", userPlaylist);

        response.sendRedirect("songs?go=list");

    }

    private void removeSong(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String songIdString = request.getParameter("song");
        int songId;
        Song song = null;
        try{
            songId = Integer.parseInt(songIdString);
        } catch (NumberFormatException e){
            response.sendRedirect("songs");;
            return;
        }

        HttpSession session = request.getSession();
        HashSet<Song> userPlaylist = (HashSet<Song>) session.getAttribute("userPlaylist");
        if (session.getAttribute("userPlaylist") == null){
            response.sendRedirect("songs?go=list");
            return;
        }
        for(Song item : userPlaylist){
            if (item.getSongId() == songId){
                song = item;
                break;
            }
        }
        if (song == null){
            response.sendRedirect("songs");
            return;
        } else {
            userPlaylist.remove(song);
            session.setAttribute("userPlaylist", userPlaylist);
        }

        //System.out.println(session.getAttribute("userPlaylist"));

        response.sendRedirect("songs?go=list");





    }

    private void viewUserPlaylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // login
        HttpSession session = request.getSession();
        if (session.getAttribute("username")== null){
            response.sendRedirect("support/login");
            session.setAttribute("pageBeforeLogIn", "songs?go=list");
            return;
        }

        request.setAttribute("userPlaylist", playlist);
        request.getRequestDispatcher("/WEB-INF/ch05/playlist.jsp").forward(request,response);



    }

    private void addToPlaylist(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String songIdString = request.getParameter("song");
        int songId;
        Song song = null;

        try{
            songId = Integer.parseInt(songIdString);
        } catch (NumberFormatException e){
            response.sendRedirect("songs");;
            return;
        }

        // login
        HttpSession session = request.getSession();
        if (session.getAttribute("username")== null){
            response.sendRedirect("support/login");
            session.setAttribute("pageBeforeLogIn", "songs?go=add&song=" + songId);
            return;
        }

        for(Song item : playlist){
            if (item.getSongId() == songId){
                song = item;
                break;
            }
        }
        if (song == null){
            response.sendRedirect("songs");
            return;
        }

        session = request.getSession();

        if (session.getAttribute("userPlaylist") == null){
            session.setAttribute("userPlaylist", new HashSet<Song>());
        }
        HashSet<Song> userPlaylist = (HashSet<Song>) session.getAttribute(("userPlaylist"));

        if (!userPlaylist.contains(songId)){
            userPlaylist.add(song);
        }
        session.setAttribute("userPlaylist", userPlaylist);

//        System.out.println(session.getAttribute("userPlaylist"));

        response.sendRedirect("songs?go=list");



    }

    private void viewPlaylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("playlist", playlist);
        request.getRequestDispatcher("/WEB-INF/ch05/songs.jsp").forward(request,response);


    }

    private void createSongLibrary (){
        //Song barbieGirl = new Song();



    }


}
