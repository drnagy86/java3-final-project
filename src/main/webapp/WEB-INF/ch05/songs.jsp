<%@ page import="java.util.HashSet" %>
<%@ page import="com.nagy.songs.Song" %>
<%--<%--%>
<%--    HashSet<Song> playlist = (HashSet<Song>) request.getAttribute("playlist");--%>
<%--%>--%>
<jsp:include page="../include/header-homepage.jsp"/>
<main class="flex-shrink-0 m-3">

    <div class="row">
        <div class="col-md-6 mx-auto">
            <div class="row">
                <h2 class="col-md-2">PlayList:</h2>
                <p>Browse songs to add to your playlist</p>
                <div class="d-flex justify-content-start">
<%--                    <a href="songs?go=list" class="btn btn-primary m-2">--%>
                    <a href="<c:url value="songs?go=list"/>" class="btn btn-primary m-2">
                        Your Playlist
                    </a>
                </div>
            </div>

            <div class="row">
                <table class="table table-striped col-lg-8">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Artist</th>
                        <th></th>

                    </tr>
                    </thead>
                    <tbody>


                    <c:forEach items="${playlist}" var="song"  >
                        <tr>
                            <td>${song.name}
                            </td>
                            <td>${song.artist}
                            </td>
                            <td>

                                <a href="<c:url value="songs?go=add&song=${song.songId}"/>">
                                    <i class="bi bi-plus-square"></i>
                                </a>


                            </td>

                        </tr>

                    </c:forEach>


                    <%--                    <% for( Song song : playlist) {  %>--%>
                    <%--                    <tr>--%>
                    <%--                        <td><%=song.getName()%></td>--%>
                    <%--                        <td><%=song.getArtist()%></td>--%>
                    <%--                        <td>--%>

                    <%--                            <a href="songs?go=add&song=<%=song.getSongId()%>">--%>
                    <%--                                <i class="bi bi-plus-square"></i>--%>
                    <%--                            </a>--%>


                    <%--                        </td>--%>

                    <%--                    </tr>--%>

                    <%--                    <%}%>--%>


                    </tbody>
                </table>


            </div>

        </div>
    </div>

</main>


<jsp:include page="../include/footer.jsp"/>