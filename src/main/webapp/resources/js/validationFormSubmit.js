function setValidationFormSubmitById(formId) {
  $('#' + formId).submit(function (e) {
    var frm = $('#' + formId);
    e.preventDefault();

    var data = {};
    $.each(this, function (i, v) {
      var input = $(v);
      data[input.attr("name")] = input.val();
      delete data["undefined"];
    });
    $.ajax({
      contentType: 'application/json; charset=utf-8',
      type: frm.attr('method'),
      url: frm.attr('action'),
      dataType: 'json',
      data: JSON.stringify(data),
      success: function (response) {
        if (response.status === 'SUCCESS') {
          $('#submitResult').html("<div class='alert alert-success' role='alert'>" + response.message + "</div>");
          clearForm(formId);
        } else if (response.status === 'FAIL') {
          $('#submitResult').html("<div class='alert alert-danger' role='alert'>" + response.message + "</div>");
        }
      },
      error: function () {
        $(this).html("Error");
      }
    });
  });
};

function clearForm(formId) {
  document.getElementById(formId).reset();
};