$(document).ready(function () {
  //настройка библиотеки FancyBox для просмотра картинок
  $(".fancybox").fancybox({
    afterLoad: function() {
      this.title = '<a href="/downloadImage?link=' + this.href + '&imageTitle=' + this.title + '">Скачать</a> ';
    },
    helpers : {
      title: {
        type: 'float'
      }
    }
  });

  //подключение всплывающих описаний к альбомам
  $(".albumbox").tooltip();

});