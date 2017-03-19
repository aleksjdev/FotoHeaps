<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="images" required="true" rtexprvalue="true"
              description="List of images to render on page" type="java.util.List" %>
<%@ attribute name="rowCount" required="true" rtexprvalue="true"
              description="How many rows to display in a table" type="java.lang.Integer" %>
<%@ attribute name="previewSize" required="true" rtexprvalue="true"
              description="Image preview size" type="org.aleksjdev.fotoset.model.ImageType" %>
<%@ attribute name="fullSize" required="true" rtexprvalue="true"
              description="Image full size" type="org.aleksjdev.fotoset.model.ImageType" %>
<%@ attribute name="alternativeSize" required="true" rtexprvalue="true"
              description="Alternative image size" type="org.aleksjdev.fotoset.model.ImageType" %>

<!-- Вывод картинок в виде таблицы-->
<c:set var="imageNumber" value="0"/>
<c:set var="imagesInRow" value="0"/>
<TABLE>
    <c:forEach items="${images}" var="image">
        <c:if test="${imageNumber % rowCount == 0}">
            <TR>
        </c:if>
        <c:set var="previewImageEntry" value="${image.imageMap[previewSize]}"/>
        <c:set var="fullImageEntry" value="${image.imageMap[fullSize]}"/>
        <c:if test="${fullImageEntry == null}">
            <c:set var="fullImageEntry" value="${image.imageMap[alternativeSize]}"/>
        </c:if>
        <TD>
            <div id="image_${image.id}" class="imagebox">
                <div class="fancybox" data-fancybox-href="${fullImageEntry.url}" data-fancybox-type="image"
                     data-fancybox-group="gallery" title="${image.title}">
                    <a href="#"><img src="${previewImageEntry.url}" alt="${image.title}"></a>
                </div>
            </div>
            <c:set var="imageNumber" value="${imageNumber + 1}"/>
            <c:set var="imagesInRow" value="${imagesInRow + 1}"/>
        </TD>
        <c:if test="${imagesInRow == rowCount}">
            </TR>
            <c:set var="imagesInRow" value="0"/>
        </c:if>

    </c:forEach>
</TABLE>
