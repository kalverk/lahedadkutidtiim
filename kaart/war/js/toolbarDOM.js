//jQuery on küll aeglasem kui javascriptiga seda teha aga jQuery on crossplatvorm
$(document).ready(function() {
	$('#navigation input').click(function(event) {
		var categoryName = $(this).attr("id");
		var hash = "#" + $(this).attr("id");
		window.history.pushState(categoryName, "Spordikaart", hash);

//		getInfo(categoryName);
	});
});

function getInfo(categoryName){
	breakInterval();
	$.get('KaartServlet', {
		method : "findByCategory",
		category : categoryName
	}, function(responseText) {
		toMap(responseText, categoryName);	
	});
}

//Comment bari osa

$('#comment-buttonid').attr('disabled','disabled');

$('#commentBox').keyup(function(){
    var text = $('#commentBox').val();
    if(text.length<1){
        $('#comment-buttonid').attr('disabled','disabled');
    }else{
        $('#comment-buttonid').removeAttr('disabled');
    }
}); 

$('#comment-buttonid').click(function(){
    if ( $('#comment-buttonid').attr('disabled') == "disabled" ) {
       return false;
      }
    else {
       alert("clicked");
   }
});