<%@ page import="java.util.HashSet" %>
<%@ page import="com.nagy.songs.Song" %>
<%
    HashSet<Song> playlist = (HashSet<Song>) request.getAttribute("playlist");
    HashSet<Song> userPlaylist = (HashSet<Song>) session.getAttribute("userPlaylist");

    if (userPlaylist == null){
        userPlaylist = new HashSet<Song>();
    }


%>
<jsp:include page="../include/header-homepage.jsp" />
<main class="flex-shrink-0 m-3" >

    <div class="row">
        <div class="col-md-10 mx-auto">
            <div class="row">
                <h2 class="col-md-2">Your Playlist:</h2>
                <p>Listen to the songs you added to your playlist</p>
                <div class="d-flex justify-content-start">
                    <a href="songs" class="btn btn-primary m-2">
                        Back to List
                    </a>
                    <a href="songs?go=empty" class="btn btn-danger m-2">
                        Empty Playlist
                    </a>
                </div>
            </div>

        </div>
        <div class="row">

            <div class="col-md-10 mx-auto d-flex flex-wrap justify-content-start">



                <%for( Song song : userPlaylist){  %>

                <div class="flex-md-column m-2">
                    <div class="card" style="width: 25rem; height: 25rem">
                        <iframe class="card-img-top" style="height: 15rem" src="https://www.youtube.com/embed/<%= song.getYoutube()%>" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                        <div class="card-body">
                            <h5 class="card-title"><%=song.getName()%></h5>
                            <p class="card-text">
                                By: <%=song.getArtist()%>
                            </p>
                            <a href="songs?go=remove&song=<%=song.getSongId()%>" class="btn btn-danger">Remove</a>
                        </div>
                    </div>
                </div>

                <%}%>

            </div>

        </div>
    </div>

<%--    <div class="col mx-auto">--%>
<%--        <div class="row mx-auto">--%>
<%--            <h2 class="col-md-2">Your Playlist:</h2>--%>
<%--            <p>Listen to the songs you added to your playlist</p>--%>
<%--            <div class="d-flex justify-content-start">--%>
<%--                <a href="songs" class="btn btn-primary m-2">--%>
<%--                    Back to List--%>
<%--                </a>--%>
<%--                <a href="songs?go=empty" class="btn btn-danger m-2">--%>
<%--                    Empty Playlist--%>
<%--                </a>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="d-flex flex-wrap justify-content-center">--%>

<%--        <%for( Song song : userPlaylist){  %>--%>

<%--            <div class="flex-md-column m-2">--%>
<%--        <div class="card" style="width: 25rem; height: 25rem">--%>
<%--            <iframe class="card-img-top" style="height: 15rem" src="https://www.youtube.com/embed/<%= song.getYoutube()%>" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>--%>
<%--            <div class="card-body">--%>
<%--                <h5 class="card-title"><%=song.getName()%></h5>--%>
<%--                <p class="card-text">--%>
<%--                    By: <%=song.getArtist()%>--%>
<%--                </p>--%>
<%--                <a href="songs?go=remove&song=<%=song.getSongId()%>" class="btn btn-danger">Remove</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--            </div>--%>

<%--        <%}%>--%>

<%--        </div>--%>



<%--    </div>--%>

</main>


<jsp:include page="../include/footer.jsp" />