<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="albums" required="true" rtexprvalue="true"
              description="List of albums to render on page" type="java.util.List" %>
<%@ attribute name="rowCount" required="true" rtexprvalue="true"
              description="How many rows to display in a table" type="java.lang.Integer" %>
<%@ attribute name="displaySize" required="true" rtexprvalue="true"
              description="Image preview size" type="org.aleksjdev.fotoset.model.ImageType"%>
<%@ attribute name="isOnlyRootAlbums" required="true" rtexprvalue="true"
              description="Sign to render only root albums" type="java.lang.Boolean" %>
<%@ attribute name="accountUserName" required="true" rtexprvalue="true"
              description="Account user name" type="java.lang.String" %>

<!-- Вывод альбомов в виде таблицы-->
<c:set var="albumNumber" value="0"/>
<c:set var="albumsInRow" value="0"/>
<TABLE>
    <c:forEach items="${albums}" var="album">
        <c:if test="${albumNumber % rowCount == 0}">
            <TR>
        </c:if>

        <c:set var="isShowAlbum" value="true"/>
        <c:if test="${isOnlyRootAlbums == true}">
            <c:set var="isShowAlbum" value="${album.parentAlbum == null}"/>
        </c:if>

        <c:if test="${isShowAlbum}">
            <c:forEach items="${album.imageMap}" var="mapEntry">
                <c:if test="${mapEntry.key == displaySize}">
                    <TD>
                        <a href="/${accountUserName}/album/${album.id}">
                            <div id="album_${album.id}" class="albumbox" title="${album.description}">
                                <h4>${album.title}</h4>
                                <img src="${mapEntry.value.url}" alt="${album.title}">
                            </div>
                            <c:set var="albumNumber" value="${albumNumber + 1}"/>
                            <c:set var="albumsInRow" value="${albumsInRow + 1}"/>
                        </a>
                    </TD>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${albumsInRow == rowCount}">
            </TR>
            <c:set var="albumsInRow" value="0"/>
        </c:if>

    </c:forEach>
</TABLE>
